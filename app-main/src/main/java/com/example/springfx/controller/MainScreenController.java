package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/MainScreen.fxml")
@PropertySource("classpath:navigation_app-main.properties")
public class MainScreenController extends BaseController {

    @Value("${app-main.navi.mainscreen.secondscreen}")
    private String naviSecondScreen;

    public MainScreenController(Navigator navigator, StageStore stageStore) {
        super(navigator, stageStore);
    }

    public void navigateToSecondScreen(ActionEvent actionEvent) {
        navigateTo(naviSecondScreen);
    }

    public void navigateToErrorScreen(ActionEvent actionEvent) {
        navigateTo(ERROR_SCREEN);
    }
}
