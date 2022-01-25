package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.banknote.Banknote;

import java.util.List;

public interface GivingAlgorithm {

    List<Banknote> giveBanknotes();
}
