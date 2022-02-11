package com.mr.atmsimulator.atm.observer;

import com.mr.atmsimulator.atm.data.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerB implements Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerB.class);

    private final long id;

    public ConsumerB(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void onBalanceLow(EventData eventData) {
        LOGGER.info("ConsumerB Do something; Lower limit {} ", eventData.getBalanceCash());
    }
}
