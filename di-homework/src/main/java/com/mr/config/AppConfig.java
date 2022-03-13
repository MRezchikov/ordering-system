package com.mr.config;

import com.mr.appcontainer.api.AppComponent;
import com.mr.appcontainer.api.AppComponentsContainerConfig;
import com.mr.services.*;

@AppComponentsContainerConfig(order = 1)
public class AppConfig {

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

    @AppComponent(order = 2, name = "gameProcessor")
    public GameProcessor gameProcessor(IOService ioService,
                                       PlayerService playerService,
                                       EquationPreparer equationPreparer) {
        System.out.println("gameProcessor was invoked");
        return new GameProcessorImpl(ioService, equationPreparer, playerService);
    }

    @AppComponent(order = 0, name = "ioService")
    public IOService ioService() {
        System.out.println("ioService was invoked");
        return new IOServiceConsole(System.out, System.in);
    }

}
