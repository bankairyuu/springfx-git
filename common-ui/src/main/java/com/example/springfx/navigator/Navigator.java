package com.example.springfx.navigator;

import com.example.springfx.composer.Composer;
import com.example.springfx.controller.BaseController;
import com.example.springfx.controller.ErrorScreenController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class Navigator {
    private final Composer composer;
    private String mainScreen;
    private Stack<Pair<Class<? extends BaseController>, Scene>> sceneStack;

    public Navigator(@Value("${springfx.mainscreen}") String mainScreen,
                     Composer composer) {
        this.mainScreen = mainScreen;
        this.composer = composer;
    }

    public void navigateTo(String targetScreen) {
        Pair<Class<? extends BaseController>, Scene> scenePair = composer.composeController(targetScreen + "Controller");
        sceneStack.push(scenePair);
        composer.display(scenePair);
    }

    public void navigateToMainScreen(Stage primaryStage) {
        validateMainScreenName();

        Pair<Class<? extends BaseController>, Scene> scenePair = composer.composeMain(mainScreen);
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
        Pair<Class<? extends BaseController>, Scene> pop = sceneStack.pop();
        if (pop.getKey().equals(ErrorScreenController.class)) {
            pop = sceneStack.peek();
        }

        composer.display(pop);
    }
}
