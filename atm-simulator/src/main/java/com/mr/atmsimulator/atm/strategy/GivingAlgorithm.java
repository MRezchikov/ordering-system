package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.banknote.Banknote;

import java.util.Map;

public interface GivingAlgorithm {

    Map<Banknote, Integer> giveBanknotes(long requestedCash);
}
