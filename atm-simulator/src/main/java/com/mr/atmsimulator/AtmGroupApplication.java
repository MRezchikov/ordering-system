package com.mr.atmsimulator;

public class AtmGroupApplication {

    public static void main(String[] args) {

/*        TakingAlgorithm takingAlgorithm = new FirstTakingAlgorithm();
        GivingAlgorithm givingAlgorithm = new FirstGivingAlgorithm();

        AtmGroup atmGroup = new AtmGroup();
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));
        atmGroup.addAtm(new FirstAtm(new MoneyStorage(takingAlgorithm, givingAlgorithm)));

        AtmAttribute atmAttribute = new AtmAttribute(atmGroup.getAtmGroup());

        History history = new History(LocalDateTime::now);

        atmAttribute.setHistory(history);

        atmGroup.setExecutorData(atmAttribute);
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
        executor.executeCommands();*/
    }
}
