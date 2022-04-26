package com.example.springfx;

import com.example.springfx.navigator.FxNavigator;
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
	public void start(Stage primaryStage) throws Exception {
		configurableApplicationContext = SpringApplication.run(SpringFxApplication.class, getParameters().getRaw().toArray(new String[0]));
		FxNavigator fxNavigator = configurableApplicationContext.getBean("fxNavigator", FxNavigator.class);
		fxNavigator.navigate(null, primaryStage);
	}

	@Override
	public void stop() throws Exception {
		configurableApplicationContext.close();
		Platform.exit();
	}

}
