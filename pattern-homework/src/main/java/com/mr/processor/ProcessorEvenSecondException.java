package com.mr.processor;

import com.mr.model.Message;

public class ProcessorEvenSecondException implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorEvenSecondException(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {

        int second = dateTimeProvider.getDateTime().getSecond();

        if (second % 2 == 0) {
            throw new EvenSecondException("Even second was happen: " + second);
        }

        return message;
    }
}
