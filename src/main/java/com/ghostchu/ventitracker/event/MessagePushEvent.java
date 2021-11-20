package com.ghostchu.ventitracker.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MessagePushEvent {
    private boolean success;
    private Throwable throwable;
}
