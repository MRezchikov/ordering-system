package com.mr.atmsimulator.atm.savepoint;

import com.mr.atmsimulator.atm.Atm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class History {

    private static final Logger LOGGER = LoggerFactory.getLogger(History.class);

    private final Deque<Memento> history = new ArrayDeque<>();
    private final DataTimeProvider dataTimeProvider;

    public History(DataTimeProvider dataTimeProvider) {
        this.dataTimeProvider = dataTimeProvider;
    }

    public void createSavePoint(Atm atm) {
        LOGGER.info("Save point was created at {}", dataTimeProvider.getDate());
        Memento memento = new Memento(atm, dataTimeProvider.getDate());
        history.push(memento);
    }

    public Atm restoreAtm() {
        Memento memento = history.pop();
        LOGGER.info("createdAt {} ", memento.getCreatedAt());
        return memento.getAtm();
    }

}
