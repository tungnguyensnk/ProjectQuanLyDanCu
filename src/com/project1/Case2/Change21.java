package com.project1.Case2;

import com.project1.Main.Menu;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.project1.Main.*;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Change21 implements Initializable {
    public Button troVe;
    public Button ghiNhan;
    public ComboBox phanLoaiComboBox;
    public TextField hoTenField;
    public DatePicker ngayGhiNhanPick;
    public TextArea noiDungArea;
    public TextField soDienThoaiField;
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change2.fxml"));
        Parent pr = loader.load();
        Change2 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void ghiNhan() {
    }
}
