package com.adde.configs;

import com.adde.adapter.AlphaProviderAdapter;
import com.adde.adapter.BetaProviderAdapter;
import com.adde.adapter.ProviderAdapter;
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
public class ProviderConfiguration {


    @Bean
    public Map<String, ProviderAdapter> providerAdapters(
            @Qualifier("alpha") AlphaProviderAdapter alphaAdapter,
            @Qualifier("beta") BetaProviderAdapter betaAdapter) {

        Map<String, ProviderAdapter> adapters = new HashMap<>();
        adapters.put("provider-alpha", alphaAdapter);
        adapters.put("provider-beta", betaAdapter);
        return adapters;
    }

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
