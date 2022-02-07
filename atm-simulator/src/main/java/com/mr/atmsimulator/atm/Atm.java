package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.data.ListenerAttribute;
import com.mr.atmsimulator.atm.data.EventData;
import com.mr.atmsimulator.atm.observer.Listener;
import com.mr.atmsimulator.atm.savepoint.History;
import com.mr.atmsimulator.banknote.Banknote;

import java.util.Map;

public interface Atm extends Cloneable {

    Map<Banknote, Integer> takeBanknotes(Map<Banknote, Integer> banknotes);

    Map<Banknote, Integer> giveBanknotes(long requestedCash);

    long getBalanceCash();

    Atm clone();

    void addListener(Listener listener, ListenerAttribute listenerAttribute);

    void removeListener(Listener listener);

    void notifyListeners(EventData eventData);

    void setOnline(boolean online);

    History getHistory();
}
