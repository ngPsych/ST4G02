package com.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApp extends Application {

    private static AnnotationConfigApplicationContext applicationContext;

    @Override
    public void init() {


        getApplicationContext().scan("com.project");

    }

    public static AnnotationConfigApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            applicationContext = (AnnotationConfigApplicationContext) new SpringApplicationBuilder(CoreApplication.class).run();
        }
        return applicationContext; //indeholder alle beans
    }

    @Override
    public void start(Stage stage) {

        applicationContext.publishEvent(new StageReadyEvent(stage));

    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }


    // inner class
    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }





}
