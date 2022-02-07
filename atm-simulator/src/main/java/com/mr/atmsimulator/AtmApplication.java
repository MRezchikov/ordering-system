package com.mr.atmsimulator;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.FirstAtm;
import com.mr.atmsimulator.atm.observer.ConsumerA;
import com.mr.atmsimulator.atm.observer.ConsumerB;
import com.mr.atmsimulator.atm.observer.ConsumerC;
import com.mr.atmsimulator.atm.savepoint.History;
import com.mr.atmsimulator.atm.strategy.FirstGivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.FirstTakingAlgorithm;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
import com.mr.atmsimulator.storage.MoneyStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
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

        TakingAlgorithm takingAlgorithm = new FirstTakingAlgorithm();
        GivingAlgorithm givingAlgorithm = new FirstGivingAlgorithm();
        MoneyStorage moneyStorage = new MoneyStorage(takingAlgorithm, givingAlgorithm);

        var requestedCash = 116_870L;

        Map<Banknote, Integer> banknotes = new TreeMap<>((k1, k2) ->
                k2.getDenomination().getValue().compareTo(k1.getDenomination().getValue()));
        banknotes.put(new Banknote(FIVE_THOUSAND), 100);
        banknotes.put(new Banknote(ONE_THOUSAND), 100);
        banknotes.put(new Banknote(FIVE_HUNDRED), 100);
        banknotes.put(new Banknote(ONE_HUNDRED), 100);
        banknotes.put(new Banknote(FIFTY), 100);
        banknotes.put(new Banknote(TEN), 100);

        FirstAtm firstAtm = new FirstAtm(moneyStorage);
        History history = new History(LocalDateTime::now);

        history.createSavePoint(firstAtm);

        LOGGER.info("Before first save point {} ", firstAtm.getBalanceCash());

        firstAtm.takeBanknotes(banknotes);
        firstAtm.giveBanknotes(requestedCash);

        history.createSavePoint(firstAtm);
        LOGGER.info("First savepoint was created");

        var requestedCash2 = 1_000_000L;

        firstAtm.giveBanknotes(requestedCash2);
        LOGGER.info("Before second savepoint {}", firstAtm.getBalanceCash());

        history.createSavePoint(firstAtm);
        LOGGER.info("Second save point was created");

        var requestedCash3 = 2_584_180L;
        firstAtm.giveBanknotes(requestedCash3);
        LOGGER.info("Before third savepoint {}", firstAtm.getBalanceCash());

        history.createSavePoint(firstAtm);
        LOGGER.info("Third save point was created");

        Atm atm = history.restoreAtm();
        LOGGER.info("Restore atm from third STATE = {} ", atm.getBalanceCash());
        Atm atm1 = history.restoreAtm();
        LOGGER.info("Restore atm from second STATE = {} ", atm1.getBalanceCash());
        Atm atm2 = history.restoreAtm();
        LOGGER.info("Restore atm from first STATE = {} ", atm2.getBalanceCash());
        Atm atm3 = history.restoreAtm();
        LOGGER.info("Restore atm from zero STATE = {} ", atm3.getBalanceCash());

        ConsumerA consumerA = new ConsumerA();
        ConsumerB consumerB = new ConsumerB();
        ConsumerC consumerC = new ConsumerC();

        firstAtm.addListener(consumerA);
        firstAtm.addListener(consumerB);
        firstAtm.addListener(consumerC);

        int i = 0;
        while (i < 10) {
            firstAtm.giveBanknotes(requestedCash2);
            i++;
        }

        LOGGER.info("MONEY IS OUT!!!");
    }
}
