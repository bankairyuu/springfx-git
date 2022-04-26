package com.example.springfx.navigator;

import com.example.springfx.controller.BaseController;
import com.example.springfx.event.StageReadyEvent;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public final class Navigator {
    private ConfigurableApplicationContext applicationContext;

    public Navigator(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public <CONTROLLER extends BaseController> void navigate(Class<CONTROLLER> controllerClass, Stage stage){
        applicationContext.publishEvent(new StageReadyEvent(stage, controllerClass));
    }
}
