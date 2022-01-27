package com.mr.atmsimulator;

import com.mr.atmsimulator.atm.FirstAtm;
import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.atm.strategy.FirstGivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.FirstTakingAlgorithm;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

import static com.mr.atmsimulator.atm.denomination.Denomination.FIFTY;
import static com.mr.atmsimulator.atm.denomination.Denomination.FIVE_HUNDRED;
import static com.mr.atmsimulator.atm.denomination.Denomination.FIVE_THOUSAND;
import static com.mr.atmsimulator.atm.denomination.Denomination.ONE_HUNDRED;
import static com.mr.atmsimulator.atm.denomination.Denomination.ONE_THOUSAND;
import static com.mr.atmsimulator.atm.denomination.Denomination.TEN;

public class AtmApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtmApplication.class);

    public static void main(String[] args) {

        MoneyStorage moneyStorage = new MoneyStorage();
        moneyStorage.setBalanceCash(1000_000L);

        var requestedCash = 116_870L;

        Map<Banknote,Integer> banknotes = new TreeMap<>((k1, k2) ->
                k2.getDenomination().getValue().compareTo(k1.getDenomination().getValue()));
        banknotes.put(new Banknote(FIVE_THOUSAND), 100);
        banknotes.put(new Banknote(ONE_THOUSAND), 100);
        banknotes.put(new Banknote(FIVE_HUNDRED), 100);
        banknotes.put(new Banknote(ONE_HUNDRED), 100);
        banknotes.put(new Banknote(FIFTY), 100);
        banknotes.put(new Banknote(TEN), 100);

        TakingAlgorithm takingAlgorithm = new FirstTakingAlgorithm();

        GivingAlgorithm givingAlgorithm = new FirstGivingAlgorithm(moneyStorage);

        FirstAtm firstAtm = new FirstAtm(takingAlgorithm, givingAlgorithm, moneyStorage);
        final Map<Denomination, Cell> denominationCellMap = firstAtm.takeBanknotes(banknotes);
        final Map<Banknote, Integer> banknoteIntegerMap = firstAtm.giveBanknotes(requestedCash);
        System.out.println("ddddddddddddddddddddddddddddd " + banknoteIntegerMap);
        final long balanceCash = firstAtm.getBalanceCash();
    }
}
