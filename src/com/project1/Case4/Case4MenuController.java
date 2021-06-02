package com.project1.Case4;

import com.project1.Main.Menu;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Case4MenuController implements Initializable {
    public Button change41;
    public Button change42;
    public Button change43;
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
     * @throws IOException
     */
    public void case41ButtonHandler() throws IOException {
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
     * @throws IOException
     */
    public void case42ButtonHandler() throws IOException {
        FXMLLoader case42Loader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case42.fxml"));
        case42Loader.setLocation(url);

        Parent parent = case42Loader.load();

        Case42Controller controller = case42Loader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }

    /**
     * Chuyển sang chức năng 4.3: Bảng theo dõi sức khỏe người dân
     *
     * @throws IOException
     */
    public void case43ButtonHandler() throws IOException {
        FXMLLoader case43Loader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case43.fxml"));
        case43Loader.setLocation(url);

        Parent parent = case43Loader.load();

        Case43Controller controller = case43Loader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        change41.setOnMouseEntered(e -> change41.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fa6373;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        change41.setOnMouseExited(e -> change41.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fa6373;"));
        change42.setOnMouseEntered(e -> change42.setStyle("-fx-background-radius: 10; -fx-background-radius: 10; -fx-background-color: #32bfbe;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        change42.setOnMouseExited(e -> change42.setStyle("-fx-background-radius: 10; -fx-background-radius: 10; -fx-background-color: #32bfbe;"));
        change43.setOnMouseEntered(e -> change43.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fd8a4f;" +
                " -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);"));
        change43.setOnMouseExited(e -> change43.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: #fd8a4f;"));
    }
}
