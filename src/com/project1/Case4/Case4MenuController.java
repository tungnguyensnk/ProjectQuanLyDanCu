package com.project1.Case4;

import com.project1.Main.Menu;
import com.project1.Case1.Change1;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Case4MenuController {
    /**
     * Hàm truyền Menu chính
     */
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    /**
     * Chuyển sang chức năng 4.1: tờ khai y tế hằng ngày
     *
     * @param clickCase41Button
     * @throws IOException
     */
    public void case41ButtonHandler(ActionEvent clickCase41Button) throws IOException {
        FXMLLoader case41Loader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case41.fxml"));
        case41Loader.setLocation(url);

        Parent parent = case41Loader.load();

        Case41Controller controller = case41Loader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }

    /**
     * Chuyển sang chức năng 4.2: tạo bảng tờ khai y tế
     *
     * @param clickCase42Button
     * @throws IOException
     */
    public void case42ButtonHandler(ActionEvent clickCase42Button) throws IOException {
        FXMLLoader case42Loader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case42.fxml"));
        case42Loader.setLocation(url);

        Parent parent = case42Loader.load();

        Case42Controller controller = case42Loader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }

    public void case43ButtonHandler(ActionEvent clickCase43Button) {

    }

    /**
     * Quay lại
     */
    public void quayLaiButtonHandler(ActionEvent clickQuayLaiButton) throws IOException {
        FXMLLoader change1Loader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("../Case1/change1.fxml"));
        change1Loader.setLocation(url);

        Parent parent = change1Loader.load();

        Change1 controller = change1Loader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }
}
