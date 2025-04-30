package com.adde.model;

import com.adde.model.enums.MessageType;
import com.adde.model.enums.OutcomeType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class OddsChangeMessage extends Message {
    private final Map<OutcomeType, Double> odds;
    
    public OddsChangeMessage(String eventId, String provider, LocalDateTime timestamp, Map<OutcomeType, Double> odds) {
        super(eventId, provider, timestamp, MessageType.ODDS_CHANGE);
        this.odds = odds;
    }
}