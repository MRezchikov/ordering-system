package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import com.mr.atmsimulator.storage.MoneyStorage;

import java.util.Map;

public class FirstAtm implements Atm {

    private final TakingAlgorithm takingAlgorithm;
    private final GivingAlgorithm givingAlgorithm;
    private final MoneyStorage moneyStorage;

    public FirstAtm(TakingAlgorithm takingAlgorithm,
                    GivingAlgorithm givingAlgorithm,
                    MoneyStorage moneyStorage) {
        this.takingAlgorithm = takingAlgorithm;
        this.givingAlgorithm = givingAlgorithm;
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<Denomination, Cell> takeBanknotes(Map<Banknote, Integer> banknotes) {

        Map<Denomination, Cell> denominationCellMap = takingAlgorithm.processAcceptedBanknotes(banknotes);
        moneyStorage.setDenominationCellMap(denominationCellMap);

        long sum = banknotes.entrySet().stream()
                .mapToLong(entry -> (long) entry.getValue() * entry.getKey()
                        .getDenomination()
                        .getValue())
                .sum();

        moneyStorage.setBalanceCash(sum + moneyStorage.getBalanceCash());
        return denominationCellMap;
    }

    @Override
    public Map<Banknote, Integer> giveBanknotes(long requestedCash) {
        return givingAlgorithm.giveBanknotes(requestedCash);
    }

    @Override
    public long getBalanceCash() {
        return this.moneyStorage.getBalanceCash();
    }
}
