package com.mr.atmsimulator.atm.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ListenerAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalanceCashCommand implements CommandAtm {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceCashCommand.class);

    @Override
    public void execute(Atm atm) {
        long balanceCash = atm.getBalanceCash();
        LOGGER.info("Atm balance cash {}", balanceCash);
    }
}
