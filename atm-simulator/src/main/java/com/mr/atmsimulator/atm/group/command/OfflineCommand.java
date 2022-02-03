package com.mr.atmsimulator.atm.group.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ExecutorData;
import com.mr.atmsimulator.atm.group.CommandAtm;

import java.util.List;

public class OfflineCommand implements CommandAtm {

    @Override
    public void execute(ExecutorData executorData) {
        List<Atm> atms = executorData.getAtms();
        for (Atm atm : atms) {
            atm.setOnline(true);
        }
    }
}
