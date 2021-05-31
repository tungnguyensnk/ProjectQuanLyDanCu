package com.project1.Case1;

import com.project1.Main.GiaoTiep;
import javafx.animation.PauseTransition;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GhiChuNhanKhau implements Initializable {
    public Button xButton;
    public TextArea ghiChuField;
    public Label ghiChuLabel;
    public AnchorPane root;
    int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //thiết lập movable cho root
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            root.getScene().getWindow().setX(mouseEvent.getScreenX() - xOffset);
            root.getScene().getWindow().setY(mouseEvent.getScreenY() - yOffset);
        });

        //hẹn giờ 10ms cho việc hiển thị dữ liệu, do FXML đang load nên idho = 0
        PauseTransition delay = new PauseTransition(Duration.millis(10));
        delay.setOnFinished(event -> {
            try {
                ghiChuLabel.setText(GiaoTiep.getGhiChuNhanKhau(getId()));
                ghiChuField.setText(ghiChuLabel.getText());

                //tooltip hiển thị chi tiết mục quá dài
                Tooltip tooltip1 = new Tooltip(ghiChuLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(ghiChuLabel, tooltip1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delay.play();
        ghiChuField.setVisible(false);

        //tạo phím tắt
        root.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

            //Ctrl+E để edit
            if (KeyCombination.keyCombination("Ctrl+E").match(keyEvent)) {
                ghiChuLabel.setVisible(false);
                ghiChuField.setVisible(true);
            }

            //Ctrl+S để lưu và update dữ liệu lên DB
            if (KeyCombination.keyCombination("Ctrl+S").match(keyEvent)) {
                ghiChuLabel.setText(ghiChuField.getText());
                try {
                    GiaoTiep.setGhiChuNhanKhau(getId(), ghiChuField.getText());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ghiChuLabel.setVisible(true);
                ghiChuField.setVisible(false);
            }
        });
    }

    public void tat() {
        root.getScene().getWindow().hide();
    }
}
