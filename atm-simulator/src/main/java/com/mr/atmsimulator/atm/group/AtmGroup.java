package com.mr.atmsimulator.atm.group;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.command.executor.Executor;
import com.mr.atmsimulator.banknote.Banknote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.mr.atmsimulator.atm.denomination.Denomination.FIFTY;
import static com.mr.atmsimulator.atm.denomination.Denomination.FIVE_HUNDRED;
import static com.mr.atmsimulator.atm.denomination.Denomination.FIVE_THOUSAND;
import static com.mr.atmsimulator.atm.denomination.Denomination.ONE_HUNDRED;
import static com.mr.atmsimulator.atm.denomination.Denomination.ONE_THOUSAND;
import static com.mr.atmsimulator.atm.denomination.Denomination.TEN;

public class AtmGroup {

    private final List<Atm> atmGroup = new ArrayList<>();

    public void addCashToEachAtm() {
        Map<Banknote, Integer> banknotes = new TreeMap<>((k1, k2) ->
                k2.getDenomination().getValue().compareTo(k1.getDenomination().getValue()));
        banknotes.put(new Banknote(FIVE_THOUSAND), 100);
        banknotes.put(new Banknote(ONE_THOUSAND), 100);
        banknotes.put(new Banknote(FIVE_HUNDRED), 100);
        banknotes.put(new Banknote(ONE_HUNDRED), 100);
        banknotes.put(new Banknote(FIFTY), 100);
        banknotes.put(new Banknote(TEN), 100);

        atmGroup.forEach(atm -> atm.takeBanknotes(banknotes));
    }

    public List<Atm> getAtmGroup() {
        return Collections.unmodifiableList(atmGroup);
    }

    public void addAtm(Atm atm) {
        atmGroup.add(atm);
    }


}
