package com.mr.config;

import com.mr.appcontainer.api.AppComponent;
import com.mr.appcontainer.api.AppComponentsContainerConfig;
import com.mr.services.EquationPreparer;
import com.mr.services.GameProcessor;
import com.mr.services.GameProcessorImpl;
import com.mr.services.IOService;
import com.mr.services.PlayerService;

@AppComponentsContainerConfig(order = 1)
public class AppConfig2 {

    @AppComponent(order = 2, name = "gameProcessor")
    public GameProcessor gameProcessor(IOService ioService,
                                       PlayerService playerService,
                                       EquationPreparer equationPreparer) {
        System.out.println("gameProcessor was invoked");
        return new GameProcessorImpl(ioService, equationPreparer, playerService);
    }
}
