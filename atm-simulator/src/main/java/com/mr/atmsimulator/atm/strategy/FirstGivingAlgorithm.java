package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstGivingAlgorithm implements GivingAlgorithm {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstGivingAlgorithm.class);

    private final MoneyStorage moneyStorage;

    public FirstGivingAlgorithm(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<Banknote, Integer> giveBanknotes(long requestedCash) {

        LOGGER.info("Giving banknotes: {}", requestedCash);

        return processBanknotes(requestedCash);
    }

    private Map<Banknote, Integer> processBanknotes(long money) {

        Map<Banknote, Integer> banknotes = new LinkedHashMap<>();

        final Map<Denomination, Cell> denominationCellMap = moneyStorage.getDenominationCellMap();

        var tempMoney = money;

        for (Map.Entry<Denomination, Cell> entry : denominationCellMap.entrySet()) {

            Integer denomination = entry.getKey().getValue();
            var takenBanknoteDenomination = (int) (tempMoney - tempMoney % denomination);

            var count = takenBanknoteDenomination / denomination;

            Cell cell = entry.getValue();
            cell.setCounter(cell.getCounter() - count);

            tempMoney %= denomination;
            banknotes.put(new Banknote(entry.getKey()), count);
        }

        return banknotes;
    }
}
