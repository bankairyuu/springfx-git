package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/MainWindow.fxml")
public class MainWindowController extends BaseController {

    public MainWindowController(Navigator navigator, StageStore stageStore) {
        super(navigator, stageStore);
    }

    public void navigateToSecondScreen(ActionEvent actionEvent) {
        navigate(SecondScreenController.class);
    }
}
