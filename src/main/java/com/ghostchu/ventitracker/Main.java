package com.ghostchu.ventitracker;

import com.ghostchu.ventitracker.bean.ResponseBean;
import com.google.common.eventbus.EventBus;
import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.Yaml;
import de.leonhard.storage.internal.settings.ConfigSettings;
import de.leonhard.storage.internal.settings.ReloadSettings;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Main {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final Logger LOGGER = Logger.getLogger("Main");
    private final EventBus eventBus = new EventBus("Callback");
    private static final Yaml config = LightningBuilder
            .fromFile(new File("config.yml"))
            .setReloadSettings(ReloadSettings.INTELLIGENT)
            .setConfigSettings(ConfigSettings.PRESERVE_COMMENTS)
            .reloadCallback((file)-> LOGGER.info("检测到配置文件重载："+file.getFile().getAbsolutePath()))
            .createYaml();
    private static final Yaml persist = LightningBuilder
            .fromFile(new File("persist.yml"))
            .reloadCallback((file)-> LOGGER.info("检测到持久存储文件重载："+file.getFile().getAbsolutePath()))
            .setReloadSettings(ReloadSettings.INTELLIGENT)
            .setConfigSettings(ConfigSettings.SKIP_COMMENTS)
            .createYaml();
    private static final Timer timer = new Timer();
    public static void main(String[] args) {
        LOGGER.info(" 我摊牌了 我就是喜欢温迪 - 新视频追踪器 - By Ghost_chu ");
        LOGGER.info(">> 注册并启动轮询线程");
        long period = config.getOrSetDefault("period-time",30000);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                new Main().query();
            }
        },1000,period);
        Scanner scan = new Scanner(System.in);


    }
    public void query(){
        String keyword = config.getOrSetDefault("keyword","温迪");
        String order = config.getOrSetDefault("order","pubdate");
        String FCTQUrl = config.getOrSetDefault("fctq","https://sctapi.ftqq.com/xxxxxx.send");
        List<String> tagsNeeds = config.getOrSetDefault("tag",Collections.singletonList("温迪"));
        boolean openInBrowser = config.getOrSetDefault("open-in-browser",false);

            try{
                ResponseBean bean = ApiHelper.executeSearchQuery(keyword,order);
                List<ResponseBean.DataDTO.ResultDTO> videos = bean.getData().getResult();
                // 获取存储的记录
                List<String> persistList = persist.getStringList("records");
                for(ResponseBean.DataDTO.ResultDTO video : videos){
                    if(persistList.contains(video.getBvid())) continue;
                    persistList.add(video.getBvid());;

                    // 视频 Tag 过滤
                    List<String> tagMissing = matchesTags(video.getTag(),tagsNeeds);
                    if(!tagMissing.isEmpty()) {
                        LOGGER.info("跳过视频：" + video.getTitle()+" 原因：没有所需要的视频标签："+list2String(tagMissing));
                        LOGGER.info("跳过视频：" +video.getArcurl());
                        continue;
                    }

                    LOGGER.info(buildConsoleMessage(video));

                    // 打开浏览器
                    if(openInBrowser){
                        openInBrowser(video.getArcurl());
                    }

                    // 发送Server酱推送
                  sendServerPush(FCTQUrl, new FormBody.Builder()
                          .add("title", video.getTitle())
                          .add("desp", buildPushMessage(video))
                          .build());
                }
                persist.set("records",persistList);

            }catch (IOException e){
                LOGGER.error("在请求 BiliBili API 时出现错误",e);
                return;
            }
        LOGGER.info("查询结束 诶嘿~");
    }

    public void sendServerPush(String FCTQUrl, RequestBody body){
        if(FCTQUrl != null && !FCTQUrl.isEmpty()) {
            createPost(FCTQUrl, body);
        }
    }

    public void openInBrowser(String url){
        if (java.awt.Desktop.isDesktopSupported()) {
            try {
                java.net.URI uri = java.net.URI.create(url);
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String buildConsoleMessage(ResponseBean.DataDTO.ResultDTO video){
        return "诶嘿 发现了新视频：\n" + video.getTitle()+ "\n" +
                "UP主：" + video.getAuthor() + "  " +
                "播放量：" + video.getPlay() + "  " +
                "弹幕：" + video.getReview() + "  " +
                "收藏：" + video.getFavorites() + "\n" +
                "标签：" +video.getTag()+"\n"+
                "地址：" + video.getArcurl();
    }
    public String buildPushMessage(ResponseBean.DataDTO.ResultDTO video){
        return "UP主：" + video.getAuthor() + "\n" +
                "播放量：" + video.getPlay() + "\n" +
                "弹幕：" + video.getReview() + "\n" +
                "收藏：" + video.getFavorites() + "\n" +
                "地址：" + video.getArcurl()+"\n"+
                "标签：" +video.getTag()+"\n"+
                "更新时间：" + SIMPLE_DATE_FORMAT.format(new Date(video.getPubdate()*1000));
    }

    public List<String> matchesTags(String tags, List<String> required){
        List<String> missing = new ArrayList<>();
        for(String tag : required){
            if(!tags.contains(tag)) {
                missing.add(tag);
            }
        }
        return missing;
    }

    public static void createPost(String url, RequestBody content){
        try {
            HttpUtil.makePost(url, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert strList to String. E.g "Foo, Bar"
     *
     * @param strList Target list
     * @return str
     */
    @NotNull
    public static String list2String(@NotNull List<String> strList) {
        StringJoiner joiner = new StringJoiner(", ", "", "");
        strList.forEach(joiner::add);
        return joiner.toString();
    }

}
