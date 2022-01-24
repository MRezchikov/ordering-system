package com.mr.atmsymulator.banknote;

import java.util.Objects;

public class Banknote {

    private long count;
    private final Integer denomination;

    public Banknote(Integer denomination) {
        this.denomination = denomination;
    }

    public long getCount() {
        return count;
    }

    public Integer getDenomination() {
        return denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Banknote banknote = (Banknote) o;

        return Objects.equals(denomination, banknote.denomination);
    }

    @Override
    public int hashCode() {
        return denomination != null ? denomination.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "count=" + count +
                ", denomination=" + denomination +
                '}';
    }
}
