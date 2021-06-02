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
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Nhập vào nội dung thông báo
 */
public class Change32b implements Initializable {
    public Button troVe;
    public Label tieuDe;
    public TextArea noiDungArea;
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

    public void pressSave() throws SQLException, IOException {
        int Index = 0;
        try {
            Index = GiaoTiep.Count();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Index++;
        GiaoTiep.addLichSH(new LichSH(Index, ThoiGian, DiaDiem, NoiDung, noiDungArea.getText()));

        //thông báo thêm thành công
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        com.project1.Main.Alert controller = loader.getController();
        controller.setTextAlert("Tạo sự kiện thành công");
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PauseTransition delay = new PauseTransition(Duration.millis(10));
        delay.setOnFinished(actionEvent -> {
            tieuDe.setText("Lịch sinh hoạt tổ dân phố ngày " + ThoiGian);
            noiDungArea.setText("Cộng hòa xã hội chủ nghĩa Việt Nam\nĐộc lập - Tự do - Hạnh phúc\n--------------\n\nTHÔNG BÁO HỌP TỔ DÂN PHỐ\n" +
                    "\nKính mời Ông/bà:...............................................................\nTới dự buổi họp: " + NoiDung + "\n" +
                    "Vào ....h ngày " + ThoiGian + " tại: " + DiaDiem + "\n\nKính mời!\t\t\t\t\t");
            panel32b.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../center.css")).toExternalForm());
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

    public void inword() throws IOException {
        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r1 = p1.createRun();
            String[] list = noiDungArea.getText().split("\n");
            for(String string: list){
                r1.setText(string);
                r1.addBreak();
            }
            try (OutputStream os = new FileOutputStream(System.getProperty("user.dir")+"//giaymoi.docx")) {
                doc.write(os);
            }
        }
        //thông báo thêm thành công
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        com.project1.Main.Alert controller = loader.getController();
        controller.setTextAlert("Tạo File thành công");
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
