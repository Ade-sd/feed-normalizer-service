package com.adde.processor;

import com.adde.model.OddsChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Qualifier("ODDS_CHANGE")
public class OddsChangeProcessor implements MessageProcessor<OddsChangeMessage> {

    @Override
    public void process(OddsChangeMessage message) {
        //In this step, I just log each message.
        log.info("Processing odds change message: provider={}, eventId={}, odds={}",
                message.getProvider(), message.getEventId(), message.getOdds());

        // Business logic for handling odds change,
        // e.g., updates odds in a database or adds a message to que, etc.
    }
}