package com.project1.Case4;

import com.project1.Main.Menu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    @FXML
    private TextField hoVaTenTF;
    @FXML
    private TextField idNhanKhauTF;
    @FXML
    private TextField soDienThoaiTF;

    @FXML
    private RadioButton yesDenNoiCoDichRB;
    @FXML
    private RadioButton noDenNoiCoDichRB;

    @FXML
    private RadioButton yesTiepXucRB;
    @FXML
    private RadioButton noTiepXucRB;

    @FXML
    private CheckBox hoCB;
    @FXML
    private CheckBox sotCB;
    @FXML
    private CheckBox dauHongCB;
    @FXML
    private CheckBox khoThoCB;
    @FXML
    private CheckBox khacCB;

    @FXML
    private TextArea khacTA;

    private final ToggleGroup denNoiCoDichTG = new ToggleGroup();
    private final ToggleGroup tiepXucTG = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        yesDenNoiCoDichRB.setToggleGroup(denNoiCoDichTG);
        noDenNoiCoDichRB.setToggleGroup(denNoiCoDichTG);
        yesTiepXucRB.setToggleGroup(tiepXucTG);
        noTiepXucRB.setToggleGroup(tiepXucTG);

        khacTA.setDisable(true);
        khacCB.selectedProperty().addListener((observableValue, oldBoolean, newBoolean) -> {
            Boolean booleanVal = (1 - (newBoolean ? 1 : 0)) > 0;
            khacTA.setDisable(booleanVal);
        });
    }

    /**
     * Bắt sự kiện cho nút Gửi
     */
    public void guiButtonHandler() throws SQLException, IOException {
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

        if (hoCB.isSelected())
            trieuChung += "ho\n";

        if (sotCB.isSelected())
            trieuChung += "sốt\n";

        if (dauHongCB.isSelected())
            trieuChung += "đau họng\n";

        if (khoThoCB.isSelected())
            trieuChung += "khó thở\n";

        if (khacCB.isSelected())
            trieuChung += khacTA.getText();

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
        quayLaiButtonHandler();
    }

    /**
     * Quay lại case 4 Menu
     *
     * @throws IOException
     */
    public void quayLaiButtonHandler() throws IOException {
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
