package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ChartController {

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
    void buttonclickedstop() {



    }

    @FXML
    void buttonclicked() {

        production.startProduction();

    }

}
