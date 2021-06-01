package com.project1.Case3;

import com.project1.Main.GiaoTiep;
import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
import com.sun.source.tree.NewArrayTree;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Nhập vào nội dung thông báo
 */
public class Change32b implements Initializable {
    public Button troVe;
    public Label tieuDe;
    public TextField diaDiem;
    public DatePicker thoiGian;
    public TextField noiDungArea;
    @FXML
    AnchorPane panel32b;
    @FXML
    public static String ThoiGian;
    public static String DiaDiem;
    public static String NoiDung;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void pressSave() throws SQLException {
        int Index = 0;
        try {
            Index = GiaoTiep.Count();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Index++;
        GiaoTiep.addLichSH(new LichSH(Index, ThoiGian, DiaDiem, NoiDung, noiDungArea.getText()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.millis(10));
        delay.setOnFinished(actionEvent -> {
            tieuDe.setText("Lịch sinh hoạt tổ dân phố ngày " + ThoiGian);
            noiDungArea.setText(NoiDung);
            thoiGian.setValue(LocalDate.parse(ThoiGian, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            diaDiem.setText(DiaDiem);
        });
        delay.play();
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change32a.fxml"));
        Parent pr = loader.load();
        Change32a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void inword() {
    }
}
