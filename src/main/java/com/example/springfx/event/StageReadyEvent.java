package com.example.springfx.event;

import com.example.springfx.controller.BaseController;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {

    private final Class<? extends BaseController> controller;

    public StageReadyEvent(Stage stage) {
        super(stage);
        controller = null;
    }

    public <CONTROLLER extends BaseController> StageReadyEvent(Stage stage, Class<CONTROLLER> controller) {
        super(stage);
        this.controller = controller;
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }

    public Class<? extends BaseController> getController() {
        return controller;
    }
}
