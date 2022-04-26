package com.example.springfx.controller;

import com.example.springfx.config.ConfigKey;
import com.example.springfx.config.GlobalConfig;
import com.example.springfx.navigator.Navigator;
import javafx.application.Platform;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {
    private Navigator navigator;

    public BaseController(Navigator navigator) {
        this.navigator = navigator;
    }

    protected <CONTROLLER extends BaseController> void navigate(Class<CONTROLLER> controllerClass) {
        Platform.runLater(() -> navigator.navigate(controllerClass, GlobalConfig.get(ConfigKey.CURRENT_STAGE)));
    }


}
