package com.mr.atmsymulator.atm;

import com.mr.atmsymulator.banknote.Banknote;

import java.util.List;

public class FirstAtm implements Atm {

    @Override
    public boolean takeBanknotes(List<Banknote> banknotes) {
        return false;
    }

    @Override
    public List<Banknote> giveBanknotes(long moneyRequestedAmount) {
        return null;
    }

    @Override
    public long showCashBalance() {
        return 0;
    }
}
