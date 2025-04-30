package com.adde.adapter.enums;

import lombok.Getter;

@Getter
public enum ProviderType {
    ALPHA("alphaProviderAdapter"),
    BETA("betaProviderAdapter");

    private final String value;

    ProviderType(String value) {
        this.value = value;
    }
}
