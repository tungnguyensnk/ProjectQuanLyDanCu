package com.project1.Case2;

import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import com.project1.Main.Menu;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Change22 implements Initializable {
    public Button troVe;
    public TableView table;
    public TableColumn hoTenCol;
    public TableColumn ngayCol;
    public TableColumn phanLoaiCol;
    public TableColumn noiDungCol;
    public TextField filterField;
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }

    void setItems() {
        /**
         * lấy dữ liệu hộ khẩu và tạo bảng
         */
        //hoKhauList = FXCollections.observableArrayList(GiaoTiep.getHoKhau());

        //khai báo kiểu của column và set giá trị điền vào từ hoKhauList
        hoTenCol.setCellValueFactory(new PropertyValueFactory<>("hoten"));
        hoTenCol.setStyle("-fx-alignment: CENTER;");
        ngayCol.setCellValueFactory(new PropertyValueFactory<>("ngay"));
        ngayCol.setStyle("-fx-alignment: CENTER;");
        phanLoaiCol.setCellValueFactory(new PropertyValueFactory<>("phanloai"));
        phanLoaiCol.setStyle("-fx-alignment: CENTER;");
        noiDungCol.setCellValueFactory(new PropertyValueFactory<>("noidung"));
        noiDungCol.setStyle("-fx-alignment: CENTER;");
        //table.setItems(hoKhauList);

        //set style cho bảng
        table.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../table.css")).toExternalForm());
        //lắng nghe dữ liệu từ filterField và check dữ liệu hợp lệ
        /*FilteredList<HoKhau> filteredList = new FilteredList<>(hoKhauList, b -> true);
        filterField.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(e -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (String.valueOf(e.getIdho()).toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (e.getHotenchu().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else return e.getDiachi().toLowerCase().contains(lowerCaseFilter);
        }));

        //đồng bộ nó với bảng
        SortedList<HoKhau> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedList);*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems();

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
