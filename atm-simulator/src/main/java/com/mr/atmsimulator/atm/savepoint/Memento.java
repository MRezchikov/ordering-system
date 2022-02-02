package com.mr.atmsimulator.atm.savepoint;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.storage.MoneyStorage;

import java.time.LocalDateTime;
import java.util.Objects;

public class Memento {

    private final Atm atm;
    private final LocalDateTime localDateTime;

    public Memento(Atm atm, LocalDateTime localDateTime) {
        this.atm = atm;
        this.localDateTime = localDateTime;
    }

    public Atm getAtm() {
        return atm;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Memento memento = (Memento) o;

        if (!Objects.equals(atm, memento.atm)) return false;
        return Objects.equals(localDateTime, memento.localDateTime);
    }

    @Override
    public int hashCode() {
        int result = atm != null ? atm.hashCode() : 0;
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "atm=" + atm +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
