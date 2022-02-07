package com.mr.atmsimulator.atm.observer;

import com.mr.atmsimulator.atm.data.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerA implements Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerA.class);

    private static final long LOWER_LIMIT = 100_000L;

    @Override
    public void onBalanceLow(EventData eventData) {
        if (LOWER_LIMIT >= eventData.getBalanceCash()) {
            LOGGER.info("Print money and put in the atm");
        }
    }
}
