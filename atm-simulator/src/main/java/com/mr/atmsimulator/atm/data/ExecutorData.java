package com.mr.atmsimulator.atm.data;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.observer.Listener;
import com.mr.atmsimulator.atm.savepoint.History;

import java.util.ArrayList;
import java.util.List;

public class ExecutorData {

    private final List<Atm> atms;
    private final List<Listener> listeners = new ArrayList<>();
    private History history;

    public ExecutorData(List<Atm> atms) {
        this.atms = atms;
    }

    public List<Atm> getAtms() {
        return atms;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
