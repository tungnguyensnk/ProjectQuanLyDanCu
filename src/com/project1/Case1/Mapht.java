package com.project1.Case1;

import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * hiển thị map
 */
public class Mapht implements Initializable {
    public WebView webView;
    public AnchorPane root;
    double x, y;
    private double xOffset = 0;
    private double yOffset = 0;

    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //set movable cho root
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            root.getScene().getWindow().setX(mouseEvent.getScreenX() - xOffset);
            root.getScene().getWindow().setY(mouseEvent.getScreenY() - yOffset);
        });
        //load test.html cho webView
        File file = new File(System.getProperty("user.dir") + "//test.html");
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(file.toURI().toString());

        //hẹn giờ 1,5s để chạy hàm initMap do test.html chưa load
        PauseTransition delay = new PauseTransition(Duration.millis(1500));
        delay.setOnFinished(actionEvent -> webEngine.executeScript("initMap(" + x + "," + y + ");"));
        delay.play();

        //ấn enter để thoát
        root.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER)
                root.getScene().getWindow().hide();
        });
    }
}
