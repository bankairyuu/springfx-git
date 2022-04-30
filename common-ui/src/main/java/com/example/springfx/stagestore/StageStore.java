package com.example.springfx.stagestore;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class StageStore {

    private Stage currentStage;

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }
}
