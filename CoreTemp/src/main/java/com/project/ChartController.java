package com.project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {

    Production production = new Production();

    @FXML
    private Label agvprint;

    @FXML
    private Label agvstatus;

    @FXML
    private Label assemblyprint;

    @FXML
    private Label assemblystatus;

    @FXML
    private TextArea inventoryprint;

    @FXML
    private TextArea processprint;

    @FXML
    private Label warehouseprint;

    @FXML
    private Label warehousestatus;

    @FXML
    private Label healthchecklabel;

    @FXML
    private Button startbutton;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        inventoryprint.setEditable(false);
        inventoryprint.isWrapText();
        inventoryprint.setWrapText(true);


        inventoryprint.setText(production.prodInventory());
        warehouseprint.setText(production.stateSetter());

    }

    @FXML
    void buttonclickedstop() {



    }


    Thread wareHouseThread;

    @FXML
    void buttonclicked() {

        production.startProduction();

        inventoryprint.setText(production.prodInventory());

        LabelChecker labelChecker = new LabelChecker(10, warehouseprint);
        wareHouseThread = new Thread(labelChecker);
        wareHouseThread.setDaemon(true);
        wareHouseThread.start();


    }

    public class LabelChecker implements Runnable {


        private long sleepTime;
        private boolean running;
        private Label label;

        public LabelChecker(long sleepTime, Label label) {

            this.sleepTime = sleepTime;
            this.label = label;
        }

        @Override
        public void run() {
            running = true;
            System.out.println("Thread started: " + Thread.currentThread());

            while (running) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        label.setText(production.stateSetter());

                    }
                });

                synchronized (this) {
                    try {
                        //Thread.sleep(sleepTime);
                        wait(sleepTime);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted: " + Thread.currentThread());
                        running = false;
                    }
                }
            }
        }
    }





}
