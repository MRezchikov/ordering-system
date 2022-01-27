package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;

import java.util.Map;

public interface TakingAlgorithm {

    Map<Denomination, Cell> processAcceptedBanknotes(Map<Banknote, Integer> banknotes);
}
