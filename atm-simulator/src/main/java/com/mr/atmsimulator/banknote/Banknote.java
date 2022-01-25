package com.mr.atmsimulator.banknote;

public class Banknote {

    private long count;
    private int denomination;

    public Banknote(int denomination) {
        this.denomination = denomination;
    }

    public Banknote(long count, int denomination) {
        this.count = count;
        this.denomination = denomination;
    }

    public long getCount() {
        return count;
    }

    public int getDenomination() {
        return denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Banknote banknote = (Banknote) o;

        return denomination == banknote.denomination;
    }

    @Override
    public int hashCode() {
        return denomination;
    }

    @Override
    public String toString() {
        return "Banknote{" +
                "count=" + count +
                ", denomination=" + denomination +
                '}';
    }
}
