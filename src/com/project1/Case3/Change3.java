package com.project1.Case3;

import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Menu lịch sinh hoạt
 */
public class Change3 implements Initializable {
    public Button button_watch;
    public Button button_make;
    public AnchorPane pane3;
    Menu menu;


    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    /**
     *
     */
    public void Change31a() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change31a.fxml"));
        Parent pr = loader.load();
        Change31a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void Change32a() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change32a.fxml"));
        Parent pr = loader.load();
        Change32a controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void PressButtonWatch() throws IOException {   /// Xem danh sách lịch sinh hoạt
        Change31a();
    }

    public void PressButtonMake() throws IOException {    /// Lên lịch sinh hoạt mới
        Change32a();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * set style cho các button
         */

        button_watch.setOnMouseEntered(e -> button_watch.setStyle("-fx-background-radius: 20;" +
                " -fx-border-radius: 20; -fx-background-color: #fd8a4f; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button_watch.setOnMouseExited(e -> button_watch.setStyle("-fx-background-radius: 20;" +
                " -fx-border-radius: 20; -fx-background-color: #fd8a4f;"));
        button_make.setOnMouseEntered(e -> button_make.setStyle("-fx-background-radius: 20;" +
                " -fx-border-radius: 20; -fx-background-color: #32bfbe; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button_make.setOnMouseExited(e -> button_make.setStyle("-fx-background-radius: 20;" +
                " -fx-border-radius: 20; -fx-background-color: #32bfbe;"));


    }
}


