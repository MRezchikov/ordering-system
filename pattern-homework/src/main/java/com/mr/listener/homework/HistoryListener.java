package com.mr.listener.homework;

import com.mr.listener.Listener;
import com.mr.model.Message;

import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {

    @Override
    public void onUpdated(Message msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        throw new UnsupportedOperationException();
    }
}
