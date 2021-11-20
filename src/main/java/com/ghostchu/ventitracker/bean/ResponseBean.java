package com.ghostchu.ventitracker.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ResponseBean {

    @com.google.gson.annotations.SerializedName("code")
    private Integer code;
    @com.google.gson.annotations.SerializedName("message")
    private String message;
    @com.google.gson.annotations.SerializedName("ttl")
    private Integer ttl;
    @com.google.gson.annotations.SerializedName("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @com.google.gson.annotations.SerializedName("seid")
        private String seid;
        @com.google.gson.annotations.SerializedName("page")
        private Integer page;
        @com.google.gson.annotations.SerializedName("pagesize")
        private Integer pagesize;
        @com.google.gson.annotations.SerializedName("numResults")
        private Integer numResults;
        @com.google.gson.annotations.SerializedName("numPages")
        private Integer numPages;
        @com.google.gson.annotations.SerializedName("suggest_keyword")
        private String suggestKeyword;
        @com.google.gson.annotations.SerializedName("rqt_type")
        private String rqtType;
        @com.google.gson.annotations.SerializedName("cost_time")
        private DataDTO.CostTimeDTO costTime;
        @com.google.gson.annotations.SerializedName("exp_list")
        private DataDTO.ExpListDTO expList;
        @com.google.gson.annotations.SerializedName("egg_hit")
        private Integer eggHit;
        @com.google.gson.annotations.SerializedName("result")
        private List<ResultDTO> result;
        @com.google.gson.annotations.SerializedName("show_column")
        private Integer showColumn;

        @NoArgsConstructor
        @Data
        public static class CostTimeDTO {
            @com.google.gson.annotations.SerializedName("params_check")
            private String paramsCheck;
            @com.google.gson.annotations.SerializedName("illegal_handler")
            private String illegalHandler;
            @com.google.gson.annotations.SerializedName("as_response_format")
            private String asResponseFormat;
            @com.google.gson.annotations.SerializedName("as_request")
            private String asRequest;
            @com.google.gson.annotations.SerializedName("save_cache")
            private String saveCache;
            @com.google.gson.annotations.SerializedName("deserialize_response")
            private String deserializeResponse;
            @com.google.gson.annotations.SerializedName("as_request_format")
            private String asRequestFormat;
            @com.google.gson.annotations.SerializedName("total")
            private String total;
            @com.google.gson.annotations.SerializedName("main_handler")
            private String mainHandler;
        }

        @NoArgsConstructor
        @Data
        public static class ExpListDTO {
            @com.google.gson.annotations.SerializedName("6603")
            private Boolean $6603;
            @com.google.gson.annotations.SerializedName("5501")
            private Boolean $5501;
        }

        @NoArgsConstructor
        @Data
        public static class ResultDTO {
            @com.google.gson.annotations.SerializedName("type")
            private String type;
            @com.google.gson.annotations.SerializedName("id")
            private Integer id;
            @com.google.gson.annotations.SerializedName("author")
            private String author;
            @com.google.gson.annotations.SerializedName("mid")
            private Integer mid;
            @com.google.gson.annotations.SerializedName("typeid")
            private String typeid;
            @com.google.gson.annotations.SerializedName("typename")
            private String typename;
            @com.google.gson.annotations.SerializedName("arcurl")
            private String arcurl;
            @com.google.gson.annotations.SerializedName("aid")
            private Integer aid;
            @com.google.gson.annotations.SerializedName("bvid")
            private String bvid;
            @com.google.gson.annotations.SerializedName("title")
            private String title;
            @com.google.gson.annotations.SerializedName("description")
            private String description;
            @com.google.gson.annotations.SerializedName("arcrank")
            private String arcrank;
            @com.google.gson.annotations.SerializedName("pic")
            private String pic;
            @com.google.gson.annotations.SerializedName("play")
            private Integer play;
            @com.google.gson.annotations.SerializedName("video_review")
            private Integer videoReview;
            @com.google.gson.annotations.SerializedName("favorites")
            private Integer favorites;
            @com.google.gson.annotations.SerializedName("tag")
            private String tag;
            @com.google.gson.annotations.SerializedName("review")
            private Integer review;
            @com.google.gson.annotations.SerializedName("pubdate")
            private Long pubdate;
            @com.google.gson.annotations.SerializedName("senddate")
            private Long senddate;
            @com.google.gson.annotations.SerializedName("duration")
            private String duration;
            @com.google.gson.annotations.SerializedName("badgepay")
            private Boolean badgepay;
            @com.google.gson.annotations.SerializedName("hit_columns")
            private List<String> hitColumns;
            @com.google.gson.annotations.SerializedName("view_type")
            private String viewType;
            @com.google.gson.annotations.SerializedName("is_pay")
            private Integer isPay;
            @com.google.gson.annotations.SerializedName("is_union_video")
            private Integer isUnionVideo;
            @com.google.gson.annotations.SerializedName("rec_tags")
            private Object recTags;
            @com.google.gson.annotations.SerializedName("new_rec_tags")
            private List<?> newRecTags;
            @com.google.gson.annotations.SerializedName("rank_score")
            private Integer rankScore;
            @com.google.gson.annotations.SerializedName("corner")
            private String corner;
            @com.google.gson.annotations.SerializedName("cover")
            private String cover;
            @com.google.gson.annotations.SerializedName("desc")
            private String desc;
            @com.google.gson.annotations.SerializedName("url")
            private String url;
            @com.google.gson.annotations.SerializedName("rec_reason")
            private String recReason;

            public String getTitle() {
                return title.replace("<em class=\"keyword\">","").replace("</em>","");
            }
        }
    }
}
