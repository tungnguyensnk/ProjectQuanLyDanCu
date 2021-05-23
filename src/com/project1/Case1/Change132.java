package com.project1.Case1;

import com.project1.Main.*;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Change132 implements Initializable {
    public Button troVe;
    public ChoiceBox soHoKhau1Choice;
    public ChoiceBox<String> hoTen1Choice;
    public DatePicker ngayDiPick;
    public TextField noiDiPick;
    public ChoiceBox soHoKhau2Choice;
    public ChoiceBox<String> hoTen2Choice;
    public Button xacNhan2Button;
    public Button xacNhan1Button;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ArrayList<HoKhau> hoKhauArrayList = GiaoTiep.getHoKhau();
            for (HoKhau hoKhau : hoKhauArrayList) {
                arrayList.add(String.valueOf(hoKhau.getIdho()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ObservableList<String> idList = FXCollections.observableList(arrayList);
        soHoKhau1Choice.setItems(idList);
        soHoKhau2Choice.setItems(idList);
        ArrayList<String> nhanKhau1 = new ArrayList<>();
        soHoKhau1Choice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) ->{
            try {
                nhanKhau1.clear();
                ArrayList<NhanKhau> nhanKhauArrayList = GiaoTiep.getNhanKhau();
                for (NhanKhau nhanKhau: nhanKhauArrayList) {
                    if(t1.equals(String.valueOf(nhanKhau.getIdho()))){
                        nhanKhau1.add(nhanKhau.getHoten());
                    }
                }
                hoTen1Choice.setItems(idList);
                hoTen1Choice.setItems(FXCollections.observableList(nhanKhau1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        ArrayList<String> nhanKhau2 = new ArrayList<>();
        soHoKhau2Choice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) ->{
            try {
                nhanKhau2.clear();
                ArrayList<NhanKhau> nhanKhauArrayList = GiaoTiep.getNhanKhau();
                for (NhanKhau nhanKhau: nhanKhauArrayList) {
                    if(t1.equals(String.valueOf(nhanKhau.getIdho()))){
                        nhanKhau2.add(nhanKhau.getHoten());
                    }
                }
                hoTen2Choice.setItems(idList);
                hoTen2Choice.setItems(FXCollections.observableList(nhanKhau2));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void troVe(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void xacNhan1(ActionEvent actionEvent) throws SQLException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanKhau nhanKhau: GiaoTiep.getNhanKhau()) {
            if(hoTen1Choice.getValue().equals(nhanKhau.getHoten())){
                GiaoTiep.setGhiChuNhanKhau(nhanKhau.getId(), "Chuyển đến "+noiDiPick.getText()+" ngày "+
                        ngayDiPick.getValue().format(dateTimeFormatter));
            }
        }
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Ghi chú chuyến đi thành công!");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(troVe.getScene().getWindow().getX()+430);
        alert1.setY(troVe.getScene().getWindow().getY()+200);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
    }

    public void xacNhan2(ActionEvent actionEvent) throws SQLException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanKhau nhanKhau: GiaoTiep.getNhanKhau()) {
            if(hoTen2Choice.getValue().equals(nhanKhau.getHoten())){
                GiaoTiep.setGhiChuNhanKhau(nhanKhau.getId(), "Qua đời ngày "+ LocalDate.now().format(dateTimeFormatter));
            }
        }
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Xin chia buồn!");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(troVe.getScene().getWindow().getX()+450);
        alert1.setY(troVe.getScene().getWindow().getY()+450);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
    }
}
