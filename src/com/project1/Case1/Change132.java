package com.project1.Case1;

import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Change132 implements Initializable {
    public Button troVe;
    public ChoiceBox soHoKhau1Choice;
    public ChoiceBox hoTen1Choice;
    public DatePicker ngayDiPick;
    public TextField noiDiPick;
    public ChoiceBox soHoKhau2Choice;
    public ChoiceBox hoTen2Choice;
    public Button xacNhan2Button;
    public Button xacNhan1Button;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void troVe(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void xacNhan1(ActionEvent actionEvent) throws SQLException {
        ArrayList<NhanKhau> arrayList = GiaoTiep.getNhanKhau();
        NhanKhau[] list = new NhanKhau[5];
    }

    public void xacNhan2(ActionEvent actionEvent) {
    }
}
