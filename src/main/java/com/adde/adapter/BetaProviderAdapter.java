package com.adde.adapter;

import com.adde.model.Message;
import com.adde.model.OddsChangeMessage;
import com.adde.model.SettlementMessage;
import com.adde.model.enums.OutcomeType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Qualifier("beta")
public class BetaProviderAdapter implements ProviderAdapter {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Message normalize(String payload) {
        try {
            JsonNode root = objectMapper.readTree(payload);
            String msgType = root.get("type").asText();
            String eventId = root.get("event_id").asText();
            
            Message message;
            if ("ODDS".equals(msgType)) {
                // Parse odds message
                JsonNode odds = root.get("odds");
                Map<OutcomeType, Double> normalizedOdds = new HashMap<>();
                normalizedOdds.put(OutcomeType.HOME, odds.get("home").asDouble());
                normalizedOdds.put(OutcomeType.DRAW, odds.get("draw").asDouble());
                normalizedOdds.put(OutcomeType.AWAY, odds.get("away").asDouble());
                
                message = new OddsChangeMessage(eventId, "beta", LocalDateTime.now(), normalizedOdds);
            } else if ("SETTLEMENT".equals(msgType)) {
                // Parse settlement message
                String result = root.get("result").asText();
                OutcomeType outcomeType = switch (result) {
                    case "home" -> OutcomeType.HOME;
                    case "draw" -> OutcomeType.DRAW;
                    case "away" -> OutcomeType.AWAY;
                    default -> throw new IllegalArgumentException("Unknown result: " + result);
                };

                message = new SettlementMessage(eventId, "beta", LocalDateTime.now(), outcomeType);
            } else {
                throw new IllegalArgumentException("Unknown message type: " + msgType);
            }
            
            return message;
        } catch (Exception e) {
            throw new RuntimeException("Failed to normalize Beta provider message", e);
        }
    }
}