package com.example.springfx.navigator;

import com.example.springfx.controller.BaseController;
import com.example.springfx.controller.ErrorScreenController;
import com.example.springfx.stagestore.StageStore;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class Navigator<CONTROLLER extends BaseController> {

    private final String applicationTitle;
    private final FxWeaver fxWeaver;
    private final StageStore stageStore;
    private String mainScreen;
    private final ConfigurableApplicationContext configurableApplicationContext;
    private Stack<Pair<Class<CONTROLLER>, Scene>> sceneStack;

    public Navigator(@Value("${spring.application.ui.title}") String applicationTitle,
                     FxWeaver fxWeaver,
                     StageStore stageStore,
                     @Value("${springfx.mainscreen}") String mainScreen,
                     ConfigurableApplicationContext configurableApplicationContext) {
        this.applicationTitle = applicationTitle;
        this.fxWeaver = fxWeaver;
        this.stageStore = stageStore;
        this.mainScreen = mainScreen;
        this.configurableApplicationContext = configurableApplicationContext;
    }

    public void navigate(Class<CONTROLLER> controllerClass) {
        Stage stage = stageStore.getCurrentStage();
        stageStore.setCurrentStage(stage);

        controllerClass = controllerClass == null
                ? (Class<CONTROLLER>) ErrorScreenController.class
                : controllerClass;

        Scene scene = new Scene(fxWeaver.loadView(controllerClass), 800, 600);

        if (sceneStack.size() > 2 && sceneStack.elementAt(sceneStack.size() - 2).getKey().equals(controllerClass)) {
                sceneStack.pop();
                sceneStack.pop();
        }

        sceneStack.push(new Pair<>(controllerClass, scene));
        stage.setScene(scene);
        stage.setTitle(applicationTitle);
        stage.show();
    }

    public void navigateToMainScreen(Stage primaryStage) {
        stageStore.setCurrentStage(primaryStage);

        /**
         * The spring handles the beans with lowercase starting character.
         * Therefor it is important, to convert the starting character to lowercase, in case it is added as uppercase
         * starting character in the application.properties
         */
        if (Character.isUpperCase(mainScreen.charAt(0))) {
            StringBuilder mainScreenNameBuilder = new StringBuilder(mainScreen);
            mainScreenNameBuilder.setCharAt(0, Character.toLowerCase(mainScreen.charAt(0)));
            mainScreen = mainScreenNameBuilder.toString();
        }

        Class<CONTROLLER> mainScreenClass = (Class<CONTROLLER>) configurableApplicationContext.getBean(mainScreen + "Controller", BaseController.class).getClass();
        Scene mainScene = new Scene(fxWeaver.loadView(mainScreenClass), 800, 600);
        if (sceneStack == null) {
            sceneStack = new Stack<>();
            sceneStack.push(new Pair<>(mainScreenClass, mainScene));
        }
        primaryStage.setScene(mainScene);
        primaryStage.setTitle(applicationTitle);
        primaryStage.show();
    }

    public void navigateBack() {
        Pair<Class<CONTROLLER>, Scene> pop = sceneStack.pop();
        if (pop.getKey().equals(ErrorScreenController.class)) {
            pop = sceneStack.peek();
        }

        Scene scene = pop.getValue();
        Stage stage = stageStore.getCurrentStage();

        stage.setScene(scene);
        stage.setTitle(applicationTitle);
        stage.show();
    }
}
