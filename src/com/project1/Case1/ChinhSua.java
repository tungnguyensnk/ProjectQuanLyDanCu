package com.project1.Case1;

import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * hiển thị stage chỉnh sửa hộ khẩu
 */
public class ChinhSua implements Initializable {
    public AnchorPane root;
    public Label soHoKhauLabel;
    public Label hoTenChuLabel;
    public Label diaChiLabel;
    public Label ghiChuLabel;
    public TextField soHoKhauField;
    public TextField hoTenChuField;
    public TextField diaChiField;
    public TextArea ghiChiField;
    Change11a change11a;

    public void setChange11a(Change11a change11a) {
        this.change11a = change11a;
    }

    int idho = 0;

    private double xOffset = 0;
    private double yOffset = 0;

    public int getIdho() {
        return idho;
    }

    public void setIdho(int idho) {
        this.idho = idho;
    }

    public void tat() {
        root.getScene().getWindow().hide();
        change11a.setItems();
    }

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
                HoKhau hoKhau = GiaoTiep.getHoKhau(getIdho());
                soHoKhauLabel.setText(hoKhau.getIdho() + "");
                hoTenChuLabel.setText(hoKhau.getHotenchu());
                diaChiLabel.setText(hoKhau.getDiachi());
                ghiChuLabel.setText(hoKhau.getGhichu());

                soHoKhauField.setText(hoKhau.getIdho() + "");
                hoTenChuField.setText(hoKhau.getHotenchu());
                diaChiField.setText(hoKhau.getDiachi());
                ghiChiField.setText(hoKhau.getGhichu());

                //tooltip hiển thị chi tiết mục quá dài
                Tooltip tooltip1 = new Tooltip(diaChiLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(diaChiLabel, tooltip1);
                Tooltip tooltip2 = new Tooltip(ghiChuLabel.getText());
                tooltip2.setShowDelay(Duration.millis(300));
                Tooltip.install(ghiChuLabel, tooltip2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delay.play();

        soHoKhauField.setDisable(true);
        hoTenChuField.setDisable(true);
        soHoKhauField.setVisible(false);
        diaChiField.setVisible(false);
        hoTenChuField.setVisible(false);
        ghiChiField.setVisible(false);

        //tạo phím tắt
        root.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

            //Ctrl+E để edit
            if (KeyCombination.keyCombination("Ctrl+E").match(keyEvent)) {
                soHoKhauLabel.setVisible(false);
                hoTenChuLabel.setVisible(false);
                diaChiLabel.setVisible(false);
                ghiChuLabel.setVisible(false);

                soHoKhauField.setVisible(true);
                diaChiField.setVisible(true);
                hoTenChuField.setVisible(true);
                ghiChiField.setVisible(true);
            }

            //Ctrl+S để lưu và update dữ liệu lên DB
            if (KeyCombination.keyCombination("Ctrl+S").match(keyEvent)) {
                soHoKhauLabel.setText(soHoKhauField.getText());
                hoTenChuLabel.setText(hoTenChuField.getText());
                diaChiLabel.setText(diaChiField.getText());
                ghiChuLabel.setText(ghiChiField.getText());
                try {
                    GiaoTiep.setHoKhau(new HoKhau(Integer.parseInt(soHoKhauLabel.getText()), "", diaChiLabel.getText(), ghiChuLabel.getText(),
                            GiaoTiep.getDanhSachViTri(diaChiLabel.getText()).get(1)));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                soHoKhauLabel.setVisible(true);
                hoTenChuLabel.setVisible(true);
                diaChiLabel.setVisible(true);
                ghiChuLabel.setVisible(true);

                soHoKhauField.setVisible(false);
                diaChiField.setVisible(false);
                hoTenChuField.setVisible(false);
                ghiChiField.setVisible(false);
            }
        });
    }
}
