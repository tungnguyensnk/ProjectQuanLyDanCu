package com.project1.Case3;

import com.project1.Main.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Lên lịch sinh hoạt mới
 */

public class Change32a implements Initializable {
    public Button troVe;
    public Button next;
    @FXML
    AnchorPane panel32a;
    @FXML
    DatePicker Date;
    @FXML
    TextField Place;
    @FXML
    ComboBox Content;

    String ThoiGian;
    String DiaDiem;
    String NoiDung;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void getDate() {
        ThoiGian = Date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void button_next() throws IOException {
        DiaDiem = Place.getText();
        NoiDung = Content.getSelectionModel().getSelectedItem().toString();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change32b.fxml"));
        Parent pr = loader.load();
        Change32b controller = loader.getController();
        Change32b.DiaDiem = DiaDiem;
        Change32b.ThoiGian = ThoiGian;
        Change32b.NoiDung = NoiDung;
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Content.getItems().addAll("Bàn luận, giải quyết phản ánh, kiến nghị",
                "Các vấn đề đóng góp ",
                "Thay đổi ban cán sự",
                "Thay đổi quy mô tổ dân phố(Thành lâp, đổi tên, sáp nhập, giải thể,...)"
        );
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change3.fxml"));
        Parent pr = loader.load();
        Change3 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

}
