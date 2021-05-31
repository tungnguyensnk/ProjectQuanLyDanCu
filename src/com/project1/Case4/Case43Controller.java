package com.project1.Case4;

import com.project1.Main.GiaoTiep;
import com.project1.Main.NhanKhau;
import com.project1.Main.Menu;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.ResourceBundle;

public class Case43Controller implements Initializable {
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    @FXML private TextField timKiemTF;

    @FXML private TableView<SucKhoeNguoiDan> table;

    @FXML private TableColumn<SucKhoeNguoiDan, Integer> idNhanKhauColumn;
    @FXML private TableColumn<SucKhoeNguoiDan, Integer> idHoKhauColumn;
    @FXML private TableColumn<SucKhoeNguoiDan, String> hoVaTenColumn;
    @FXML private TableColumn<SucKhoeNguoiDan, String> gioiTinhColumn;
    @FXML private TableColumn<SucKhoeNguoiDan, String> ngaySinhColumn;
    @FXML private TableColumn<SucKhoeNguoiDan, String> tinhTrangColumn;

    ObservableList<SucKhoeNguoiDan> tableContentList;

    ArrayList<NhanKhau> nhanKhauList;
    ArrayList<SucKhoeNguoiDan> sucKhoeList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Nếu có nhân khẩu mới trong tổ dân phố, hoặc tách hộ
         * -> Cập nhật vào bảng Sức khỏe người dân
         */
        try {
            nhanKhauList = GiaoTiep.getNhanKhau();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            sucKhoeList = SucKhoeNguoiDanGiaoTiep.get();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for (NhanKhau curNhanKhau: nhanKhauList) {
            boolean found = false;

            for (SucKhoeNguoiDan curSucKhoe: sucKhoeList) {
                if (curNhanKhau.getId() == curSucKhoe.getIdNhanKhau()) {
                    found = true;

                    /**
                     * Tách hộ
                     */
                    if (curNhanKhau.getIdho() != curSucKhoe.getIdHoKhau()) {
                        curSucKhoe.setIdHoKhau(curNhanKhau.getIdho());

                        try {
                            SucKhoeNguoiDanGiaoTiep.update(curSucKhoe);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }
            }

            /**
             * Nhân khẩu mới
             */
            if (!found) {
                SucKhoeNguoiDan tmp = new SucKhoeNguoiDan();

                tmp.setIdNhanKhau(curNhanKhau.getId());
                tmp.setIdHoKhau(curNhanKhau.getIdho());
                tmp.setHoVaTen(curNhanKhau.getHoten());
                tmp.setGioiTinh(curNhanKhau.getGioitinh());
                tmp.setNgaySinh(curNhanKhau.getNgaysinh());
                tmp.setCmnd_cccd(curNhanKhau.getCmnd());

                try {
                    SucKhoeNguoiDanGiaoTiep.insert(tmp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        /**
         * Tạo bảng Sức khỏe người dân
         */
        try {
            tableContentList = FXCollections.observableArrayList(SucKhoeNguoiDanGiaoTiep.get());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        idHoKhauColumn.setCellValueFactory(new PropertyValueFactory<SucKhoeNguoiDan, Integer>("idHoKhau"));
        idNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<SucKhoeNguoiDan, Integer>("idNhanKhau"));
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<SucKhoeNguoiDan, String>("hoVaTen"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<SucKhoeNguoiDan, String>("gioiTinh"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<SucKhoeNguoiDan, String>("ngaySinh"));
        tinhTrangColumn.setCellValueFactory(cellData -> {
            int curTinhTrang = cellData.getValue().getTinhTrang();

            String showedString = null;
            if (curTinhTrang == 0)
                showedString = "F0";
            if (curTinhTrang == 1)
                showedString = "F1";
            if (curTinhTrang == 2)
                showedString = "F2";
            if (curTinhTrang == 3)
                showedString = "F3";
            if (curTinhTrang == 4)
                showedString = "Bình thường";

            return new ReadOnlyStringWrapper(showedString);
        });

        /**
         * Chức năng tìm kiếm
         */
        FilteredList<SucKhoeNguoiDan> filteredList = new FilteredList<>(tableContentList, predicate -> true);

        timKiemTF.textProperty().addListener((observableValue, oldString, newString) -> {
            /**
             * Với mỗi bản ghi, xét xem có được hiện hay không
             */
            filteredList.setPredicate(sucKhoeRecord -> {
                /**
                 * Không nhập gì
                 */
                if (newString == null || newString.isEmpty()) {
                    return true;
                }

                /**
                 * Có nhập
                 */
                if (Integer.toString(sucKhoeRecord.getIdHoKhau()).toLowerCase().contains(newString.toLowerCase())) {
                    return true; // Trùng ID hộ khẩu
                } else if (Integer.toString(sucKhoeRecord.getIdNhanKhau()).toLowerCase().contains(newString.toLowerCase())) {
                    return true; // Trùng ID nhân khẩu
                } else if (sucKhoeRecord.getHoVaTen().toLowerCase().contains(newString.toLowerCase())) {
                    return true; // Trùng họ và tên
                }

                // Kiểm tra trùng cho cột Tình trạng
                if (sucKhoeRecord.getTinhTrang() == 0) {
                    if ("F0".toLowerCase().contains(newString.toLowerCase())){
                        return true;
                    }
                }

                if (sucKhoeRecord.getTinhTrang() == 1) {
                    if ("F1".toLowerCase().contains(newString.toLowerCase())){
                        return true;
                    }
                }

                if (sucKhoeRecord.getTinhTrang() == 2) {
                    if ("F2".toLowerCase().contains(newString.toLowerCase())){
                        return true;
                    }
                }

                if (sucKhoeRecord.getTinhTrang() == 3) {
                    if ("F3".toLowerCase().contains(newString.toLowerCase())){
                        return true;
                    }
                }

                if (sucKhoeRecord.getTinhTrang() == 4) {
                    if ("Bình thường".toLowerCase().contains(newString.toLowerCase())){
                        return true;
                    }
                }

                /**
                 * Không cột vào trùng
                 */
                return false;
            });
        });

        /**
         * Sau khi lọc qua thanh tìm kiếm, cho danh sách vào bảng
         */
        SortedList<SucKhoeNguoiDan> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(table.comparatorProperty());

        table.setItems(sortedList);

        table.getSortOrder().add(idHoKhauColumn);
    }

    /**
     * Nút xóa thông tin trong thanh tìm kiếm
     *
     * @param actionEvent
     */
    public void xTimKiemButtonHandler(ActionEvent actionEvent) {
        timKiemTF.setText(null);
    }

    /**
     * Hiển thị chi tiết nội dung bản ghi trong bảng Sức khỏe người dân
     *
     * @param clickChiTietButton
     */
    public void chiTietButtonHandler(ActionEvent clickChiTietButton) {
        /**
         * Lấy bản ghi được chọn
         */
        SucKhoeNguoiDan selected = (SucKhoeNguoiDan) table.getSelectionModel().getSelectedItem();

        /**
         * Tạo dialog
         */
        Dialog<Void> chiTietDialog = new Dialog<>();
        chiTietDialog.setTitle("Chi tiết tình trạng sức khỏe");
        chiTietDialog.getDialogPane().setMinWidth(500);
        chiTietDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        /**
         * Tạo layout chứa nội dung chi tiết
         */
        GridPane thongTinGrid = new GridPane();
        thongTinGrid.setVgap(10);
        thongTinGrid.setHgap(10);

        Label idHoKhauLabel = new Label("- ID hộ khẩu: ");
        Label idNhanKhauLabel = new Label("- ID nhân khẩu: ");
        Label hoVaTenLabel = new Label("- Họ và tên: ");
        Label gioiTinhLabel = new Label("- Giới tính: ");
        Label ngaySinhLabel = new Label("- Ngày sinh: ");
        Label cmnd_cccdLabel = new Label("- CMND/CCCD: ");
        Label tinhTrangLabel = new Label("- Tình trạng: ");
        Label nguonLayLabel = new Label("- Nguồn lây Covid-19: ");
        Label noiCachLyLabel = new Label("- Nơi cách lý: ");
        Label ngayBatDauCachLyLabel = new Label("- Bắt đầu cách ly: ");
        Label noiDieuTriLabel = new Label("- Nơi điều trị: ");
        Label ngayBatDaiDieuTriLabel = new Label("- Bắt đầu điều trị: ");

        Label idHoKhauNoiDungLabel = new Label(Integer.toString(selected.getIdHoKhau()));
        Label idNhanKhauNoiDungLabel = new Label(Integer.toString(selected.getIdNhanKhau()));
        Label hoVaTenNoiDungLabel = new Label(selected.getHoVaTen());
        Label gioiTinhNoiDungLabel = new Label(selected.getGioiTinh());
        Label ngaySinhNoiDungLabel = new Label(selected.getNgaySinh());
        Label cmnd_cccdNoiDungLabel = new Label(selected.getCmnd_cccd());
        Label tinhTrangNoiDungLabel = new Label();
        if (selected.getTinhTrang() == 0)
            tinhTrangNoiDungLabel.setText("F0");
        if (selected.getTinhTrang() == 1)
            tinhTrangNoiDungLabel.setText("F1");
        if (selected.getTinhTrang() == 2)
            tinhTrangNoiDungLabel.setText("F2");
        if (selected.getTinhTrang() == 3)
            tinhTrangNoiDungLabel.setText("F3");
        if (selected.getTinhTrang() == 4)
            tinhTrangNoiDungLabel.setText("Bình thường");
        Label nguonLayNoiDungLabel = new Label(selected.getNguonLayCovid19());
        Label noiCachLyNoiDungLabel = new Label(selected.getNoiCachLy());
        Label ngayBatDauCachLyNoiDungLabel = new Label(selected.getNgayBatDauCachLy());
        Label noiDieuTriNoiDungLabel = new Label(selected.getNoiDieuTri());
        Label ngayBatDaiDieuTriNoiDungLabel = new Label(selected.getNgayBatDauDieuTri());

        Label ketQuaTestCovidLabel = new Label("- Kết quả xét nghiệm: ");
        Label test1Label = new Label("+ Lần 1: ");
        Label test2Label = new Label("+ Lần 2: ");
        Label test3Label = new Label("+ Lần 3: ");

        Label test1NoiDungLabel = new Label();
        if (selected.getKetQuaTest1() == 1)
            test1NoiDungLabel.setText("Dương tính");
        if (selected.getKetQuaTest1() == 0)
            test1NoiDungLabel.setText("Âm tính");

        Label test2NoiDungLabel = new Label();
        if (selected.getKetQuaTest2() == 1)
            test2NoiDungLabel.setText("Dương tính");
        if (selected.getKetQuaTest2() == 0)
            test2NoiDungLabel.setText("Âm tính");

        Label test3NoiDungLabel = new Label();
        if (selected.getKetQuaTest3() == 1)
            test3NoiDungLabel.setText("Dương tính");
        if (selected.getKetQuaTest3() == 0)
            test3NoiDungLabel.setText("Âm tính");

        /**
         * cột 0 trong GridPane
         */
        thongTinGrid.add(idHoKhauLabel, 0, 0);
        thongTinGrid.add(idNhanKhauLabel, 0, 1);
        thongTinGrid.add(hoVaTenLabel, 0, 2);
        thongTinGrid.add(gioiTinhLabel, 0, 3);
        thongTinGrid.add(ngaySinhLabel, 0, 4);
        thongTinGrid.add(cmnd_cccdLabel, 0, 5);
        thongTinGrid.add(tinhTrangLabel, 0, 6);
        thongTinGrid.add(nguonLayLabel, 0, 7);
        thongTinGrid.add(noiCachLyLabel, 0, 8);
        thongTinGrid.add(ngayBatDauCachLyLabel, 0, 9);
        thongTinGrid.add(noiDieuTriLabel, 0, 10);
        thongTinGrid.add(ngayBatDaiDieuTriLabel, 0, 11);
        thongTinGrid.add(ketQuaTestCovidLabel, 0, 12);

        /**
         * Cột 1 trong GridPane
         */
        thongTinGrid.add(idHoKhauNoiDungLabel, 1, 0);
        thongTinGrid.add(idNhanKhauNoiDungLabel, 1, 1);
        thongTinGrid.add(hoVaTenNoiDungLabel, 1, 2);
        thongTinGrid.add(gioiTinhNoiDungLabel, 1, 3);
        thongTinGrid.add(ngaySinhNoiDungLabel, 1, 4);
        thongTinGrid.add(cmnd_cccdNoiDungLabel, 1, 5);
        thongTinGrid.add(tinhTrangNoiDungLabel, 1, 6);
        thongTinGrid.add(nguonLayNoiDungLabel, 1, 7);
        thongTinGrid.add(noiCachLyNoiDungLabel, 1, 8);
        thongTinGrid.add(ngayBatDauCachLyNoiDungLabel, 1, 9);
        thongTinGrid.add(noiDieuTriNoiDungLabel, 1, 10);
        thongTinGrid.add(ngayBatDaiDieuTriNoiDungLabel, 1, 11);
        thongTinGrid.add(test1Label, 1, 12);
        thongTinGrid.add(test2Label, 1, 13);
        thongTinGrid.add(test3Label, 1, 14);

        /**
         * Cột 3 trong GridPane
         */
        thongTinGrid.add(test1NoiDungLabel, 2, 12);
        thongTinGrid.add(test2NoiDungLabel, 2, 13);
        thongTinGrid.add(test3NoiDungLabel, 2, 14);

        /**
         * Đưa GridPane vào Dialog
         */
        chiTietDialog.getDialogPane().setContent(thongTinGrid);

        chiTietDialog.showAndWait();
    }

    /**
     * Chỉnh sửa bản ghi sức khỏe người dân
     *
     * @param clickChinhSuaButton
     */
    public void chinhSuaButtonHandler(ActionEvent clickChinhSuaButton) throws IOException {
        /**
         * Lấy bản ghi đang được chọn
         */
        SucKhoeNguoiDan selected = (SucKhoeNguoiDan) table.getSelectionModel().getSelectedItem();
        Case43aController.setSelectedRecord(selected);

        /**
         * Chuyển sang case 4.3.a
         */
        FXMLLoader case43aLoader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("Case43a.fxml"));
        case43aLoader.setLocation(url);

        Parent parent = case43aLoader.load();

        Case43aController controller = case43aLoader.getController();
        controller.setMenu(menu);

        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(parent);
    }

    /**
     * Quay lại menu Case 4
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
