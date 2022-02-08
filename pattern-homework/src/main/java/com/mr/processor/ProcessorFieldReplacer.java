package com.mr.processor;

import com.mr.model.Message;

public class ProcessorFieldReplacer implements Processor {

    @Override
    public Message process(Message message) {
        return message.toBuilder()
                .field11(message.getField12())
                .field12(message.getField11())
                .build();
    }
}
