package com.example.springfx.controller;

import com.example.springfx.config.ConfigKey;
import com.example.springfx.config.GlobalConfig;
import com.example.springfx.navigator.FxNavigator;
import javafx.application.Platform;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {
    private FxNavigator fxNavigator;

    public BaseController(FxNavigator fxNavigator) {
        this.fxNavigator = fxNavigator;
    }

    protected <CONTROLLER extends BaseController> void navigate(Class<CONTROLLER> controllerClass) {
        Platform.runLater(() -> fxNavigator.navigate(controllerClass, GlobalConfig.get(ConfigKey.CURRENT_STAGE)));
    }


}
