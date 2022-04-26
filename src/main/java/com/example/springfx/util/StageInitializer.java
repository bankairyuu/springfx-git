package com.example.springfx.util;

import com.example.springfx.config.ConfigKey;
import com.example.springfx.config.GlobalConfig;
import com.example.springfx.controller.MainWindowController;
import com.example.springfx.event.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final String applicationTitle;
    private final FxWeaver fxWeaver;

    private Stage currentStage = null;

    private Stack<Stage> stageStack = new Stack<>();

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            FxWeaver fxWeaver) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        currentStage = event.getStage();
        GlobalConfig.set(ConfigKey.CURRENT_STAGE, currentStage);
        if (event.getController() == null) {
            currentStage.setScene(new Scene(fxWeaver.loadView(MainWindowController.class), 800, 600));
        } else {
            currentStage.setScene(new Scene(fxWeaver.loadView(event.getController()), 800, 600));
        }
        currentStage.setTitle(applicationTitle);
        currentStage.show();
    }
}
