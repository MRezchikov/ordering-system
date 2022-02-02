package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;

import java.util.Map;

import static com.mr.atmsimulator.validation.BanknoteValidation.checkBanknoteAndReturnIncorrect;

public class FirstAtm implements Atm {

    private final GivingAlgorithm givingAlgorithm;
    private final MoneyStorage moneyStorage;

    public FirstAtm(GivingAlgorithm givingAlgorithm,
                    MoneyStorage moneyStorage) {
        this.givingAlgorithm = givingAlgorithm;
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<Banknote, Integer> takeBanknotes(Map<Banknote, Integer> banknotes) {
        return moneyStorage.takeBanknotes(banknotes);
    }

    @Override
    public Map<Banknote, Integer> giveBanknotes(long requestedCash) {
        return givingAlgorithm.giveBanknotes(requestedCash);
    }

    @Override
    public long getBalanceCash() {
        return moneyStorage.getDenominationCellMap()
                .values()
                .stream()
                .mapToLong(s -> s.getCounter() * s.getDenomination().getValue())
                .sum();
    }

    @Override
    public String toString() {
        return "FirstAtm{" +
                "givingAlgorithm=" + givingAlgorithm +
                ", moneyStorage=" + moneyStorage +
                '}';
    }
}
