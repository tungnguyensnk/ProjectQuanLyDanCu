package com.project1.Case2;

import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Change23 implements Initializable {
    public Button troVe;
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
}
