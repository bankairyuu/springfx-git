package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/ErrorScreen.fxml")
public class ErrorScreenController extends BaseController {
    public ErrorScreenController(Navigator navigator, StageStore stageStore) {
        super(navigator, stageStore);
    }

    public void onActionNavigateBack(ActionEvent actionEvent) {
        navigateBack();
    }
}
