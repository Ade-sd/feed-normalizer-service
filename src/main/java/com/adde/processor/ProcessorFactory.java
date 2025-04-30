package com.adde.processor;

import com.adde.model.Message;
import com.adde.model.enums.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProcessorFactory {

    private final Map<MessageType, MessageProcessor<? extends Message>> processors;

    @Autowired
    public ProcessorFactory(Map<MessageType, MessageProcessor<? extends Message>> processors) {
        this.processors = processors;
    }

    @SuppressWarnings("unchecked")
    public <T extends Message> MessageProcessor<T> getProcessor(MessageType type) {
        MessageProcessor<? extends Message> processor = processors.get(type);
        if (processor == null) {
            throw new IllegalArgumentException("No processor found for message type: " + type);
        }
        // This cast is safe because we control the mapping between MessageType and processors
        return (MessageProcessor<T>) processor;
    }
}