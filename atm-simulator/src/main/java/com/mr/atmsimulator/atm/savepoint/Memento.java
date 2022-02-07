package com.mr.atmsimulator.atm.savepoint;

import com.mr.atmsimulator.atm.Atm;

import java.time.LocalDateTime;

public class Memento {

    private final Atm atm;
    private final LocalDateTime createdAt;

    public Memento(Atm atm, LocalDateTime createdAt) {
        this.atm = atm.clone();
        this.createdAt = createdAt;
    }

    public Atm getAtm() {
        return atm;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "atm=" + atm +
                ", localDateTime=" + createdAt +
                '}';
    }
}
