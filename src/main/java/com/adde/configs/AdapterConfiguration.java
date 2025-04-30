package com.adde.configs;

import com.adde.adapter.AlphaProviderAdapter;
import com.adde.adapter.BetaProviderAdapter;
import com.adde.adapter.ProviderAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AdapterConfiguration {

    @Bean
    public Map<String, ProviderAdapter> providerAdapters(
            @Qualifier("alpha") AlphaProviderAdapter alphaAdapter,
            @Qualifier("beta") BetaProviderAdapter betaAdapter) {

        Map<String, ProviderAdapter> adapters = new HashMap<>();
        adapters.put("provider-alpha", alphaAdapter);
        adapters.put("provider-beta", betaAdapter);
        return adapters;
    }

}
