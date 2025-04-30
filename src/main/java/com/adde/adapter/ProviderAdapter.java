package com.adde.adapter;

import com.adde.model.Message;

public interface ProviderAdapter {
    Message normalize(String payload);
}
