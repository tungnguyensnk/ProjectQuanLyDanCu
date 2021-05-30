package com.project1.Case3;

import com.project1.Main.GiaoTiep;
import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


/** ////////////
Menu Lịch sinh hoạt

*/////////////
public class Change3 implements Initializable {
    public Button button_watch;
    public Button button_make;
    public AnchorPane pane3;
    //ArrayList<LichSH> Now;
    Menu menu;


    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    /**

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

    public void PressButtonWatch(ActionEvent event) throws IOException {   /// Xem danh sách lịch sinh hoạt
        Change31a();
    }

    public void PressButtonMake(ActionEvent event)  throws IOException{    /// Lên lịch sinh hoạt mới
        Change32a();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * set style cho các button
         */

        button_watch.setOnMouseEntered(e -> button_watch.setStyle("-fx-background-color: #fd8a4f; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button_watch.setOnMouseExited(e -> button_watch.setStyle("-fx-background-color: #fd8a4f; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;"));
        button_make.setOnMouseEntered(e -> button_make.setStyle("-fx-background-color: #fa6373; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        button_make.setOnMouseExited(e -> button_make.setStyle("-fx-background-color: #fa6373; -fx-border-radius: 20px; " +
                "-fx-background-radius: 20px;"));


    }
}


