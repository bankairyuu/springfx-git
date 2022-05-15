package com.example.springfx.controller;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@FxmlView("/fxml/SecondScreen.fxml")
@PropertySource("classpath:navigation_app-secondary.properties")
public class SecondScreenController extends BaseController {

    public Label lblChosenFile;
    @Value("${app-secondary.navi.secondscreen.mainscreen}")
    private String naviMainScreen;

    public SecondScreenController(Navigator navigator, StageStore stageStore) {
        super(navigator, stageStore);
    }

    public void back(ActionEvent actionEvent) {
        navigateTo(naviMainScreen);
    }

    public void openInfoAlert(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION, "This is an information alert").showAndWait();
    }

    public void openFileChooserDialog(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        File file = fileChooser.showOpenDialog(getStageStore().getCurrentStage());
        if (file != null && file.exists()){
            lblChosenFile.setText(file.getName());
        }
    }
}
