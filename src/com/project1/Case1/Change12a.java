package com.project1.Case1;

import com.project1.Main.GiaoTiep;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * chức năng 1-2a: hiển thị nhân khẩu dạng bảng
 */
public class Change12a implements Initializable {
    public TableView<NhanKhau> table;
    public Button troVe;
    public TableColumn<NhanKhau, Integer> soHoKhauCol;
    public TableColumn<NhanKhau, String> hoTenCol;
    public TableColumn<NhanKhau, String> diaChiCol;
    public ObservableList<NhanKhau> nhanKhauList;
    public TextField filterField;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * lấy dữ liệu nhân khẩu và tạo bảng
         */
        try {
            nhanKhauList = FXCollections.observableArrayList(GiaoTiep.getNhanKhau());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //khai báo kiểu của column và set giá trị điền vào từ nhanKhauList
        soHoKhauCol.setCellValueFactory(new PropertyValueFactory<>("idho"));
        soHoKhauCol.setStyle("-fx-alignment: CENTER;");
        hoTenCol.setCellValueFactory(new PropertyValueFactory<>("hoten"));
        hoTenCol.setStyle("-fx-alignment: CENTER;");
        diaChiCol.setCellValueFactory(new PropertyValueFactory<>("noisinh"));
        diaChiCol.setStyle("-fx-alignment: CENTER;");
        table.setItems(nhanKhauList);

        //set style cho bảng
        table.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../table.css")).toExternalForm());

        //lắng nghe dữ liệu từ filterField và check dữ liệu hợp lệ
        FilteredList<NhanKhau> filteredList = new FilteredList<>(nhanKhauList, b -> true);
        filterField.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(e -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(e.getIdho()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (e.getHoten().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else return e.getNoisinh().toLowerCase().contains(lowerCaseFilter);
        }));

        //đồng bộ nó với bảng
        SortedList<NhanKhau> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

        //menu chuột phải
        ContextMenu cm = new ContextMenu();
        MenuItem chiTiet = new MenuItem("Chi tiết");
        MenuItem ghiChu = new MenuItem("Ghi chú");
        cm.getItems().addAll(chiTiet, ghiChu);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
            if (t.getButton() == MouseButton.SECONDARY) {
                cm.show(table, t.getScreenX(), t.getScreenY());
            }
            if (t.getButton() == MouseButton.PRIMARY) {
                cm.hide();
            }
        });
        chiTiet.setOnAction(actionEvent -> {

        });
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change1.fxml"));
        Parent pr = loader.load();
        Change1 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }
}
