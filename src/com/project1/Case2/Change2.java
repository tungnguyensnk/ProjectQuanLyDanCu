package com.project1.Case2;

import com.project1.Main.Menu;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Change2 implements Initializable {
    public Button change21;
    public Button change22;
    public Button change23;
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        change21.setOnMouseEntered(e -> change21.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fa6373;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        change21.setOnMouseExited(e -> change21.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fa6373;"));
        change22.setOnMouseEntered(e -> change22.setStyle("-fx-background-radius: 10; -fx-background-radius: 10; -fx-background-color: #32bfbe;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        change22.setOnMouseExited(e -> change22.setStyle("-fx-background-radius: 10; -fx-background-radius: 10; -fx-background-color: #32bfbe;"));
        change23.setOnMouseEntered(e -> change23.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fd8a4f;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        change23.setOnMouseExited(e -> change23.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fd8a4f;"));


    }

    public void change21() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change21.fxml"));
        Parent pr = loader.load();
        Change21 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void change22() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change22.fxml"));
        Parent pr = loader.load();
        Change22 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void change23() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change23.fxml"));
        Parent pr = loader.load();
        Change23 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }
}
