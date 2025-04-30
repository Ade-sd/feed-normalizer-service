package com.adde.processor;

import com.adde.model.SettlementMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("SETTLEMENT")
public class SettlementProcessor implements MessageProcessor<SettlementMessage> {
    

    @Override
    public void process(SettlementMessage message) {
        log.info("Processing settlement message: provider={}, eventId={}, outcome={}",
                message.getProvider(), message.getEventId(), message.getOutcome());
        
        // Business logic for handling settlement
        // e.g., settle bets in database, notify users, etc.
    }
}