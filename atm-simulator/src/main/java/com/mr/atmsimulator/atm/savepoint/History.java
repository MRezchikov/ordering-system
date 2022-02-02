package com.mr.atmsimulator.atm.savepoint;

import com.mr.atmsimulator.atm.Atm;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

public class History {

    private final Deque<Memento> history = new ArrayDeque<>();

    public void createSavePoint(Atm atm) {
        history.push(new Memento(atm, LocalDateTime.now()));
    }

    public Atm restoreAtm() {
        Memento memento = history.pop();
        return memento.getAtm();
    }

}
