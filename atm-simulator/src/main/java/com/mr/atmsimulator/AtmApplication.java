package com.mr.atmsimulator;

import com.mr.atmsimulator.atm.FirstAtm;
import com.mr.atmsimulator.atm.command.BalanceCashCommand;
import com.mr.atmsimulator.atm.command.OfflineCommand;
import com.mr.atmsimulator.atm.command.OnlineCommand;
import com.mr.atmsimulator.atm.command.RestoreStateCommand;
import com.mr.atmsimulator.atm.command.executor.Executor;
import com.mr.atmsimulator.atm.data.ListenerAttribute;
import com.mr.atmsimulator.atm.group.AtmGroup;
import com.mr.atmsimulator.atm.observer.ConsumerA;
import com.mr.atmsimulator.atm.observer.ConsumerB;
import com.mr.atmsimulator.atm.observer.ConsumerC;
import com.mr.atmsimulator.atm.strategy.FirstGivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.FirstTakingAlgorithm;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.banknote.Banknote;
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

        LOGGER.info("Before first save point {} ", firstAtm.getBalanceCash());

        firstAtm.takeBanknotes(banknotes);

        /*LOGGER.info("First savepoint was created");

        var requestedCash2 = 1_000_000L;

        LOGGER.info("Before second savepoint {}", firstAtm.getBalanceCash());

        LOGGER.info("Second save point was created");

        var requestedCash3 = 2_584_180L;

        LOGGER.info("Before third savepoint {}", firstAtm.getBalanceCash());

        LOGGER.info("Third save point was created");

        ConsumerA consumerA = new ConsumerA(1L);
        ConsumerB consumerB = new ConsumerB(2L);
        ConsumerC consumerC = new ConsumerC(3L);
        ListenerAttribute listenerAttribute1 = new ListenerAttribute(100_000L, 200_000L);
        ListenerAttribute listenerAttribute2 = new ListenerAttribute(200_000L, 300_000L);
        ListenerAttribute listenerAttribute3 = new ListenerAttribute(300_000L, 400_000L);

        firstAtm.addListener(consumerA, listenerAttribute1);
        firstAtm.addListener(consumerB, listenerAttribute2);
        firstAtm.addListener(consumerC, listenerAttribute3);

        int i = 0;
        while (i < 8) {
            firstAtm.giveBanknotes(requestedCash2);
            i++;
        }

        LOGGER.info("MONEY IS OUT!!!");

        Map<Banknote, Integer> banknotes2 = new TreeMap<>((k1, k2) ->
                k2.getDenomination().getValue().compareTo(k1.getDenomination().getValue()));
        banknotes2.put(new Banknote(FIVE_THOUSAND), 1000);
        banknotes2.put(new Banknote(ONE_THOUSAND), 100);
        banknotes2.put(new Banknote(FIVE_HUNDRED), 100);
        banknotes2.put(new Banknote(ONE_HUNDRED), 100);
        banknotes2.put(new Banknote(FIFTY), 100);
        banknotes2.put(new Banknote(TEN), 100);

        firstAtm.takeBanknotes(banknotes2);
        LOGGER.info("CASH {}", firstAtm.getBalanceCash());*/

/*        int i1 = 0;
        while (i1 < 8) {
            firstAtm.giveBanknotes(requestedCash2);
            i1++;
        }*/

        AtmGroup atmGroup = new AtmGroup();
        atmGroup.addAtm(new FirstAtm(moneyStorage));
        atmGroup.addAtm(new FirstAtm(moneyStorage));
        atmGroup.addAtm(new FirstAtm(moneyStorage));
        atmGroup.addAtm(new FirstAtm(moneyStorage));
        atmGroup.addAtm(new FirstAtm(moneyStorage));

        atmGroup.addCashToEachAtm();

        atmGroup.getAtmGroup().forEach(atm -> {
            Executor executor = new Executor(atm);
            executor.addCommand(new OnlineCommand());
            executor.addCommand(new OfflineCommand());
            executor.addCommand(new BalanceCashCommand());
            executor.addCommand(new RestoreStateCommand());
            executor.executeCommands();
        });

    }
}
