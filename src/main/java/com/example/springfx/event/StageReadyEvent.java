package com.example.springfx.event;

import com.example.springfx.controller.BaseController;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {

    private final Class<? extends BaseController> controllerClass;

    public StageReadyEvent(Stage stage) {
        super(stage);
        controllerClass = null;
    }

    public <CONTROLLER extends BaseController> StageReadyEvent(Stage stage, Class<CONTROLLER> controllerClass) {
        super(stage);
        this.controllerClass = controllerClass;
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }

    public Class<? extends BaseController> getControllerClass() {
        return controllerClass;
    }
}
