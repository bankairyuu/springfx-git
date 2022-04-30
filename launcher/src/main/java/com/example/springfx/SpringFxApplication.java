package com.example.springfx;

import com.example.springfx.navigator.Navigator;
import com.example.springfx.stagestore.StageStore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringFxApplication extends Application {

    private ConfigurableApplicationContext configurableApplicationContext;

    public static void main(String[] args) {
        Application.launch(SpringFxApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) {
        configurableApplicationContext = SpringApplication.run(SpringFxApplication.class, getParameters().getRaw().toArray(new String[0]));

        StageStore stageStore = configurableApplicationContext.getBean("stageStore", StageStore.class);
        stageStore.setCurrentStage(primaryStage);

        Navigator navigator = configurableApplicationContext.getBean("navigator", Navigator.class);
        navigator.navigateToMainScreen(primaryStage);
    }

    @Override
    public void stop() {
        configurableApplicationContext.close();
        Platform.exit();
    }

}
