package com.project1.Main;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * tạo cảnh báo
 */
public class Alert implements Initializable {
    public Label textAlert;

    /**
     * set nội dung cảnh báo
     *
     * @param s nội dung
     */
    public void setTextAlert(String s) {
        textAlert.setText(s);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
