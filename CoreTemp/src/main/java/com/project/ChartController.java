package com.project;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

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

    Thread wareHouseStateThread;
    Thread inventoryThread;
    Thread agvStatusThread;
    Thread assemblyLabelThread;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        healthchecklabel.setText("");

        // setting textarea properties
        inventoryprint.setEditable(false);
        inventoryprint.isWrapText();
        inventoryprint.setWrapText(true);

        // Starting the that updates the inventory textarea
        InventoryUpdater inventoryUpdater = new InventoryUpdater(100, inventoryprint);
        inventoryThread = new Thread(inventoryUpdater);
        inventoryThread.start();


        // Starting thread that updates the warehouse state label
        WarehouseLabelUpdater labelChecker = new WarehouseLabelUpdater(100, warehouseprint);
        wareHouseStateThread = new Thread(labelChecker);
        wareHouseStateThread.start();

        // Starting thread that updates the agv label
        AGVStatusUpdater agvStatusUpdater = new AGVStatusUpdater(100, agvprint, healthchecklabel, processprint);
        agvStatusThread = new Thread(agvStatusUpdater);
        agvStatusThread.start();

        // Starting thread that updates assembly  label
        AssemblyLabelUpdater assemblyLabelUpdater = new AssemblyLabelUpdater(100, assemblyprint);
        assemblyLabelThread = new Thread(assemblyLabelUpdater);
        assemblyLabelThread.start();

        // sets connection labels
        agvstatus.setText(production.agvConnectionCheck());
        warehousestatus.setText(production.warehouseConnectionCheck());
        assemblystatus.setText(production.assemblyConnectionCheck());


    }

    @FXML
    void stopButton() {

        production.pauseProduction();
        System.out.println("It will stop, wait for it");

    }


    @FXML
    void startButton() {

        new Thread(() -> {
            production.startProduction();
        }).start();

    }


    public class WarehouseLabelUpdater implements Runnable {

        private int sleepTime;
        private boolean running;
        private Label label;

        public WarehouseLabelUpdater(int sleepTime, Label label) {

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
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted: " + Thread.currentThread());
                        running = false;
                    }
                }

            }
        }
    }

    public class InventoryUpdater implements Runnable {

        private int sleepTime;
        private boolean running;
        private TextArea textArea;


        public InventoryUpdater(int sleepTime, TextArea textArea) {

            this.sleepTime = sleepTime;
            this.textArea = textArea;

        }

        @Override
        public void run() {
            running = true;
            System.out.println("Thread started: " + Thread.currentThread());

            while (running) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        textArea.setText(production.prodInventory());

                    }
                });
                synchronized (this) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted: " + Thread.currentThread());
                        running = false;
                    }
                }

            }
        }
    }

    public class AGVStatusUpdater implements Runnable {

        private int sleepTime;
        private boolean running;
        private Label label;
        private Label label2;
        private TextArea textArea;

        public AGVStatusUpdater(int sleepTime, Label label, Label label2, TextArea textArea) {
            this.sleepTime = sleepTime;
            this.label = label;
            this.label2 = label2;
            this.textArea = textArea;
        }

        @Override
        public void run() {
            running = true;
            System.out.println("Thread started: " + Thread.currentThread());

            while (running) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        label.setText(production.agvGetStatus());

                        if (label.getText().contains("PickAssemblyOperation")) {
                            label2.setText(production.assemblyHealth());
                        }

                        if (label.getText().contains("PutWarehouseOperation")) {
                            label2.setText("");
                        }

                    }
                });

                synchronized (this) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted: " + Thread.currentThread());
                        running = false;
                    }
                }
            }
        }
    }

    public class AssemblyLabelUpdater implements Runnable {

        private int sleepTime;
        private boolean running;
        private Label label;

        public AssemblyLabelUpdater(int sleepTime, Label label) {
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

                        label.setText(production.assemblyLabelMain());

                    }
                });

                synchronized (this) {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted: " + Thread.currentThread());
                        running = false;
                    }
                }
            }
        }
    }


}
