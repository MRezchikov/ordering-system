package com.mr.atmsimulator.util;

import com.mr.atmsimulator.banknote.Banknote;

import java.util.LinkedHashMap;
import java.util.Map;

public class AtmUtil {

    public static Map<Integer, Banknote> getIntegerBanknoteMap() {

        Map<Integer, Banknote> cells = new LinkedHashMap<>();

        cells.put(5000, new Banknote(5000));
        cells.put(1000, new Banknote(1000));
        cells.put(500, new Banknote(500));
        cells.put(100, new Banknote(100));
        cells.put(50, new Banknote(50));
        cells.put(10, new Banknote(10));
        return cells;
    }
}
