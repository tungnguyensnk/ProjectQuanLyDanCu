package com.project1.Case3;

import com.project1.Main.GiaoTiep;
import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Bảng lịch sinh hoạt
 */

public class Change31a implements Initializable {
    public AnchorPane panel31;
    public TableView<LichSH> lichsh_table;
    public TableColumn<LichSH, Integer> stt;
    public TableColumn<LichSH, String> NgayThang;
    public TableColumn<LichSH, String> DiaDiem;
    public TableColumn<LichSH, String> NoiDung;
    public ObservableList<LichSH> lichSH_list;
    public Button troVe;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    /**
     * Hiển thị thông báo của hàng trong bảng
     *
     * @throws IOException
     */
    public void Show() throws IOException {
        LichSH now = lichsh_table.getSelectionModel().getSelectedItem();
        if (now != null) {
            Change31b.now = now;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Change31b.fxml"));
            Parent pr = loader.load();
            Change31b controller = loader.getController();
            controller.setMenu(menu);
            menu.contentRoot.getChildren().clear();
            menu.contentRoot.getChildren().add(pr);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * lấy dữ liệu lichsh và tạo bảng
         */
        try {
            lichSH_list = FXCollections.observableArrayList(GiaoTiep.getLich());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //khai báo kiểu của column và set giá trị điền vào từ lichSH_list
        stt.setCellValueFactory(new PropertyValueFactory<>("stt"));
        stt.setStyle("-fx-alignment: CENTER;");
        NgayThang.setCellValueFactory(new PropertyValueFactory<>("NgayThang"));
        NgayThang.setStyle("-fx-alignment: CENTER;");
        DiaDiem.setCellValueFactory(new PropertyValueFactory<>("DiaDiem"));
        DiaDiem.setStyle("-fx-alignment: CENTER;");
        NoiDung.setCellValueFactory(new PropertyValueFactory<>("NoiDung"));
        NoiDung.setStyle("-fx-alignment: CENTER;");
        lichsh_table.setItems(lichSH_list);

        //set style cho bảng
        lichsh_table.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../table.css")).toExternalForm());
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change3.fxml"));
        Parent pr = loader.load();
        Change3 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }
}
