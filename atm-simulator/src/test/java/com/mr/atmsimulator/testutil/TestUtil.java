package com.mr.atmsimulator.testutil;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;

import java.util.Map;

public class TestUtil {

    public static void populateAtm(Map<Banknote, Integer> banknotes, Atm atm) {
        atm.takeBanknotes(banknotes);
    }
}
