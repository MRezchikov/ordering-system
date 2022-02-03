package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;

import java.util.Map;
import java.util.Objects;

public class FirstTakingAlgorithm implements TakingAlgorithm {

    @Override
    public Map<Denomination, Cell> processAcceptedBanknotes(Map<Banknote, Integer> banknotes,
                                                            Map<Denomination, Cell> denominationCellMap) {

        for (Map.Entry<Banknote, Integer> entry : banknotes.entrySet()) {
            Banknote key = entry.getKey();
            if (Objects.nonNull(key)) {
                Cell cell = denominationCellMap.get(key.getDenomination());
                cell.setCounter(cell.getCounter() + entry.getValue());
            }
        }

        return denominationCellMap;
    }
}
