package com.project1.Case1;

import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Change13 implements Initializable {
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button troVe;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void change131(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change131.fxml"));
        Parent pr = loader.load();
        Change131 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void change132(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change132.fxml"));
        Parent pr = loader.load();
        Change132 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void change133(ActionEvent actionEvent) {
    }

    public void change134(ActionEvent actionEvent) {
    }

    public void troVe(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change1.fxml"));
        Parent pr = loader.load();
        Change1 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }
}
