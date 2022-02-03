package com.mr.atmsimulator.atm.group.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ExecutorData;
import com.mr.atmsimulator.atm.group.CommandAtm;
import com.mr.atmsimulator.atm.savepoint.History;

import java.util.List;

public class RestoreStateCommand implements CommandAtm {

    @Override
    public void execute(ExecutorData executorData) {
        History history = executorData.getHistory();
        List<Atm> atms = executorData.getAtms();
        atms.forEach(atm -> history.restoreAtm());
    }
}
