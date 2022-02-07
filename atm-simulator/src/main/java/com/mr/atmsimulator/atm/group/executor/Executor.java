package com.mr.atmsimulator.atm.group.executor;

import com.mr.atmsimulator.atm.group.AtmGroup;
import com.mr.atmsimulator.atm.group.CommandAtm;

import java.util.ArrayList;
import java.util.List;

public class Executor {

    private final AtmGroup atmGroup;
    private final List<CommandAtm> commandAtms = new ArrayList<>();

    public Executor(AtmGroup atmGroup) {
        this.atmGroup = atmGroup;
    }

    public void addCommand(CommandAtm commandAtm) {
        commandAtms.add(commandAtm);
    }

    public void executeCommands() {
        for (CommandAtm commandAtm : commandAtms) {
            commandAtm.execute(atmGroup.getExecutorData());
        }
    }
}
