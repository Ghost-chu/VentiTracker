package com.ghostchu.ventitracker.event;

import com.ghostchu.ventitracker.bean.ResponseBean;

public class VideoFoundEvent extends AbstractVideoEvent {
    public VideoFoundEvent(ResponseBean.DataDTO.ResultDTO video) {
        super(video);
    }
}
