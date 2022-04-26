package com.example.springfx;

import com.example.springfx.event.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringFxApplication extends Application{

	private ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		Application.launch(SpringFxApplication.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
	}

	@Override
	public void init() throws Exception {
		super.init();
		applicationContext = new SpringApplicationBuilder(SpringFxApplication.class).run();
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
		Platform.exit();
	}

}
