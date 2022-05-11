package Controller;

import Controller.appController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("APP");
        stage.setScene(scene);
        stage.show();

        //appControllerBean.init(stage);

        //appControllerBean.loadScreen("/resources/app.fxml");


    }


}
