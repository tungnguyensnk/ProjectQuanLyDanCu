package com.project1.Case4;

import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Chức năng 4.3.a: chỉnh sửa bản ghi Sức khỏe người dân
 */
public class Case43aController implements Initializable {
    /**
     * Truyền menu chính
     */
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    @FXML private Label hoVaTenLabel;
    @FXML private Label gioiTinhLabel;
    @FXML private Label ngaySinhLabel;
    @FXML private Label idHoKhauLabel;
    @FXML private Label idNhanKhauLabel;

    @FXML private ComboBox<TinhTrangComboBoxItem> tinhTrangCBB;
    @FXML private TextField nguonLayTF;

    @FXML private TextField noiCachLyTF;
    @FXML private DatePicker ngayBatDauCachLyDP;

    @FXML private TextField noiDieuTriTF;
    @FXML private DatePicker ngayBatDauDieuTriDP;

    @FXML private RadioButton lan1AmRB;
    @FXML private RadioButton lan1DuongRB;
    @FXML private RadioButton lan1ChuaRB;
    private ToggleGroup lan1TG;

    @FXML private RadioButton lan2AmRB;
    @FXML private RadioButton lan2DuongRB;
    @FXML private RadioButton lan2ChuaRB;
    private ToggleGroup lan2TG;

    @FXML private RadioButton lan3AmRB;
    @FXML private RadioButton lan3DuongRB;
    @FXML private RadioButton lan3ChuaRB;
    private ToggleGroup lan3TG;

    private static SucKhoeNguoiDan selectedRecord;

    public static void setSelectedRecord(SucKhoeNguoiDan selectedRecord) {
        Case43aController.selectedRecord = selectedRecord;
    }

    /**
     * Thiết lập ban đầu cho các giá trị
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoVaTenLabel.setText(selectedRecord.getHoVaTen());
        gioiTinhLabel.setText(selectedRecord.getGioiTinh());
        ngaySinhLabel.setText(selectedRecord.getNgaySinh());
        idHoKhauLabel.setText(Integer.toString(selectedRecord.getIdHoKhau()));
        idNhanKhauLabel.setText(Integer.toString(selectedRecord.getIdNhanKhau()));

        tinhTrangCBB.getItems().add(new TinhTrangComboBoxItem("F0", 0));
        tinhTrangCBB.getItems().add(new TinhTrangComboBoxItem("F1", 1));
        tinhTrangCBB.getItems().add(new TinhTrangComboBoxItem("F2", 2));
        tinhTrangCBB.getItems().add(new TinhTrangComboBoxItem("F3", 3));
        tinhTrangCBB.getItems().add(new TinhTrangComboBoxItem("Bình thường", 4));

        for (TinhTrangComboBoxItem curItem: tinhTrangCBB.getItems()) {
            if (curItem.getValue() == selectedRecord.getTinhTrang()){
                tinhTrangCBB.setValue(curItem);
                break;
            }
        }

        if (selectedRecord.getNguonLayCovid19() != null)
            nguonLayTF.setText(selectedRecord.getNguonLayCovid19());

        if (selectedRecord.getNoiCachLy() != null)
            noiCachLyTF.setText(selectedRecord.getNoiCachLy());

        if (selectedRecord.getNgayBatDauCachLy() != null && !selectedRecord.getNgayBatDauCachLy().equals(""))
        ngayBatDauCachLyDP.setValue(LocalDate.parse(selectedRecord.getNgayBatDauCachLy()));

        if (selectedRecord.getNoiDieuTri() != null)
            noiDieuTriTF.setText(selectedRecord.getNoiDieuTri());

        if (selectedRecord.getNgayBatDauDieuTri() != null && !selectedRecord.getNgayBatDauDieuTri().equals(""))
        ngayBatDauDieuTriDP.setValue(LocalDate.parse(selectedRecord.getNgayBatDauDieuTri()));

        lan1TG = new ToggleGroup();
        lan1AmRB.setToggleGroup(lan1TG);
        lan1DuongRB.setToggleGroup(lan1TG);
        lan1ChuaRB.setToggleGroup(lan1TG);
        lan1AmRB.setSelected(selectedRecord.getKetQuaTest1() == 0);
        lan1DuongRB.setSelected(selectedRecord.getKetQuaTest1() == 1);
        lan1ChuaRB.setSelected(selectedRecord.getKetQuaTest1() == -1);

        lan2TG = new ToggleGroup();
        lan2AmRB.setToggleGroup(lan2TG);
        lan2DuongRB.setToggleGroup(lan2TG);
        lan2ChuaRB.setToggleGroup(lan2TG);
        lan2AmRB.setSelected(selectedRecord.getKetQuaTest2() == 0);
        lan2DuongRB.setSelected(selectedRecord.getKetQuaTest2() == 1);
        lan2ChuaRB.setSelected(selectedRecord.getKetQuaTest2() == -1);

        lan3TG = new ToggleGroup();
        lan3AmRB.setToggleGroup(lan3TG);
        lan3DuongRB.setToggleGroup(lan3TG);
        lan3ChuaRB.setToggleGroup(lan3TG);
        lan3AmRB.setSelected(selectedRecord.getKetQuaTest3() == 0);
        lan3DuongRB.setSelected(selectedRecord.getKetQuaTest3() == 1);
        lan3ChuaRB.setSelected(selectedRecord.getKetQuaTest3() == -1);
    }

    /**
     * Xóa DatePicker ngayBatDauCachLy
     *
     * @param clickX1Button
     */
    public void x1ButtonHandler(ActionEvent clickX1Button) {
        ngayBatDauCachLyDP.setValue(null);
    }

