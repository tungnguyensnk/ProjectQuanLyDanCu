package com.project1.Main;

import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller khi login
 */
public class Controller implements Initializable {
    public TextField userName;
    public PasswordField passWord;
    public ImageView exit;
    public Button loginButton;

    /**
     * Check xem login có hợp lệ không bằng cách check kết quả trả về của methođ GiaoTiep
     *
     * @throws IOException
     */
    public void loginCheck() throws IOException {
        Connection con = GiaoTiep.connect(userName.getText(), passWord.getText());
        /**
         * nếu kq không rỗng,tạo hiệu ứng thu nhỏ màn hình đăng nhập rồi phóng to màn hình menu
         */
        if (con != null) {
            Scene sc1 = exit.getScene();
            Stage st1 = (Stage) sc1.getWindow();
            Parent root = sc1.getRoot();
            sc1.setRoot(new Region());
            Scene[] scene = new Scene[40];
            for (int i = 0; i < 20; i++) {
                if (i > 0) {
                    scene[i - 1].setRoot(new Region());
                }
                scene[i] = new Scene(root, 711 - 36 * i, 400 - 20 * i);
                st1.setScene(scene[i]);
            }

            //quãng thay đổi giữa 2 scene
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
            scene[19].setRoot(new Region());
            for (int i = 0; i < 20; i++) {
                if (i > 0) {
                    scene[20 + i - 1].setRoot(new Region());
                }
                scene[20 + i] = new Scene(root, 50.52 * i, 28.42 * i);
                st1.setScene(scene[20 + i]);
            }
            scene[39].setFill(Color.TRANSPARENT);
        } else {

            /**
             * nếu không, đưa ra cảnh báo sai tk,mk
             */
            Stage alert1 = new Stage();
            Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("alert.fxml")));
            Scene sc1 = new Scene(root1);
            alert1.setScene(sc1);
            sc1.setFill(Color.TRANSPARENT);
            alert1.initStyle(StageStyle.TRANSPARENT);
            alert1.setX(exit.getScene().getWindow().getX() + 230);
            alert1.setY(exit.getScene().getWindow().getY() + 240);
            alert1.setAlwaysOnTop(true);
            alert1.show();
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> alert1.close());
            delay.play();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * tạo style cho button đăng nhập
         */
        loginButton.setOnMouseEntered(e -> loginButton.setStyle("-fx-background-color: #ff4f81;" +
                "-fx-background-radius: 50; -fx-border-radius: 50;"));
        loginButton.setOnMouseExited(e -> loginButton.setStyle("-fx-background-color: #2dde98;" +
                "-fx-background-radius: 50; -fx-border-radius: 50;"));
    }

    /**
     * thoát ứng dụng
     */
    public void exitApp() {
        System.exit(0);
    }

    /**
     * quên mật khẩu
     */
    public void forgot() {

    }

    /**
     * bắt sự kiện login bằng phím enter
     *
     * @param keyEvent
     * @throws IOException
     */
    public void loginEnter(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            loginCheck();
        }
    }
}
