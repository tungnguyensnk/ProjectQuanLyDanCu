package com.project1.Case2;

import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import com.project1.Main.Menu;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Change22 implements Initializable {
    public Button troVe;
    public TableView<NoiDungPhanAnh> table;
    public TableColumn<NoiDungPhanAnh, String> hoTenCol;
    public TableColumn<NoiDungPhanAnh, String> ngayCol;
    public TableColumn<NoiDungPhanAnh, String> phanLoaiCol;
    public TableColumn<NoiDungPhanAnh, String> noiDungCol;
    public TextField filterField;
    public ObservableList<NoiDungPhanAnh> list;
    public TableColumn daXemCol;
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    void setItems() throws SQLException {
        /**
         * lấy dữ liệu hộ khẩu và tạo bảng
         */
        list = FXCollections.observableArrayList(GiaoTiep.getPhanAnh());

        //khai báo kiểu của column và set giá trị điền vào từ hoKhauList
        hoTenCol.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        hoTenCol.setStyle("-fx-alignment: CENTER;");
        ngayCol.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        ngayCol.setStyle("-fx-alignment: CENTER;");
        phanLoaiCol.setCellValueFactory(new PropertyValueFactory<>("phanLoai"));
        phanLoaiCol.setStyle("-fx-alignment: CENTER;");
        noiDungCol.setCellValueFactory(new PropertyValueFactory<>("noiDung"));
        noiDungCol.setStyle("-fx-alignment: CENTER;");
        daXemCol.setCellValueFactory(new PropertyValueFactory<>("daXem"));
        daXemCol.setStyle("-fx-alignment: CENTER;");
        table.setItems(list);

        //set style cho bảng
        table.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../table.css")).toExternalForm());
        //lắng nghe dữ liệu từ filterField và check dữ liệu hợp lệ
        FilteredList<NoiDungPhanAnh> filteredList = new FilteredList<>(list, b -> true);
        filterField.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(e -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (e.getHoTen().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (e.getNgay().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (e.getPhanLoai().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else return e.getNoiDung().toLowerCase().contains(lowerCaseFilter);
        }));

        //đồng bộ nó với bảng
        SortedList<NoiDungPhanAnh> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //menu chuột phải
        ContextMenu cm = new ContextMenu();
        MenuItem daXem = new MenuItem("Đã xem");
        MenuItem daGQ = new MenuItem("Đã giải quyết");
        cm.getItems().addAll(daXem, daGQ);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, context -> {
            if (context.getButton() == MouseButton.SECONDARY) {
                cm.show(table, context.getScreenX(), context.getScreenY());
            }
            if (context.getButton() == MouseButton.PRIMARY) {
                cm.hide();
            }
        });

        daXem.setOnAction(actionEvent -> {
            try {
                GiaoTiep.setTinhTrang(table.getSelectionModel().getSelectedItems().get(0).getId(), "Đã xem");
                setItems();
                table.refresh();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        daGQ.setOnAction(actionEvent -> {
            try {
                GiaoTiep.setTinhTrang(table.getSelectionModel().getSelectedItems().get(0).getId(), "Đã giải quyết");
                setItems();
                table.refresh();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        table.setRowFactory(noiDungPhanAnhTableView -> new TableRow<>() {
            private Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(NoiDungPhanAnh noiDungPhanAnh, boolean b) {
                super.updateItem(noiDungPhanAnh, b);
                tooltip.setShowDelay(Duration.millis(300));
                tooltip.setHideDelay(Duration.millis(300));
                if (noiDungPhanAnh == null)
                    setTooltip(null);
                else {
                    tooltip.setText("SDT: " + noiDungPhanAnh.getSoDienThoai() + "\nDC: " + noiDungPhanAnh.getDiaChi() +
                            "\nND: " + noiDungPhanAnh.getNoiDung());
                    setTooltip(tooltip);
                }
            }
        });
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change2.fxml"));
        Parent pr = loader.load();
        Change2 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }
}
