package com.mr.atmsimulator.storage;

import com.mr.atmsimulator.atm.denomination.Denomination;

public class Cell {

    private long counter;
    private final Denomination denomination;

    public Cell(long counter, Denomination denomination) {
        this.counter = counter;
        this.denomination = denomination;
    }

    public long getCounter() {
        return counter;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "counter=" + counter +
                ", denomination=" + denomination +
                '}';
    }
}

