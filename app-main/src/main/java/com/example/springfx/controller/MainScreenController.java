package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/MainScreen.fxml")
public class MainScreenController extends BaseController {

    public MainScreenController(Navigator navigator, StageStore stageStore) {
        super(navigator, stageStore);
    }

    public void navigateToSecondScreen(ActionEvent actionEvent) {
        navigateTo("secondScreen");
    }

    public void navigateToErrorScreen(ActionEvent actionEvent) {
        navigate(null);
    }
}
