package com.mr.atmsimulator.atm.command.executor;

import com.mr.atmsimulator.atm.Atm;
import com.mr.atmsimulator.atm.group.AtmGroup;
import com.mr.atmsimulator.atm.command.CommandAtm;

import java.util.ArrayList;
import java.util.List;

public class Executor {

    private final Atm atm;
    private final List<CommandAtm> commandAtms = new ArrayList<>();

    public Executor(Atm atm) {
        this.atm = atm;
    }

    public void addCommand(CommandAtm commandAtm) {
        commandAtms.add(commandAtm);
    }

    public void executeCommands() {
        for (CommandAtm commandAtm : commandAtms) {
            commandAtm.execute(atm);
        }
    }
}
