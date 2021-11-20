package com.ghostchu.ventitracker.event;

import com.ghostchu.ventitracker.bean.ResponseBean;

public class VideoSkippedEvent extends AbstractVideoEvent{

    public VideoSkippedEvent(ResponseBean.DataDTO.ResultDTO video) {
        super(video);
    }
}
