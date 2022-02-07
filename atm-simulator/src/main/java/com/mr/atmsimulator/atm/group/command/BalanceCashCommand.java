package com.mr.atmsimulator.atm.group.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ExecutorData;
import com.mr.atmsimulator.atm.group.CommandAtm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BalanceCashCommand implements CommandAtm {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceCashCommand.class);

    @Override
    public void execute(ExecutorData executorData) {
        List<Atm> atms = executorData.getAtms();
        for (Atm atm : atms) {
            long balanceCash = atm.getBalanceCash();
            LOGGER.info("Balance cash into atm: {}", balanceCash);
        }
    }
}
