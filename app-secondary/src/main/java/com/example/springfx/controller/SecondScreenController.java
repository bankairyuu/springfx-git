package com.example.springfx.controller;

import com.example.springfx.navigation.SecondaryAppExternalNavigation;
import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/SecondScreen.fxml")
public class SecondScreenController extends BaseController {

    public SecondScreenController(Navigator navigator, StageStore stageStore) {
        super(navigator, stageStore);
    }

    public void back(ActionEvent actionEvent) {
        navigateTo(SecondaryAppExternalNavigation.MAIN_APP_Main_SCREEN.get());
    }
}
