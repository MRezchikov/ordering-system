package com.mr.atmsimulator.storage;

import com.mr.atmsimulator.atm.denomination.Denomination;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MoneyStorage {

    private Long balanceCash;
    private Map<Denomination, Cell> denominationCellMap = new TreeMap<>(Comparator.reverseOrder());

    public Long getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(Long balanceCash) {
        this.balanceCash = balanceCash;
    }

    public Map<Denomination, Cell> getDenominationCellMap() {
        return denominationCellMap;
    }

    public void setDenominationCellMap(Map<Denomination, Cell> denominationCellMap) {
        this.denominationCellMap = denominationCellMap;
    }
}
