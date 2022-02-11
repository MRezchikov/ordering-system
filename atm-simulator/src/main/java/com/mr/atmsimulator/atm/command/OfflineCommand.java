package com.mr.atmsimulator.atm.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ListenerAttribute;

public class OfflineCommand implements CommandAtm {

    @Override
    public void execute(Atm atm) {
        atm.setOnline(false);
    }
}
