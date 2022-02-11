package com.mr.atmsimulator.atm.observer;

import com.mr.atmsimulator.atm.data.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerA implements Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerA.class);

    private final long id;

    public ConsumerA(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void onBalanceLow(EventData eventData) {
        LOGGER.info("ConsumerA Print money and put in the atm {} ", eventData.getBalanceCash());
    }
}
