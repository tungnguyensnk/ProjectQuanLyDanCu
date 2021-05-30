package com.project1.Case4;

import com.project1.Main.Menu;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Tạo bảng các tờ khai đã nộp để cán bộ y tế theo dõi
 */
public class Case42Controller implements Initializable {
    /**
     * truyền Menu chính
     */
    Menu menu;
    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    @FXML private TableView table;

    @FXML private TableColumn<ToKhaiYTeHangNgay, String> ngayNopColumn;
    @FXML private TableColumn<ToKhaiYTeHangNgay, String> hoVaTenColumn;
    @FXML private TableColumn<ToKhaiYTeHangNgay, String> idNhanKhauColumn;
    @FXML private TableColumn<ToKhaiYTeHangNgay, String> soDienThoaiColumn;
    @FXML private TableColumn<ToKhaiYTeHangNgay, String> trangThaiColumn;

    ObservableList<ToKhaiYTeHangNgay> toKhaiList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * Lấy danh sách tờ khai từ database
         */
        try {
            toKhaiList = FXCollections.observableArrayList(ToKhaiYTeHangNgayGiaoTiep.getToKhaiYTeHangNgay());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /**
         * Chỉ định thông tin hiện trong mỗi ô của từng cột
         */
        ngayNopColumn.setCellValueFactory(new PropertyValueFactory<ToKhaiYTeHangNgay, String>("ngayNop"));
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<ToKhaiYTeHangNgay, String>("hoVaTen"));
        idNhanKhauColumn.setCellValueFactory(new PropertyValueFactory<ToKhaiYTeHangNgay, String>("idNhanKhau"));
        soDienThoaiColumn.setCellValueFactory(new PropertyValueFactory<ToKhaiYTeHangNgay, String>("soDienThoai"));
        trangThaiColumn.setCellValueFactory(cellData -> {
            boolean daXem = cellData.getValue().getDaXem();

            String showedString;
            if (daXem) {
                showedString = "Đã xem";
            } else {
                showedString = "Chưa xem";
            }

            return new ReadOnlyStringWrapper(showedString);
        });

        table.setItems(toKhaiList);
        ngayNopColumn.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().addAll(ngayNopColumn, trangThaiColumn);
    }

    public void chiTietButtonHandler(ActionEvent clickChiTietButton) {
        /**
         * Lấy tờ khai được chọn
         */
        ToKhaiYTeHangNgay toKhai = (ToKhaiYTeHangNgay) table.getSelectionModel().getSelectedItem();

        /**
         * Tạo dialog
         */
        Dialog<Void> chiTietDialog = new Dialog<>();
        chiTietDialog.setTitle("Chi tiết tờ khai y tế");
        chiTietDialog.getDialogPane().setMinHeight(350);
        chiTietDialog.getDialogPane().setMinWidth(450);
        chiTietDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        /**
         * Tạo layout GridPane hiển thị thông tin trong tờ khai
         */
        GridPane chiTietGrid = new GridPane();
        chiTietGrid.setHgap(10);
        chiTietGrid.setVgap(10);

        Label hoVaTenLabel = new Label("- Họ và tên: ");
        Label ngayNopLabel = new Label("- Ngày nộp tờ khai: ");
        Label idNhanKhauLabel = new Label("- ID nhân khẩu: ");
        Label soDienThoaiLabel = new Label("- Số điện thoại: ");
        Label denNoiCoDichLabel = new Label("- Đến nới có dịch lưu hành: ");
        Label tiepXucLabel = new Label("- Tiếp xúc với người xác định hoặc nghi nhiễm Covid-19: ");
        Label trieuChungLabel = new Label("- Triệu chứng: ");

        Label hoVaTenNoiDungLabel = new Label(toKhai.getHoVaTen());
        Label ngayNopNoiDungLabel = new Label(toKhai.getNgayNop());
        Label idNhanKhauNoiDungLabel = new Label(toKhai.getIdNhanKhau());
        Label soDienThoaiNoiDungLabel = new Label(toKhai.getSoDienThoai());

        Label denNoiCoDichNoiDungLabel = new Label();
        if (toKhai.getDenNoiCoDich())  {
            denNoiCoDichNoiDungLabel.setText("Có");
        } else {
            denNoiCoDichNoiDungLabel.setText("Không");
        }

        Label tiepXucNoiDungLabel = new Label("Tiếp xúc với người xác định hoặc nghi nhiễm Covid-19: ");
        if (toKhai.getTiepXuc()) {
            tiepXucNoiDungLabel.setText("Có");
        } else {
            tiepXucNoiDungLabel.setText("Không");
        }

        Label trieuChungNoiDungLabel = new Label(toKhai.getTrieuChung());

        chiTietGrid.add(hoVaTenLabel, 0, 0);
        chiTietGrid.add(ngayNopLabel, 0, 1);
        chiTietGrid.add(idNhanKhauLabel, 0, 2);
        chiTietGrid.add(soDienThoaiLabel, 0, 3);
        chiTietGrid.add(denNoiCoDichLabel, 0, 4);
        chiTietGrid.add(tiepXucLabel, 0, 5);
        chiTietGrid.add(trieuChungLabel, 0, 6);
        chiTietGrid.add(trieuChungNoiDungLabel, 0, 7);

        chiTietGrid.add(hoVaTenNoiDungLabel, 1, 0);
        chiTietGrid.add(ngayNopNoiDungLabel, 1, 1);
        chiTietGrid.add(idNhanKhauNoiDungLabel, 1, 2);
        chiTietGrid.add(soDienThoaiNoiDungLabel, 1, 3);
        chiTietGrid.add(denNoiCoDichNoiDungLabel, 1, 4);
        chiTietGrid.add(tiepXucNoiDungLabel, 1, 5);

        chiTietDialog.getDialogPane().setContent(chiTietGrid);

        chiTietDialog.showAndWait();
    }

    /**
     * Đổi trạng thái của tờ khai đang được chọn thành đã xem
     *
     * @param clickDaXemButton
     * @throws SQLException
     */
    public void daXemButtonHandler(ActionEvent clickDaXemButton) throws SQLException {
        ToKhaiYTeHangNgay toKhai = (ToKhaiYTeHangNgay) table.getSelectionModel().getSelectedItem();

        toKhai.setDaXem(true);
        ToKhaiYTeHangNgayGiaoTiep.updateDaXem(toKhai);

        table.refresh();
    }

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
