package com.adde.processor;

import com.adde.model.Message;

public interface MessageProcessor<T extends Message> {
    void process(T message);
}