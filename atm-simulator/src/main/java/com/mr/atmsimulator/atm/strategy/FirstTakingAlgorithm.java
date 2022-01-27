package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;

import java.util.Map;
import java.util.TreeMap;

public class FirstTakingAlgorithm implements TakingAlgorithm {

    @Override
    public Map<Denomination, Cell> processAcceptedBanknotes(Map<Banknote, Integer> banknotes) {

        Map<Denomination, Cell> denominationCellMap = new TreeMap<>();

        banknotes.forEach((key, value) -> {
            Denomination denomination = key.getDenomination();
            denominationCellMap.put(denomination, new Cell(value, denomination));
        });

        return denominationCellMap;
    }
}
