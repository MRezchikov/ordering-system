package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.data.EventData;
import com.mr.atmsimulator.atm.observer.Listener;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FirstAtm implements Atm {

    private static final Logger LOGGER = LoggerFactory.getLogger(Atm.class);

    private boolean online;

    private final List<Listener> listeners = new ArrayList<>();
    private final MoneyStorage moneyStorage;

    public FirstAtm(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    public void setOnline(boolean online) {
        LOGGER.info("Online state: {}", online);
        this.online = online;
    }

    @Override
    public Map<Banknote, Integer> takeBanknotes(Map<Banknote, Integer> banknotes) {
        return moneyStorage.takeBanknotes(banknotes);
    }

    @Override
    public Map<Banknote, Integer> giveBanknotes(long requestedCash) {
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
    public String toString() {
        return "FirstAtm{" +
                ", moneyStorage=" + moneyStorage +
                '}';
    }

    @Override
    public Atm clone() {
        MoneyStorage moneyStorageClone = (MoneyStorage) moneyStorage.clone();
        return new FirstAtm(moneyStorageClone);
    }

    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(EventData eventData) {
        listeners.forEach(listener -> {
            listener.onBalanceLow(eventData);
        });
    }

}
