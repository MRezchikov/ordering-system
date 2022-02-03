package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;

import java.util.Map;

public interface GivingAlgorithm {

    Map<Banknote, Integer> giveBanknotes(long requestedCash, Map<Denomination, Cell> denominationCellMap);
}
