package com.mr.atmsimulator.atm.strategy;

import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mr.atmsimulator.util.AtmUtil.getIntegerBanknoteMap;

public class FirstGivingAlgorithm implements GivingAlgorithm {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstGivingAlgorithm.class);

    private final long moneyRequestedAmount;
    private final MoneyStorage moneyStorage;

    public FirstGivingAlgorithm(long moneyRequestedAmount, MoneyStorage moneyStorage) {
        this.moneyRequestedAmount = moneyRequestedAmount;
        this.moneyStorage = moneyStorage;
    }

    @Override
    public List<Banknote> giveBanknotes() {

        LOGGER.info("Giving banknotes: {}", moneyRequestedAmount);

        if (moneyStorage.getBalanceCash() < moneyRequestedAmount) {
            LOGGER.error("Not enough money on your account {}: ", moneyStorage.getBalanceCash());
            throw new RuntimeException("Not enough money on your account " + moneyStorage.getBalanceCash());
        }

        var moneyRemainder = moneyStorage.getBalanceCash() - moneyRequestedAmount;
        moneyStorage.setBalanceCash(moneyRemainder);

        return processBanknotes(moneyRequestedAmount);
    }

    private List<Banknote> processBanknotes(long money) {

        var cells = getIntegerBanknoteMap();

        List<Banknote> banknotes = new ArrayList<>();

        var tempMoney = money;

        for (Map.Entry<Integer, Banknote> entry : cells.entrySet()) {

            Integer denomination = entry.getKey();
            var tookBanknoteDenomination = (int) (tempMoney - tempMoney % denomination);
            var count = tookBanknoteDenomination / denomination;
            if (count == 1) {
                tookBanknoteDenomination = (int) (tempMoney - tempMoney % denomination);
            } else if (count > 1) {
                tookBanknoteDenomination /= count;
            }
            tempMoney %= denomination;
            banknotes.add(new Banknote(count, tookBanknoteDenomination));
        }

        return banknotes;
    }
}
