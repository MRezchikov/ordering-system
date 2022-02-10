package com.mr.listener.homework;

import com.mr.listener.Listener;
import com.mr.model.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HistoryListener implements Listener, HistoryReader {

    private final Map<Long, Message> messages = new HashMap<>();

    @Override
    public void onUpdated(Message msg) {
        Message clone = msg.clone();
        messages.put(clone.getId(), clone);
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        return Optional.of(messages.get(id));
    }
}
