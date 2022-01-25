package com.mr.atmsimulator;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.strategy.FirstGivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.FirstTakingAlgorithm;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.service.AtmStorageService;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AtmApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtmApplication.class);

    public static void main(String[] args) {

        MoneyStorage moneyStorage = new MoneyStorage();
        moneyStorage.setBalanceCash(1000_000L);

        var requestedCash = 116_870L;

        List<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(20, 5000));
        banknotes.add(new Banknote(3, 1000));
        banknotes.add(new Banknote(1, 500));
        banknotes.add(new Banknote(3, 100));
        banknotes.add(new Banknote(1, 50));
        banknotes.add(new Banknote(3, 10));

        TakingAlgorithm takingAlgorithm = new FirstTakingAlgorithm(banknotes);

        GivingAlgorithm givingAlgorithm = new FirstGivingAlgorithm(requestedCash, moneyStorage);

        Atm atm = new Atm(takingAlgorithm, givingAlgorithm, moneyStorage);

        AtmStorageService atmStorageService = new AtmStorageService(atm, moneyStorage);

        var resultSumOfAcceptedBanknotes = atmStorageService.processAcceptedBanknotes(banknotes);
        LOGGER.info("resultSumOfAcceptedBanknotes {} = ", resultSumOfAcceptedBanknotes);
        LOGGER.info("General atm balance {} = ", moneyStorage.getBalanceCash());

        atmStorageService.processGivenBanknotes();
        var cashBalance = atmStorageService.getBalanceCash();
        LOGGER.info("cashBalance {} = ", cashBalance);
    }
}
