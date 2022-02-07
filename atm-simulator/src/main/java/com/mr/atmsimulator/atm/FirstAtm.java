package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.data.EventData;
import com.mr.atmsimulator.atm.data.ListenerAttribute;
import com.mr.atmsimulator.atm.observer.Listener;
import com.mr.atmsimulator.atm.savepoint.History;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstAtm implements Atm {

    private static final Logger LOGGER = LoggerFactory.getLogger(Atm.class);

    private boolean online;

    private final History history;
    private final MoneyStorage moneyStorage;
    private final List<Listener> listeners = new ArrayList<>();
    private final Map<Long, ListenerAttribute> listenerIdAttributes = new HashMap<>();

    public FirstAtm(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
        this.history = new History(LocalDateTime::now);
    }

    public void setOnline(boolean online) {
        LOGGER.info("Online state: {}", online);
        this.online = online;
    }

    @Override
    public History getHistory() {
        return history;
    }

    @Override
    public Map<Banknote, Integer> takeBanknotes(Map<Banknote, Integer> banknotes) {

        history.createSavePoint(this);

        Map<Banknote, Integer> banknoteIntegerMap = moneyStorage.takeBanknotes(banknotes);

        listeners.forEach(listener -> {
            ListenerAttribute listenerAttribute = listenerIdAttributes.get(listener.getId());
            if (getBalanceCash() > listenerAttribute.getLowerLimit()) {
                listenerAttribute.setStopNotificationFlag(false);
            }
        });

        return banknoteIntegerMap;
    }

    @Override
    public Map<Banknote, Integer> giveBanknotes(long requestedCash) {

        history.createSavePoint(this);

        Map<Banknote, Integer> banknoteIntegerMap = moneyStorage.giveBanknotes(requestedCash);

        EventData eventData = EventData.builder()
                .balanceCash(getBalanceCash())
                .build();

        notifyListeners(eventData);

        return banknoteIntegerMap;
    }

    @Override
    public long getBalanceCash() {
        return moneyStorage.getDenominationCellMap()
                .values()
                .stream()
                .mapToLong(s -> s.getCounter() * s.getDenomination().getValue())
                .sum();
    }

    @Override
    public Atm clone() {
        MoneyStorage moneyStorageClone = (MoneyStorage) moneyStorage.clone();
        return new FirstAtm(moneyStorageClone);
    }

    @Override
    public void addListener(Listener listener, ListenerAttribute listenerAttribute) {
        listenerIdAttributes.put(listener.getId(), listenerAttribute);
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(EventData eventData) {
        listeners.forEach(listener -> {
            ListenerAttribute listenerAttribute = listenerIdAttributes.get(listener.getId());
            if (listenerAttribute.isStopNotificationFlag()) {
                return;
            }
            if (listenerAttribute.getLowerLimit() >= eventData.getBalanceCash()) {
                LOGGER.info("Notify the listeners");
                listener.onBalanceLow(eventData);
                listenerAttribute.setStopNotificationFlag(true);
            }
        });
    }

    @Override
    public String toString() {
        return "FirstAtm{" +
                ", moneyStorage=" + moneyStorage +
                '}';
    }
}
