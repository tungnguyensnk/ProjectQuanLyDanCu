package com.project1.Case3;

import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Hiển thị 1 thông báo trong bảng
 */
public class Change31b implements Initializable {
    public Button troVe;
    public Label tieuDe;
    public Label content;
    Menu menu;
    static LichSH now;

    public void ShowTieuDe() {
        String Date = now.getNgayThang();
        tieuDe.setText("Lịch sinh hoạt ngày " + Date);
    }

    public void ShowContent() {
        String ThongBao = now.getThongBao();
        content.setText(ThongBao);
    }

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShowContent();
        ShowTieuDe();
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change31a.fxml"));
        Parent pr = loader.load();
        Change31a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

}
