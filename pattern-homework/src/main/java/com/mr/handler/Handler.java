package com.mr.handler;

import com.mr.listener.Listener;
import com.mr.model.Message;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);
    void removeListener(Listener listener);
}
