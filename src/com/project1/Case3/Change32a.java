package com.project1.Case3;

import com.project1.Main.LichSH;
import com.project1.Main.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Lên lịch sinh hoạt mới
 */

public class Change32a implements Initializable {
    @FXML
    AnchorPane panel32a;
    @FXML
    DatePicker Date;
    @FXML
    TextField Place;
    @FXML
    Button button_return;

    ObservableList<String> options =  FXCollections.observableArrayList("Bàn luận, giải quyết phản ánh, kiến nghị",
            "Các vấn đề đóng góp ",
            "Thay đổi ban cán sự",
            "Thay đổi quy mô tổ dân phố(Thành lâp, đổi tên, sáp nhập, giải thể,...)"
    );
    @FXML
    ComboBox Content;   //// Nội Dung thông báo


    String ThoiGian;
    String DiaDiem;
    String NoiDung;
    Menu menu;
    //ArrayList<LichSH> NewDB = new ArrayList<>();

    public void setMenu(Menu controller) {
        this.menu = controller;
    }


    public void getContent(ActionEvent event) {   /// Chọn loại thông báo chọn trong Box
        NoiDung = Content.getSelectionModel().getSelectedItem().toString();
    }

    public void getDate(ActionEvent event) {     /// Lấy ngày tháng từ DatePicker
        ThoiGian = Date.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void getPlace() {     ///Lấy địa điểm được nhập vào
        DiaDiem = Place.getText();
    }

    public void setButton_return(ActionEvent event) throws IOException {     //// Trở về Menu lịch sinh hoạt
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change3.fxml"));
        Parent pr = loader.load();
        Change3 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void MakeNew() { /// Lấy địa điểm, thời gian, loại thông báo sang Change32b
        Change32b.DiaDiem = DiaDiem;
        Change32b.ThoiGian = ThoiGian;
        Change32b.NoiDung = NoiDung;
    }

    public void button_next(ActionEvent event) throws IOException {
        getPlace();
        //getContent();
        MakeNew();   /// Lấy địa điểm, thời gian, loại thông báo sang Change32b

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change32b.fxml"));
        Parent pr = loader.load();
        Change32b controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ///// Thêm loại thông báo vào Box
        Content.getItems().addAll("Bàn luận, giải quyết phản ánh, kiến nghị",
                "Các vấn đề đóng góp ",
                "Thay đổi ban cán sự",
                "Thay đổi quy mô tổ dân phố(Thành lâp, đổi tên, sáp nhập, giải thể,...)"
        );
    }
}
