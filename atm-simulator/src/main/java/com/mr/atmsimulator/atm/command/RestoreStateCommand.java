package com.mr.atmsimulator.atm.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.savepoint.History;

public class RestoreStateCommand implements CommandAtm {

    @Override
    public void execute(Atm atm) {
        atm.getHistory().restoreAtm();
    }
}
