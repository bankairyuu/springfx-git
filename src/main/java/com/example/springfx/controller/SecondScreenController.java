package com.example.springfx.controller;

import com.example.springfx.navigator.FxNavigator;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/SecondScreen.fxml")
public class SecondScreenController extends BaseController{
    public SecondScreenController(FxNavigator fxNavigator) {
        super(fxNavigator);
    }

    public void back(ActionEvent actionEvent) {
        navigate(MainWindowController.class);
    }
}
