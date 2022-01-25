package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.banknote.Banknote;

import java.util.List;

public class FirstTakingAlgorithm implements TakingAlgorithm {

    private final List<Banknote> banknotes;

    public FirstTakingAlgorithm(List<Banknote> banknotes) {
        this.banknotes = banknotes;
    }

    @Override
    public long takeBanknotes() {

        long money = 0;

        for (Banknote banknote : banknotes) {
            money = money + banknote.getCount() * banknote.getDenomination();
        }

        return money;
    }
}
