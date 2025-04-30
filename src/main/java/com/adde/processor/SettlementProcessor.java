package com.adde.processor;

import com.adde.model.SettlementMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("SETTLEMENT")
public class SettlementProcessor implements MessageProcessor<SettlementMessage> {
    

    @Override
    public void process(SettlementMessage message) {
        //In this step, I just log each message.
        log.info("Processing settlement message: provider={}, eventId={}, outcome={}",
                message.getProvider(), message.getEventId(), message.getOutcome());

        // Business logic for handling settlement,
        // e.g., settles bets in a database or adds to que, etc.
    }
}