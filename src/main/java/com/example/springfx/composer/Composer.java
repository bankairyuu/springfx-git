package com.example.springfx.composer;

import com.example.springfx.controller.BaseController;
import com.example.springfx.stagestore.StageStore;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Composer<CONTROLLER extends BaseController> {
    private final Double height;
    private final Double width;
    private final ConfigurableApplicationContext applicationContext;
    private final String applicationTitle;
    private final StageStore stageStore;
    FxWeaver fxWeaver;

    public Composer(@Value("${springfx.height}") Double height,
                    @Value("${springfx.width}") Double width,
                    FxWeaver fxWeaver,
                    ConfigurableApplicationContext applicationContext,
                    StageStore stageStore,
                    @Value("${spring.application.ui.title}") String applicationTitle) {
        this.height = height;
        this.width = width;
        this.fxWeaver = fxWeaver;
        this.applicationContext = applicationContext;
        this.stageStore = stageStore;
        this.applicationTitle = applicationTitle;
    }

    public Pair<Class<CONTROLLER>, Scene> compose(Class<CONTROLLER> controllerClass) {
        return new Pair<>(controllerClass, new Scene(fxWeaver.loadView(controllerClass), width, height));
    }

    public Pair<Class<CONTROLLER>, Scene> composeMain(String mainScreen) {
        Class<CONTROLLER> mainScreenClass = (Class<CONTROLLER>) applicationContext.getBean(mainScreen + "Controller", BaseController.class).getClass();
        return new Pair<>(mainScreenClass, new Scene(fxWeaver.loadView(mainScreenClass), width, height));
    }

    public void display(Pair<Class<CONTROLLER>, Scene> scenePair) {
        Stage stage = stageStore.getCurrentStage();

        stage.setScene(scenePair.getValue());
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
