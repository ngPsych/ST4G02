package com.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChartController {

    Production production = new Production();

    @FXML
    void buttonclicked() {

        production.startProduction();

    }

}
