package com.ghostchu.ventitracker.event;

import com.ghostchu.ventitracker.bean.ResponseBean;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class AbstractVideoEvent {
    private ResponseBean.DataDTO.ResultDTO video;
}
