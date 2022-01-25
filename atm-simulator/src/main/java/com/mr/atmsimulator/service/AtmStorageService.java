package com.mr.atmsimulator.service;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AtmStorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtmStorageService.class);

    private final Atm atm;
    private final MoneyStorage moneyStorage;

    public AtmStorageService(Atm atm, MoneyStorage moneyStorage) {
        this.atm = atm;
        this.moneyStorage = moneyStorage;
    }

    public long processAcceptedBanknotes(List<Banknote> banknotes) {

        LOGGER.info("Get cells for banknotes");
        List<Cell> cells = moneyStorage.getCells();

        LOGGER.info("Add banknotes to cell");
        banknotes.stream()
                .map(banknote -> new Cell(banknote.getCount(), banknote))
                .forEach(cells::add);

        LOGGER.info("Take banknotes");
        long cashAmount = atm.takeBanknotes();
        Long balanceCash = moneyStorage.getBalanceCash();
        moneyStorage.setBalanceCash(balanceCash + cashAmount);
        moneyStorage.setCells(cells);

        LOGGER.info("Cells: {} ", cells);

        return cashAmount;
    }

    public List<Banknote> processGivenBanknotes() {
        LOGGER.info("Process given banknotes");

        List<Banknote> banknotes = null;

        try {
            banknotes = atm.giveBanknotes();
        } catch (Exception e) {
            LOGGER.error("Not enough money on your account", e);
        }

        LOGGER.info("Given banknotes: {}", banknotes);
        return banknotes;
    }

    public long getBalanceCash() {
        return atm.getBalanceCash();
    }
}
