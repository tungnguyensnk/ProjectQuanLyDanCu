package com.project1.Case1;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChinhSua implements Initializable {
    public AnchorPane root;
    public Label soHoKhauLabel;
    public Label hoTenChuLabel;
    public Label diaChiLabel;
    public Label ghiChuLabel;
    public TextField soHoKhauField;
    public TextField hoTenChuField;
    public TextField diaChiField;
    public TextArea ghiChiField;

    public void changeMode(MouseEvent mouseEvent) {
    }

    public void tat(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        soHoKhauField.setDisable(true);
        soHoKhauField.setVisible(false);
    }
}
