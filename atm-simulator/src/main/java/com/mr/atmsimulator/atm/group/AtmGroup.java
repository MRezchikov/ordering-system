package com.mr.atmsimulator.atm.group;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ExecutorData;
import com.mr.atmsimulator.atm.observer.Listener;
import com.mr.atmsimulator.atm.savepoint.History;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtmGroup {

    private final List<Atm> atmGroup = new ArrayList<>();

    private ExecutorData executorData;

    public ExecutorData getExecutorData() {
        return executorData;
    }

    public void setExecutorData(ExecutorData executorData) {
        this.executorData = executorData;
    }

    public List<Atm> getAtmGroup() {
        return Collections.unmodifiableList(atmGroup);
    }

    public void addAtm(Atm atm) {
        atmGroup.add(atm);
    }

    public void withdrawMoneyFromAllAtms(long requestedCash) {
        for (Atm atm : atmGroup) {
            atm.giveBanknotes(requestedCash);
        }
    }

    public void createSavePoint(History history) {
        for (Atm atm : atmGroup) {
            history.createSavePoint(atm);
        }
    }

    public void addListener(Listener listener) {
        for (Atm atm : atmGroup) {
            atm.addListener(listener);
        }
    }

}
