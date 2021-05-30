package com.project1.Case3;

import com.project1.Main.GiaoTiep;
import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
import com.sun.source.tree.NewArrayTree;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Nhập vào nội dung thông báo
 */
public class Change32b implements Initializable {
    @FXML
    Alert save = new Alert((AlertType.INFORMATION));
    @FXML
    AnchorPane panel32b;
    @FXML
    Button QuayLai;
    @FXML
    Button Save;
    @FXML
    TextField TieuDe;
    TextArea ThongBao;
    @FXML
    Label time;
    @FXML
    Button back = new Button("OK");
    public static String ThoiGian;     /// Thời gian đã lấy ở Change32a
    public static String DiaDiem;     //// Địa điểm đã lấy ở Change32a
    public static String NoiDung;   /** loại thông báo đã lấy ở Change32a*/
    public static String Thong_Bao = "";   /// Nội dung thông báo
    @FXML
    Label dong1, dong2, dong3, dong7, dong8, dong9, dong10;
    @FXML
    TextField dong4, dong5, dong6;
    String[] dong = new String[15];
    Menu menu;
    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void pressQuayLai(ActionEvent event) throws IOException {    /// Trở lại tab "Lên lịch sinh hoạt mới"
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change32a.fxml"));
        Parent pr = loader.load();
        Change32a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void ShowDiaDiem() {   /// Hiển thị địa điểm
        dong6.setText(DiaDiem);
    }

    public void ShowThoiGian(ActionEvent event) {    //// Lấy thêm giờ phút nhập từ bàn phím
        String Get = dong5.getText();
        dong5.setText(Get + " ngày " + ThoiGian + ".");    /// Hiện giờ phút, ngày tháng
    }




    public void run() {     /// Lấy từ văn bản vào String Thong_Bao
        dong[1] = dong1.getText();
        dong[2] = dong2.getText();
        dong[3] = dong3.getText();
        dong[4] = dong4.getText();
        dong[5] = dong5.getText();
        dong[6] = dong6.getText();
        dong[7] = dong7.getText();
        dong[8] = dong8.getText();
        dong[9] = dong9.getText();
        dong[10] = dong10.getText();
        for(int i = 1; i <= 10; i++) {
            Thong_Bao = Thong_Bao + dong[i] + "\r\n";
        }
    }

    private void showAlert() {     /// Hiển thị lưu thành công
        Alert alert = new Alert(AlertType.INFORMATION, "Lưu thông báo thành công", ButtonType.YES);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            alert.close();
        }
    }


    public void pressSave(ActionEvent event) throws SQLException {   //// Lưu nội dung thông báo
        run();
        System.out.print(Thong_Bao);
        int Index = 0;
        try {
            Index = GiaoTiep.Count();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Index++;
        GiaoTiep.addLichSH(new LichSH(Index, ThoiGian, DiaDiem, NoiDung, Thong_Bao));
        showAlert();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TieuDe.setText("Lịch sinh hoạt tổ dân phố ngày " + ThoiGian);   /// Hiển thị tiêu đề
        time.setText("Hà Nội, " + ThoiGian);/// Hiển thị thời gian
        ShowDiaDiem();    /// Hiển thị địa điểm
    }
}
