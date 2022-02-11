package com.mr.atmsimulator.atm.command;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.data.ListenerAttribute;

public interface CommandAtm {

    void execute(Atm atm);
}
