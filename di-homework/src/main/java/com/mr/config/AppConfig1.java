package com.mr.config;

import com.mr.appcontainer.api.AppComponent;
import com.mr.appcontainer.api.AppComponentsContainerConfig;
import com.mr.services.EquationPreparer;
import com.mr.services.EquationPreparerImpl;
import com.mr.services.GameProcessor;
import com.mr.services.GameProcessorImpl;
import com.mr.services.IOService;
import com.mr.services.IOServiceConsole;
import com.mr.services.PlayerService;
import com.mr.services.PlayerServiceImpl;

@AppComponentsContainerConfig(order = 1)
public class AppConfig1 {

    @AppComponent(order = 0, name = "equationPreparer")
    public EquationPreparer equationPreparer(){
        System.out.println("equationPreparer was invoked");
        return new EquationPreparerImpl();
    }

    @AppComponent(order = 1, name = "playerService")
    public PlayerService playerService(IOService ioService) {
        System.out.println("playerService was invoked");
        return new PlayerServiceImpl(ioService);
    }

    @AppComponent(order = 0, name = "ioService")
    public IOService ioService() {
        System.out.println("ioService was invoked");
        return new IOServiceConsole(System.out, System.in);
    }
}
