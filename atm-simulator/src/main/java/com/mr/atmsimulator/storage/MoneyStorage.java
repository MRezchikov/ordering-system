package com.mr.atmsimulator.storage;

import java.util.ArrayList;
import java.util.List;

public class MoneyStorage {

    private Long balanceCash;
    private List<Cell> cells = new ArrayList<>();

    public Long getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(Long balanceCash) {
        this.balanceCash = balanceCash;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
