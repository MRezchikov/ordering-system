package com.mr.atmsimulator.storage;

import com.mr.atmsimulator.banknote.Banknote;

public class Cell {

    private final long count;
    private final Banknote banknote;

    public Cell(long count, Banknote banknote) {
        this.count = count;
        this.banknote = banknote;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "count=" + count +
                ", banknote=" + banknote +
                '}';
    }
}
