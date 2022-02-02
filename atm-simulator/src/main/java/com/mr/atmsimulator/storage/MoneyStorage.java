package com.mr.atmsimulator.storage;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import static com.mr.atmsimulator.validation.BanknoteValidation.checkBanknoteAndReturnIncorrect;

public class MoneyStorage {

    private final TakingAlgorithm takingAlgorithm;

    private final Map<Denomination, Cell> denominationCellMap = new TreeMap<>(Comparator.naturalOrder());

    public MoneyStorage(TakingAlgorithm takingAlgorithm) {
        initDefaultValues();
        this.takingAlgorithm = takingAlgorithm;
    }

    public Map<Denomination, Cell> getDenominationCellMap() {
        return denominationCellMap;
    }

    public Map<Banknote, Integer> takeBanknotes(Map<Banknote, Integer> banknotes) {

        Map<Banknote, Integer> incorrectBanknotes = checkBanknoteAndReturnIncorrect(banknotes);

        if (incorrectBanknotes.size() != 0) {
            for (Banknote banknote : banknotes.keySet()) {
                banknotes.remove(banknote);
            }
        }
        takingAlgorithm.processAcceptedBanknotes(banknotes, denominationCellMap);

        return incorrectBanknotes;
    }

    private void initDefaultValues() {
        denominationCellMap.put(Denomination.FIVE_THOUSAND, new Cell(1000, Denomination.FIVE_THOUSAND));
        denominationCellMap.put(Denomination.ONE_THOUSAND, new Cell(1000, Denomination.ONE_THOUSAND));
        denominationCellMap.put(Denomination.FIVE_HUNDRED, new Cell(1000, Denomination.FIVE_HUNDRED));
        denominationCellMap.put(Denomination.ONE_HUNDRED, new Cell(1000, Denomination.ONE_HUNDRED));
        denominationCellMap.put(Denomination.FIFTY, new Cell(1000, Denomination.FIFTY));
        denominationCellMap.put(Denomination.TEN, new Cell(1000, Denomination.TEN));
    }
}
