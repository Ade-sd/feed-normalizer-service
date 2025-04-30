package com.adde.model;

import com.adde.model.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public abstract class Message {
    private final String eventId;
    private final String provider;
    private final LocalDateTime timestamp;
    private final MessageType type;
}
