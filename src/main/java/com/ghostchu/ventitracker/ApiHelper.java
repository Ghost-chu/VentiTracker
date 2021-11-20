package com.ghostchu.ventitracker;

import com.ghostchu.ventitracker.bean.ResponseBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ApiHelper {
    private static final Gson GSON = new Gson();
    public static ResponseBean executeSearchQuery(String keyword, String order) throws IOException {
        String url = "https://api.bilibili.com/x/web-interface/search/type?search_type=video&keyword={keyword}&order={order}";
        url = url.replace("{keyword}", URLEncoder.encode(keyword, StandardCharsets.UTF_8)).replace("{order}",order);
        String response = HttpUtil.createGet(url);
        ResponseBean bean = GSON.fromJson(response,ResponseBean.class);
        if(bean.getCode() != 0){
            throw new IOException("Bilibili API denied: "+bean.getCode()+": "+bean.getMessage());
        }
        return bean;
    }
}
