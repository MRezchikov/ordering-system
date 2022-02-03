package com.mr.atmsimulator.storage;

import com.mr.atmsimulator.atm.denomination.Denomination;

public class Cell implements Cloneable {

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (counter != cell.counter) return false;
        return denomination == cell.denomination;
    }

    @Override
    public int hashCode() {
        int result = (int) (counter ^ (counter >>> 32));
        result = 31 * result + denomination.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "counter=" + counter +
                ", denomination=" + denomination +
                '}';
    }

    protected Cell clone() {
        return new Cell(counter, denomination);
    }
}

