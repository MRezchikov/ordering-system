package com.mr.atmsimulator.atm.group.command;

import com.mr.atmsimulator.atm.data.ExecutorData;
import com.mr.atmsimulator.atm.group.CommandAtm;

public class NotificationCommand implements CommandAtm {

    @Override
    public void execute(ExecutorData executorData) {
/*        List<Listener> listeners = executorData.getListeners();
        List<Atm> atms = executorData.getAtms();
        for (Atm atm : atms) {
            for (Listener listener : listeners) {
                atm.addListener(listener);
            }
        }*/
    }
}
