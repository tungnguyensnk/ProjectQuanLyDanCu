package com.project1.Main;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Alert implements Initializable {
    public Label textAlert;

    public void setTextAlert(String s) {
        textAlert.setText(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
