package com.project1.Case4;

import com.project1.Main.Menu;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
        try {
            toKhaiList = FXCollections.observableArrayList(ToKhaiYTeHangNgayGiaoTiep.getToKhaiYTeHangNgay());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
    }

    public void chiTietButtonHandler(ActionEvent clickChiTietButton) {

    }

    public void quayLaiButtonHandler(ActionEvent clickQuayLaiButton) {

    }
}
