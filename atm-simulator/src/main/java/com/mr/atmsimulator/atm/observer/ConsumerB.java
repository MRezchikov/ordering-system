package com.mr.atmsimulator.atm.observer;

import com.mr.atmsimulator.atm.data.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerB implements Listener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerB.class);

    private static final long LOWER_LIMIT = 200_000L;

    @Override
    public void onBalanceLow(EventData eventData) {
        if (LOWER_LIMIT >= eventData.getBalanceCash()) {
            LOGGER.info("Do something; Lower limit: {}", LOWER_LIMIT);
        }
    }
}
