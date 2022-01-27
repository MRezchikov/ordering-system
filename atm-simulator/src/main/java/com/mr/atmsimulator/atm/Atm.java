package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;

import java.util.Map;

public interface Atm {

    Map<Denomination, Cell> takeBanknotes(Map<Banknote, Integer> banknotes);

    Map<Banknote, Integer> giveBanknotes(long requestedCash);

    long getBalanceCash();
}
