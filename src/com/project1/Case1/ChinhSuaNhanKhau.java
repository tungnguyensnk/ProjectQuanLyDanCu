package com.project1.Case1;

import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import com.project1.Main.NhanKhau;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChinhSuaNhanKhau implements Initializable {
    public AnchorPane root;
    public Label soHoKhauLabel;
    public Label gioiTinhLabel;
    public Label nguyenQuanLabel;
    public Label danTocLabel;
    public Label ngaySinhLabel;
    public Label hoTenLabel;
    public Label ngheNghiepLabel;
    public Label noiSinhLabel;
    public Label quanheCHLabel;
    public Label noiLamViecLabel;
    public Label CMNDLabel;
    public Label ngayCapLabel;
    public Label noiCapLabel;
    public Label ndkThuongTruLabel;
    public Label dcThuongTrutrcLabel;
    public TextField soHoKhauField;
    public TextField hoTenField;
    public TextField quanHeCHField;
    public TextField gioiTinhField;
    public TextField ngaySinhField;
    public TextField noiSinhField;
    public TextField nguyenQuanField;
    public TextField noiCapField;
    public TextField danTocField;
    public TextField noiLamViecField;
    public TextField CMNDField;
    public TextField ndkThuongTruField;
    public TextField ngheNghiepField;
    public TextField ngayCapField;
    public TextField dcThuongTrutrcField;
    Change12a change12a;

    public void setChange12a(Change12a change12a) {
        this.change12a = change12a;
    }

    int id = 0;

    private double xOffset = 0;
    private double yOffset = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                NhanKhau nhanKhau = GiaoTiep.getNhanKhau(getId());
                soHoKhauLabel.setText(nhanKhau.getIdho() + "");
                hoTenLabel.setText(nhanKhau.getHoten());
                noiSinhLabel.setText(nhanKhau.getNoisinh());
                quanheCHLabel.setText(nhanKhau.getQuanhech());
                gioiTinhLabel.setText(nhanKhau.getGioitinh());
                ngaySinhLabel.setText(nhanKhau.getNgaysinh());
                nguyenQuanLabel.setText(nhanKhau.getNguyenquan());
                danTocLabel.setText(nhanKhau.getDantoc());
                ngheNghiepLabel.setText(nhanKhau.getNghenghiep());
                noiLamViecLabel.setText(nhanKhau.getNoilamviec());
                CMNDLabel.setText(nhanKhau.getCmnd());
                ngayCapLabel.setText(nhanKhau.getNgaycap());
                noiCapLabel.setText(nhanKhau.getNoicap());
                ndkThuongTruLabel.setText(nhanKhau.getNdkthuongtru());
                dcThuongTrutrcLabel.setText(nhanKhau.getDcthuongtrutrc());

                soHoKhauField.setText(nhanKhau.getIdho() + "");
                hoTenField.setText(nhanKhau.getHoten());
                noiSinhField.setText(nhanKhau.getNoisinh());
                quanHeCHField.setText(nhanKhau.getQuanhech());
                gioiTinhField.setText(nhanKhau.getGioitinh());
                ngaySinhField.setText(nhanKhau.getNgaysinh());
                nguyenQuanField.setText(nhanKhau.getNguyenquan());
                danTocField.setText(nhanKhau.getDantoc());
                ngheNghiepField.setText(nhanKhau.getNghenghiep());
                noiLamViecField.setText(nhanKhau.getNoilamviec());
                CMNDField.setText(nhanKhau.getCmnd());
                ngayCapField.setText(nhanKhau.getNgaycap());
                noiCapField.setText(nhanKhau.getNoicap());
                ndkThuongTruField.setText(nhanKhau.getNdkthuongtru());
                dcThuongTrutrcField.setText(nhanKhau.getDcthuongtrutrc());

                if (quanheCHLabel.getText().equals("Chủ"))
                    quanHeCHField.setDisable(true);

                //tooltip hiển thị chi tiết mục quá dài
                Tooltip tooltip1 = new Tooltip(hoTenLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(hoTenLabel, tooltip1);

                Tooltip tooltip2 = new Tooltip(noiSinhLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(noiSinhLabel, tooltip2);

                Tooltip tooltip3 = new Tooltip(nguyenQuanLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(nguyenQuanLabel, tooltip3);

                Tooltip tooltip4 = new Tooltip(noiLamViecLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(noiLamViecLabel, tooltip4);

                Tooltip tooltip5 = new Tooltip(noiCapLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(noiCapLabel, tooltip5);

                Tooltip tooltip6 = new Tooltip(dcThuongTrutrcLabel.getText());
                tooltip1.setShowDelay(Duration.millis(300));
                Tooltip.install(dcThuongTrutrcLabel, tooltip6);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        delay.play();

        soHoKhauField.setDisable(true);

        quanHeCHField.setVisible(false);
        hoTenField.setVisible(false);
        gioiTinhField.setVisible(false);
        ngaySinhField.setVisible(false);
        noiSinhField.setVisible(false);
        nguyenQuanField.setVisible(false);
        danTocField.setVisible(false);
        ngheNghiepField.setVisible(false);
        noiLamViecField.setVisible(false);
        CMNDField.setVisible(false);
        ngayCapField.setVisible(false);
        noiCapField.setVisible(false);
        ndkThuongTruField.setVisible(false);
        dcThuongTrutrcField.setVisible(false);
        soHoKhauField.setVisible(false);

        //tạo phím tắt
        root.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {

            //Ctrl+E để edit
            if (KeyCombination.keyCombination("Ctrl+E").match(keyEvent)) {
                quanheCHLabel.setVisible(false);
                hoTenLabel.setVisible(false);
                gioiTinhLabel.setVisible(false);
                ngaySinhLabel.setVisible(false);
                noiSinhLabel.setVisible(false);
                nguyenQuanLabel.setVisible(false);
                danTocLabel.setVisible(false);
                ngheNghiepLabel.setVisible(false);
                noiLamViecLabel.setVisible(false);
                CMNDLabel.setVisible(false);
                ngayCapLabel.setVisible(false);
                noiCapLabel.setVisible(false);
                ndkThuongTruLabel.setVisible(false);
                dcThuongTrutrcLabel.setVisible(false);
                soHoKhauLabel.setVisible(false);

                quanHeCHField.setVisible(true);
                hoTenField.setVisible(true);
                gioiTinhField.setVisible(true);
                ngaySinhField.setVisible(true);
                noiSinhField.setVisible(true);
                nguyenQuanField.setVisible(true);
                danTocField.setVisible(true);
                ngheNghiepField.setVisible(true);
                noiLamViecField.setVisible(true);
                CMNDField.setVisible(true);
                ngayCapField.setVisible(true);
                noiCapField.setVisible(true);
                ndkThuongTruField.setVisible(true);
                dcThuongTrutrcField.setVisible(true);
                soHoKhauField.setVisible(true);
            }

            //Ctrl+S để lưu và update dữ liệu lên DB
            if (KeyCombination.keyCombination("Ctrl+S").match(keyEvent)) {
                soHoKhauLabel.setText(soHoKhauField.getText());
                hoTenLabel.setText(hoTenField.getText());
                noiSinhLabel.setText(noiSinhField.getText());
                quanheCHLabel.setText(quanHeCHField.getText());
                gioiTinhLabel.setText(gioiTinhField.getText());
                ngaySinhLabel.setText(ngaySinhField.getText());
                nguyenQuanLabel.setText(nguyenQuanField.getText());
                danTocLabel.setText(danTocField.getText());
                ngheNghiepLabel.setText(ngheNghiepField.getText());
                noiLamViecLabel.setText(noiLamViecField.getText());
                CMNDLabel.setText(CMNDField.getText());
                ngayCapLabel.setText(ngayCapField.getText());
                noiCapLabel.setText(noiCapField.getText());
                ndkThuongTruLabel.setText(ndkThuongTruField.getText());
                dcThuongTrutrcLabel.setText(dcThuongTrutrcField.getText());
                try {
                    GiaoTiep.setNhanKhau(getId(), new NhanKhau(Integer.parseInt(soHoKhauField.getText()), quanHeCHField.getText(), hoTenField.getText(),
                            gioiTinhField.getText(), ngaySinhField.getText(), noiSinhField.getText(), nguyenQuanField.getText(),
                            danTocField.getText(), ngheNghiepField.getText(), noiLamViecField.getText(), CMNDField.getText(),
                            ngayCapField.getText(), noiCapField.getText(), ndkThuongTruField.getText(), dcThuongTrutrcField.getText(), ""));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                quanheCHLabel.setVisible(true);
                hoTenLabel.setVisible(true);
                gioiTinhLabel.setVisible(true);
                ngaySinhLabel.setVisible(true);
                noiSinhLabel.setVisible(true);
                nguyenQuanLabel.setVisible(true);
                danTocLabel.setVisible(true);
                ngheNghiepLabel.setVisible(true);
                noiLamViecLabel.setVisible(true);
                CMNDLabel.setVisible(true);
                ngayCapLabel.setVisible(true);
                noiCapLabel.setVisible(true);
                ndkThuongTruLabel.setVisible(true);
                dcThuongTrutrcLabel.setVisible(true);
                soHoKhauLabel.setVisible(true);

                quanHeCHField.setVisible(false);
                hoTenField.setVisible(false);
                gioiTinhField.setVisible(false);
                ngaySinhField.setVisible(false);
                noiSinhField.setVisible(false);
                nguyenQuanField.setVisible(false);
                danTocField.setVisible(false);
                ngheNghiepField.setVisible(false);
                noiLamViecField.setVisible(false);
                CMNDField.setVisible(false);
                ngayCapField.setVisible(false);
                noiCapField.setVisible(false);
                ndkThuongTruField.setVisible(false);
                dcThuongTrutrcField.setVisible(false);
                soHoKhauField.setVisible(false);
            }
        });
    }

    public void tat() {
        root.getScene().getWindow().hide();
        change12a.setItems();
    }
}
