package com.mr.listener;

import com.mr.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListenerPrinterConsole implements Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListenerPrinterConsole.class);

    @Override
    public void onUpdated(Message msg) {
        LOGGER.info("oldMsg: {}", msg);
    }
}
