package com.adde.configs;

import com.adde.model.Message;
import com.adde.model.enums.MessageType;
import com.adde.processor.MessageProcessor;
import com.adde.processor.OddsChangeProcessor;
import com.adde.processor.SettlementProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProcessorConfiguration {
    @Bean
    public Map<MessageType, MessageProcessor<? extends Message>> messageProcessors(
            @Qualifier("ODDS_CHANGE") OddsChangeProcessor oddsProcessor,
            @Qualifier("SETTLEMENT") SettlementProcessor settlementProcessor) {

        Map<MessageType, MessageProcessor<? extends Message>> processors = new HashMap<>();
        processors.put(MessageType.ODDS_CHANGE, oddsProcessor);
        processors.put(MessageType.BET_SETTLEMENT, settlementProcessor);
        return processors;
    }

}
