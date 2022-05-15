package com.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import org.springframework.core.io.Resource;

import java.io.IOException;


@Component
@ComponentScan
public class StageInitializer implements ApplicationListener<SpringApp.StageReadyEvent> {

    @Value("classpath:/chart.fxml")
    private Resource chartResource;

    //registry
    @Autowired
    private AnnotationConfigApplicationContext applicationContext2;

    private String applicationTitle;

    private ApplicationContext applicationContext;


    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext applicationContext) {

        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;

    }


    @Override
    public void onApplicationEvent(SpringApp.StageReadyEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
            Parent parent = fxmlLoader.load();


            // Denne giver runtime exeption
            //fxmlLoader.setControllerFactory(aClasa -> applicationContext.getBean(aClass));
            //fxmlLoader.setControllerFactory(AssemblyConnect -> applicationContext.getBean(AssemblyConnect.class));


            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 800, 600));

            stage.show();

            stage.setTitle(applicationTitle);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}
