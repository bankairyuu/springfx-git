package com.example.springfx.navigator;

import com.example.springfx.config.ConfigKey;
import com.example.springfx.config.GlobalConfig;
import com.example.springfx.controller.BaseController;
import com.example.springfx.controller.MainWindowController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FxNavigator {

    private final String applicationTitle;
    private final FxWeaver fxWeaver;

    public FxNavigator(@Value("${spring.application.ui.title}") String applicationTitle,
                       FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
    }

    public <CONTROLLER extends BaseController> void navigate(Class<CONTROLLER> controllerClass, Stage stage){
        GlobalConfig.set(ConfigKey.CURRENT_STAGE, stage);
        if (controllerClass == null) {
            stage.setScene(new Scene(fxWeaver.loadView(MainWindowController.class), 800, 600));
        } else {
            stage.setScene(new Scene(fxWeaver.loadView(controllerClass), 800, 600));
        }
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
