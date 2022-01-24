package com.mr.atmsymulator.atm;

import com.mr.atmsymulator.banknote.Banknote;

import java.util.List;

public interface Atm {

    boolean takeBanknotes(List<Banknote> banknotes);

    List<Banknote> giveBanknotes(long moneyRequestedAmount);

    long showCashBalance();
}
