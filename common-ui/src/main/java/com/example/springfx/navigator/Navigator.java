package com.example.springfx.navigator;

import com.example.springfx.composer.Composer;
import com.example.springfx.config.navigation.NavigationEntryStore;
import com.example.springfx.controller.BaseController;
import com.example.springfx.controller.ErrorScreenController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class Navigator<CONTROLLER extends BaseController> {
    private final Composer composer;
    private String mainScreen;
    private Stack<Pair<Class<CONTROLLER>, Scene>> sceneStack;

    private final NavigationEntryStore navigationEntryStore;

    public Navigator(@Value("${springfx.mainscreen}") String mainScreen,
                     Composer composer,
                     NavigationEntryStore navigationEntryStore) {
        this.mainScreen = mainScreen;
        this.composer = composer;
        this.navigationEntryStore = navigationEntryStore;
    }

    public void navigate(Class<CONTROLLER> controllerClass) {
        controllerClass = controllerClass == null
                ? (Class<CONTROLLER>) ErrorScreenController.class
                : controllerClass;

        if (sceneStack.size() > 2 && sceneStack.elementAt(sceneStack.size() - 2).getKey().equals(controllerClass)) {
            sceneStack.pop();
            sceneStack.pop();
        }

        Pair<Class<CONTROLLER>, Scene> scenePair = composer.compose(controllerClass);
        sceneStack.push(scenePair);
        composer.display(scenePair);

    }

    public void navigateTo(String targetScreen) {
        Pair<Class<CONTROLLER>, Scene> scenePair = composer.composeController(navigationEntryStore.getController(targetScreen));
        sceneStack.push(scenePair);
        composer.display(scenePair);
    }

    public void navigateToMainScreen(Stage primaryStage) {
        validateMainScreenName();

        Pair<Class<CONTROLLER>, Scene> scenePair = composer.composeMain(mainScreen);
        if (sceneStack == null) {
            sceneStack = new Stack<>();
            sceneStack.push(scenePair);
        }

        composer.display(scenePair);
    }

    /**
     * The spring handles the beans with lowercase starting character.
     * Therefor it is important, to convert the starting character to lowercase, in case it is added as uppercase
     * starting character in the application.properties
     */
    private void validateMainScreenName() {
        if (Character.isUpperCase(mainScreen.charAt(0))) {
            StringBuilder mainScreenNameBuilder = new StringBuilder(mainScreen);
            mainScreenNameBuilder.setCharAt(0, Character.toLowerCase(mainScreen.charAt(0)));
            mainScreen = mainScreenNameBuilder.toString();
        }
    }

    public void navigateBack() {
        Pair<Class<CONTROLLER>, Scene> pop = sceneStack.pop();
        if (pop.getKey().equals(ErrorScreenController.class)) {
            pop = sceneStack.peek();
        }

        composer.display(pop);
    }
}
