package com.mr.atmsimulator.atm.observer;

import com.mr.atmsimulator.atm.data.EventData;

public interface Listener {

    void onBalanceLow(EventData eventData);

    long getId();
}
