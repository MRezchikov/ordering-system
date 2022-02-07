package com.mr.atmsimulator;

import com.mr.atmsimulator.atm.FirstAtm;
import com.mr.atmsimulator.atm.data.ExecutorData;
import com.mr.atmsimulator.atm.group.AtmGroup;
import com.mr.atmsimulator.atm.group.command.BalanceCashCommand;
import com.mr.atmsimulator.atm.group.command.OfflineCommand;
import com.mr.atmsimulator.atm.group.command.OnlineCommand;
import com.mr.atmsimulator.atm.group.command.RestoreStateCommand;
import com.mr.atmsimulator.atm.group.executor.Executor;
import com.mr.atmsimulator.atm.observer.ConsumerA;
import com.mr.atmsimulator.atm.observer.ConsumerB;
import com.mr.atmsimulator.atm.observer.ConsumerC;
import com.mr.atmsimulator.atm.savepoint.History;
import com.mr.atmsimulator.atm.strategy.FirstGivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.FirstTakingAlgorithm;
import com.mr.atmsimulator.atm.strategy.GivingAlgorithm;
import com.mr.atmsimulator.atm.strategy.TakingAlgorithm;
import com.mr.atmsimulator.storage.MoneyStorage;

import java.time.LocalDateTime;

public class AtmGroupApplication {

    public static void main(String[] args) {

        TakingAlgorithm takingAlgorithm = new FirstTakingAlgorithm();
        GivingAlgorithm givingAlgorithm = new FirstGivingAlgorithm();

        AtmGroup atmGroup = new AtmGroup();
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));

        ExecutorData executorData = new ExecutorData(atmGroup.getAtmGroup());

        History history = new History(LocalDateTime::now);

        executorData.setHistory(history);

        atmGroup.setExecutorData(executorData);
        atmGroup.createSavePoint(history);


        ConsumerA consumerA = new ConsumerA();
        ConsumerB consumerB = new ConsumerB();
        ConsumerC consumerC = new ConsumerC();

        atmGroup.addListener(consumerA);
        atmGroup.addListener(consumerB);
        atmGroup.addListener(consumerC);

        atmGroup.withdrawMoneyFromAllAtms(6_660_000L);

        Executor executor = new Executor(atmGroup);
        executor.addCommand(new OnlineCommand());
        executor.addCommand(new OfflineCommand());
        executor.addCommand(new BalanceCashCommand());
        executor.addCommand(new RestoreStateCommand());
        executor.executeCommands();
    }
}
