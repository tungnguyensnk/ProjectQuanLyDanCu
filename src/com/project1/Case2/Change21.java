package com.project1.Case2;

import com.project1.Main.Alert;
import com.project1.Main.GiaoTiep;
import com.project1.Main.Menu;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Change21 implements Initializable {
    public Button troVe;
    public Button ghiNhan;
    public ComboBox phanLoaiComboBox;
    public TextField hoTenField;
    public DatePicker ngayGhiNhanPick;
    public TextArea noiDungArea;
    public TextField soDienThoaiField;
    public TextField diaChiField;
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phanLoaiComboBox.getItems().addAll("Khiếu nại","Tố cáo","Khuyến nghị","Phản ánh");

    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change2.fxml"));
        Parent pr = loader.load();
        Change2 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void ghiNhan() throws SQLException, IOException {
        GiaoTiep.setPhanAnh(new NoiDungPhanAnh(hoTenField.getText(),soDienThoaiField.getText(),
                ngayGhiNhanPick.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                ,phanLoaiComboBox.getSelectionModel().getSelectedItem().toString(),noiDungArea.getText(),diaChiField.getText(),"Chưa xem"));
        //thông báo thêm thành công
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Đã ghi nhận");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(troVe.getScene().getWindow().getX() + 430);
        alert1.setY(troVe.getScene().getWindow().getY() + 400);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
    }
}
