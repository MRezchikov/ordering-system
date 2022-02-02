package com.mr.atmsimulator.atm;

import com.mr.atmsimulator.banknote.Banknote;

import java.util.Map;

public interface Atm {

    Map<Banknote, Integer> takeBanknotes(Map<Banknote, Integer> banknotes);

    Map<Banknote, Integer> giveBanknotes(long requestedCash);

    long getBalanceCash();
}
