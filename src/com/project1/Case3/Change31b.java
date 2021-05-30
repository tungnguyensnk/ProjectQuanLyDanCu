package com.project1.Case3;

import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Hiển thị 1 thông báo trong bảng
 */
public class Change31b implements Initializable {
    @FXML
    TextField Content;
    @FXML
    Button TroVe;
    @FXML
    TextField TieuDe;
    Menu menu;
    static LichSH now;

    public void ShowTieuDe() {     /// Hiện tiêu đề
        String Date = now.getNgayThang();
        TieuDe.setText("Lịch sinh hoạt ngày " + Date);
        //TieuDe.setAlignment();
    }

    public void ShowContent() {     /// Hiện nội dung thông báo
        String ThongBao = now.getThongBao();
        System.out.print("\naaa\n\n" + ThongBao);
        Content.setText(ThongBao);
    }


    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void Back(ActionEvent event) throws IOException {     ///Trở về bảng sinh hoạt
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change31a.fxml"));
        Parent pr = loader.load();
        Change31a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowContent();
        ShowTieuDe();
    }

//    public void ShowContent(javafx.event.ActionEvent event) {
//    }
}
