package com.adde.adapter;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProviderAdapterFactory {

    private final Map<String, ProviderAdapter> adapters;

    public ProviderAdapterFactory(Map<String, ProviderAdapter> adapters) {
        this.adapters = adapters;
    }

    public ProviderAdapter getAdapter(String providerName) {
        ProviderAdapter adapter = adapters.get(providerName);
        if (adapter == null) {
            throw new IllegalArgumentException("Unsupported provider: " + providerName);
        }
        return adapter;
    }



}
