package com.example.springfx.navigator;

import com.example.springfx.controller.BaseController;
import com.example.springfx.controller.MainWindowController;
import com.example.springfx.stagestore.StageStore;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Navigator {

    private final String applicationTitle;
    private final FxWeaver fxWeaver;
    private final StageStore stageStore;

    public Navigator(@Value("${spring.application.ui.title}") String applicationTitle,
                     FxWeaver fxWeaver, StageStore stageStore) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
        this.stageStore = stageStore;
    }

    public <CONTROLLER extends BaseController> void navigate(Class<CONTROLLER> controllerClass, Stage stage){
        stageStore.setCurrentStage(stage);
        if (controllerClass == null) {
            stage.setScene(new Scene(fxWeaver.loadView(MainWindowController.class), 800, 600));
        } else {
            stage.setScene(new Scene(fxWeaver.loadView(controllerClass), 800, 600));
        }
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
