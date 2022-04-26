package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/fxml/SecondScreen.fxml")
public class SecondScreenController extends BaseController{
    public SecondScreenController(Navigator navigator) {
        super(navigator);
    }

    public void back(ActionEvent actionEvent) {
        navigate(MainWindowController.class);
    }
}
