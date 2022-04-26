package com.example.springfx.controller;

import com.example.springfx.navigator.FxNavigator;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/MainWindow.fxml")
public class MainWindowController extends BaseController {

    public MainWindowController(FxNavigator fxNavigator) {
        super(fxNavigator);
    }

    public void navigateToSecondScreen(ActionEvent actionEvent) {
        navigate(SecondScreenController.class);
    }
}
