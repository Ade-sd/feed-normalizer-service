package com.adde.adapter;

import com.adde.model.Message;
import com.adde.model.OddsChangeMessage;
import com.adde.model.SettlementMessage;
import com.adde.model.enums.OutcomeType;
import com.adde.model.enums.SystemError;
import com.adde.model.exceptions.SystemException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Qualifier("alpha")
@Component
public class AlphaProviderAdapter implements ProviderAdapter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message normalize(String payload) {
        try {
            JsonNode root = objectMapper.readTree(payload);
            String msgType = root.get("msg_type").asText();
            String eventId = root.get("event_id").asText();

            Message message;
            if ("odds_update".equals(msgType)) {
                // Parse odds update message
                JsonNode values = root.get("values");
                Map<OutcomeType, Double> odds = new HashMap<>();
                odds.put(OutcomeType.HOME, values.get("1").asDouble());
                odds.put(OutcomeType.DRAW, values.get("X").asDouble());
                odds.put(OutcomeType.AWAY, values.get("2").asDouble());

                message = new OddsChangeMessage(eventId, "alpha", LocalDateTime.now(), odds);
            } else if ("settlement".equals(msgType)) {
                // Parse settlement message
                String outcome = root.get("outcome").asText();
                OutcomeType outcomeType = switch (outcome) {
                    case "1" -> OutcomeType.HOME;
                    case "X" -> OutcomeType.DRAW;
                    case "2" -> OutcomeType.AWAY;
                    default -> throw new IllegalArgumentException("Unknown outcome: " + outcome);
                };

                message = new SettlementMessage(eventId, "alpha", LocalDateTime.now(), outcomeType);
            } else {
                throw new IllegalArgumentException("Unknown message type: " + msgType);
            }

            return message;
        } catch (Exception e) {
            throw new SystemException(SystemError.BAD_REQUEST, "Failed to normalize Alpha provider message", 1235);
        }
    }
}