    /**
     * Xóa DatePicker ngayBatDauDieuTri
     *
     * @param clickX2Button
     */
    public void x2ButtonHandler(ActionEvent clickX2Button) {
        ngayBatDauDieuTriDP.setValue(null);
    }

    /**
     * Tổng hợp thông tin, cập nhật vào database
     *
     * @param clickXacNhanButton
     */
    public void xacNhanButtonHandler(ActionEvent clickXacNhanButton) throws SQLException, IOException {
        SucKhoeNguoiDan curRecord = selectedRecord.clone();

        TinhTrangComboBoxItem curTinhTrangItem = (TinhTrangComboBoxItem) tinhTrangCBB.getSelectionModel().getSelectedItem();
        curRecord.setTinhTrang(curTinhTrangItem.getValue());

        curRecord.setNguonLayCovid19(nguonLayTF.getText());

        curRecord.setNoiCachLy(noiCachLyTF.getText());
        if (ngayBatDauCachLyDP.getValue() != null) {
            curRecord.setNgayBatDauCachLy(ngayBatDauCachLyDP.getValue().toString());
        } else {
            curRecord.setNgayBatDauCachLy("");
        }

        curRecord.setNoiDieuTri(noiDieuTriTF.getText());
        if (ngayBatDauDieuTriDP.getValue() != null) {
            curRecord.setNgayBatDauDieuTri(ngayBatDauDieuTriDP.getValue().toString());
        } else {
            curRecord.setNgayBatDauDieuTri("");
        }

        if (((RadioButton) lan1TG.getSelectedToggle()).getText().equals("Âm tính")) {
            curRecord.setKetQuaTest1(0);
        }
        if (((RadioButton) lan1TG.getSelectedToggle()).getText().equals("Dương tính")) {
            curRecord.setKetQuaTest1(1);
        }
        if (((RadioButton) lan1TG.getSelectedToggle()).getText().equals("Chưa xét nghiệm")) {
            curRecord.setKetQuaTest1(-1);
        }

        if (((RadioButton) lan2TG.getSelectedToggle()).getText().equals("Âm tính")) {
            curRecord.setKetQuaTest2(0);
        }
        if (((RadioButton) lan2TG.getSelectedToggle()).getText().equals("Dương tính")) {
            curRecord.setKetQuaTest2(1);
        }
        if (((RadioButton) lan2TG.getSelectedToggle()).getText().equals("Chưa xét nghiệm")) {
            curRecord.setKetQuaTest2(-1);
        }

        if (((RadioButton) lan3TG.getSelectedToggle()).getText().equals("Âm tính")) {
            curRecord.setKetQuaTest3(0);
        }
        if (((RadioButton) lan3TG.getSelectedToggle()).getText().equals("Dương tính")) {
            curRecord.setKetQuaTest3(1);
        }
        if (((RadioButton) lan3TG.getSelectedToggle()).getText().equals("Chưa xét nghiệm")) {
            curRecord.setKetQuaTest3(-1);
        }

        SucKhoeNguoiDanGiaoTiep.update(curRecord);

        /**
         * Tạo thông báo
         */
        Alert thongBaoAlert = new Alert(Alert.AlertType.INFORMATION);
        thongBaoAlert.setTitle("Thông báo");
        thongBaoAlert.setHeaderText("Lời nhắn");
        thongBaoAlert.setContentText("Chỉnh sửa thành công!");
        thongBaoAlert.show();

        /**
         * Quay về case 4.3
         */
        quayLaiButtonHandler(new ActionEvent());
    }

    /**
     * Quay lại case 4.3
     *
     * @param clickQuayLaiButton
     * @throws IOException
     */
    public void quayLaiButtonHandler(ActionEvent clickQuayLaiButton) throws IOException {
        FXMLLoader case43Loader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case43.fxml"));
        case43Loader.setLocation(url);

        Parent parent = case43Loader.load();

        Case43Controller controller = case43Loader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }
}
