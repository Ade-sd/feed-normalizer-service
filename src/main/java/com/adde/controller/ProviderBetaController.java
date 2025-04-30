package com.adde.controller;

import com.adde.adapter.ProviderAdapterFactory;
import com.adde.adapter.enums.ProviderType;
import com.adde.controller.statics.ProviderBetaApis;
import com.adde.model.Message;
import com.adde.processor.MessageProcessor;
import com.adde.processor.ProcessorFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProviderBetaApis.ROOT)
public class ProviderBetaController {
    private final ProviderAdapterFactory providerAdapterFactory;
    private final ProcessorFactory processorFactory;

    public ProviderBetaController(ProviderAdapterFactory providerAdapterFactory, ProcessorFactory processorFactory) {
        this.providerAdapterFactory = providerAdapterFactory;
        this.processorFactory = processorFactory;
    }

    @PostMapping(ProviderBetaApis.FEED)
    public ResponseEntity<String> receiveMessage(@RequestBody String payload) {
        Message normalizedMessage = providerAdapterFactory.getAdapter(ProviderType.BETA.getValue()).normalize(payload);

        // Process the normalized message
        MessageProcessor<Message> processor = processorFactory.getProcessor(normalizedMessage.getType());
        processor.process(normalizedMessage);

        return ResponseEntity.ok(normalizedMessage.toString());
    }

}
