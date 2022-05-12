package com.project.coretemp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import com.project.coretemp.SpringApp.StageReadyEvent;

import org.springframework.core.io.Resource;

import java.io.IOException;


@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

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
    public void onApplicationEvent(StageReadyEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());

            // Denne giver runtime exeption
            //fxmlLoader.setControllerFactory(aClasa -> applicationContext.getBean(aClass));
            fxmlLoader.setControllerFactory(AssemblyConnect -> applicationContext.getBean(AssemblyConnect.class));
            Parent parent = fxmlLoader.load();

            Stage stage = event.getStage();

            stage.setScene(new Scene(parent, 800, 600));
            stage.setTitle(applicationTitle);

            stage.show();

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }
}
