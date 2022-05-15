package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.application.Platform;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {
    private final Navigator navigator;
    private final StageStore stageStore;

    protected static final String ERROR_SCREEN = "errorScreen";

    public BaseController(Navigator navigator, StageStore stageStore) {
        this.navigator = navigator;
        this.stageStore = stageStore;
    }

    protected void navigateTo(String controllerClass){
        Platform.runLater(() -> navigator.navigateTo(controllerClass));
    }

    protected void navigateBack() {
        Platform.runLater(navigator::navigateBack);
    }

    protected StageStore getStageStore(){
        return stageStore;
    }
}
