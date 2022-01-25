package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;

import java.util.List;

public class Atm {

    private final TakingAlgorithm takingAlgorithm;
    private final GivingAlgorithm givingAlgorithm;
    private final MoneyStorage moneyStorage;

    public Atm(TakingAlgorithm takingAlgorithm,
               GivingAlgorithm givingAlgorithm,
               MoneyStorage moneyStorage) {
        this.takingAlgorithm = takingAlgorithm;
        this.givingAlgorithm = givingAlgorithm;
        this.moneyStorage = moneyStorage;
    }

    public long takeBanknotes() {
        return takingAlgorithm.takeBanknotes();
    }

    public List<Banknote> giveBanknotes() {
        return givingAlgorithm.giveBanknotes();
    }

    public long getBalanceCash() {
        return this.moneyStorage.getBalanceCash();
    }
}
