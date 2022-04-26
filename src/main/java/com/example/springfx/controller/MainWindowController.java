package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/MainWindow.fxml")
public class MainWindowController extends BaseController {

    public MainWindowController(Navigator navigator) {
        super(navigator);
    }

    public void navigateToSecondScreen(ActionEvent actionEvent) {
        navigate(SecondScreenController.class);
    }
}
