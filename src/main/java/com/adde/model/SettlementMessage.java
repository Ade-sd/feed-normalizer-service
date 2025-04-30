package com.adde.model;

import com.adde.model.enums.MessageType;
import com.adde.model.enums.OutcomeType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SettlementMessage extends Message {
    private final OutcomeType outcome;

    public SettlementMessage(String eventId, String provider, LocalDateTime timestamp, OutcomeType outcome) {
        super(eventId, provider, timestamp, MessageType.BET_SETTLEMENT);
        this.outcome = outcome;
    }
}