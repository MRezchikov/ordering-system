package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.atm.denomination.Denomination;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;

public class FirstGivingAlgorithm implements GivingAlgorithm {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstGivingAlgorithm.class);

    @Override
    public Map<Banknote, Integer> giveBanknotes(long requestedCash, Map<Denomination, Cell> denominationCellMap) {

        LOGGER.info("Giving banknotes: {}", requestedCash);

        return processBanknotes(requestedCash, denominationCellMap);
    }

    private Map<Banknote, Integer> processBanknotes(long money, Map<Denomination, Cell> denominationCellMap) {

        Map<Banknote, Integer> banknotes = new LinkedHashMap<>();

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
