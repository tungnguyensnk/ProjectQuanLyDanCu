package com.project1.Case1;

import com.project1.Main.Menu;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Change1 implements Initializable {
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    /**
     * chức năng 1-1: hiển thị hộ khẩu
     *ssf
     *
     *
     * @throws IOException
     */
    public void change11() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change11a.fxml"));
        Parent pr = loader.load();
        Change11a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    /**
     * chức năng 1-2:hiển thị nhân khẩu
     *
     * @throws IOException
     */
    public void change12() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change12a.fxml"));
        Parent pr = loader.load();
        Change12a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    /**
     * chức năng 1-3:thay đổi hoạt động nhân khẩu
     *
     * @throws IOException
     */
    public void change13() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    /**
     * chức năng 1-4:thống kê
     *
     * @throws IOException
     */
    public void change14() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change14.fxml"));
        Parent pr = loader.load();
        Change14 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * set style cho các button
         */
        button1.setOnMouseEntered(e -> button1.setStyle("-fx-background-color: #fd8a4f; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button1.setOnMouseExited(e -> button1.setStyle("-fx-background-color: #fd8a4f; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;"));
        button2.setOnMouseEntered(e -> button2.setStyle("-fx-background-color: #fa6373; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button2.setOnMouseExited(e -> button2.setStyle("-fx-background-color: #fa6373; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;"));
        button3.setOnMouseEntered(e -> button3.setStyle("-fx-background-color: #90eeed; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button3.setOnMouseExited(e -> button3.setStyle("-fx-background-color: #90eeed; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;"));
        button4.setOnMouseEntered(e -> button4.setStyle("-fx-background-color: #32bfbe; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button4.setOnMouseExited(e -> button4.setStyle("-fx-background-color: #32bfbe; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;"));
    }
}
