package com.project1.Case4;

import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Chức năng 4.1: tờ khai y tế hằng ngày
 */

public class Case41Controller implements Initializable {
    /**
     * Hàm truyền Menu chính
     */
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    @FXML private TextField hoVaTenTF;
    @FXML private TextField idNhanKhauTF;
    @FXML private TextField soDienThoaiTF;
    @FXML private TextField khacTF;

    @FXML private RadioButton yesDenNoiCoDichRB;
    @FXML private RadioButton noDenNoiCoDichRB;

    @FXML private RadioButton yesTiepXucRB;
    @FXML private RadioButton noTiepXucRB;

    @FXML private CheckBox hoCB;
    @FXML private CheckBox sotCB;
    @FXML private CheckBox dauHongCB;
    @FXML private CheckBox khoThoCB;
    @FXML private CheckBox khacCB;

    private ToggleGroup denNoiCoDichTG = new ToggleGroup();
    private ToggleGroup tiepXucTG = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yesDenNoiCoDichRB.setToggleGroup(denNoiCoDichTG);
        noDenNoiCoDichRB.setToggleGroup(denNoiCoDichTG);
        yesTiepXucRB.setToggleGroup(tiepXucTG);
        noTiepXucRB.setToggleGroup(tiepXucTG);
    }

    /**
     * Bắt sự kiện cho nút Gửi
     *
     * @param clickGuiButton
     */
    public void guiButtonHandler(ActionEvent clickGuiButton) throws SQLException, IOException {
        /**
         * Tổng hợp thông tin trong tờ khai vào đối tượng toKhai của lớp ToKhaiYTeHangNgay
         */
        ToKhaiYTeHangNgay toKhai = new ToKhaiYTeHangNgay();

        toKhai.setNgayNop(LocalDate.now().toString());

        toKhai.setHoVaTen(hoVaTenTF.getText());
        toKhai.setIdNhanKhau(idNhanKhauTF.getText());
        toKhai.setSoDienThoai(soDienThoaiTF.getText());

        toKhai.setDenNoiCoDich(denNoiCoDichTG.getSelectedToggle() == yesDenNoiCoDichRB);
        toKhai.setTiepXuc(tiepXucTG.getSelectedToggle() == yesTiepXucRB);

        String trieuChung = "";

        if (hoCB.isSelected()) {
            if (trieuChung.length() > 0)
                trieuChung += ", ";
            trieuChung += "ho";
        }

        if (sotCB.isSelected()) {
            if (trieuChung.length() > 0)
                trieuChung += ", ";
            trieuChung += "sốt";
        }

        if (dauHongCB.isSelected()) {
            if (trieuChung.length() > 0)
                trieuChung += ", ";
            trieuChung += "đau họng";
        }

        if (khoThoCB.isSelected()) {
            if (trieuChung.length() > 0)
                trieuChung += ", ";
            trieuChung += "khó thở";
        }

        if (khacCB.isSelected()) {
            if (trieuChung.length() > 0)
                trieuChung += ", ";
            trieuChung += khacTF.getText();
        }

        toKhai.setTrieuChung(trieuChung);

        /**
         * Tạo kết nối với database, chèn tờ khai mới vào bảng tokhaiytehangngay
         */
        ToKhaiYTeHangNgayGiaoTiep.insertToKhaiYTeHangNgay(toKhai);

        /**
         * Tạo Alert thông báo gửi tờ khai thành công
         */
        Alert guiThanhCongAlert = new Alert(Alert.AlertType.INFORMATION);
        guiThanhCongAlert.setTitle("Thông báo");
        guiThanhCongAlert.setHeaderText("Lời nhắn:");
        guiThanhCongAlert.setContentText("Tờ khai y tế của bạn đã gửi thành công!");
        guiThanhCongAlert.show();

        /**
         * Quay lại menu case 4
         */
        quayLaiButtonHandler(new ActionEvent());
    }

    /**
     * Quay lại case 4 Menu
     *
     * @param clickQuayLaiButton
     * @throws IOException
     */
    public void quayLaiButtonHandler(ActionEvent clickQuayLaiButton) throws IOException {
        FXMLLoader case4MenuLoader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case4Menu.fxml"));
        case4MenuLoader.setLocation(url);

        Parent parent = case4MenuLoader.load();

        Case4MenuController controller = case4MenuLoader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }
}
