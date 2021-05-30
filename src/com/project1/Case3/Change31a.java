package com.project1.Case3;

import com.project1.Main.GiaoTiep;
import com.project1.Main.LichSH;
import com.project1.Main.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Bảng lịch sinh hoạt
 */

public class Change31a implements  Initializable{
    public AnchorPane panel31;
    public TableView<LichSH> lichsh_table;
    public TableColumn<LichSH, Integer> stt;
    public TableColumn<LichSH, String> NgayThang;
    public TableColumn<LichSH, String> DiaDiem;
    public TableColumn<LichSH, String> NoiDung;
    public ObservableList<LichSH> lichSH_list;
    public TextField filterField;
    public Button Back;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }



    public void getBack(ActionEvent event) throws IOException {     /// Trở về menu sinh hoạt
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Change3.fxml"));
        Parent pr = loader.load();
        Change3 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void Show(MouseEvent event) throws IOException {     //// Hiển thị thông báo của hàng trong bảng
        LichSH now = lichsh_table.getSelectionModel().getSelectedItem();
        if(now != null)
        {
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
        //lichsh_table.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../table.css")).toExternalForm());

//        //lắng nghe dữ liệu từ filterField và check dữ liệu hợp lệ
//        FilteredList<LichSH> filteredList = new FilteredList<>(lichSH_list, b -> true);
//        filterField.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(e -> {
//            if (newValue == null || newValue.isEmpty()) {
//                return true;
//            }
//            String lowerCaseFilter = newValue.toLowerCase();
//            if (String.valueOf(e.getNgayThang()).toLowerCase().contains(lowerCaseFilter)) {
//                return true;
//            } else return  (e.getNoiDung().toLowerCase().contains(lowerCaseFilter));
//        }));
//
//        //đồng bộ nó với bảng
//        SortedList<LichSH> sortedList = new SortedList<>(filteredList);
//        sortedList.comparatorProperty().bind(lichsh_table.comparatorProperty());
//        lichsh_table.setItems(sortedList);
//
////        //menu chuột phải
////        ContextMenu cm = new ContextMenu();
////        MenuItem chiTiet = new MenuItem("Chi tiết");
////        MenuItem ghiChu = new MenuItem("Ghi chú");
////        cm.getItems().addAll(chiTiet, ghiChu);
////
////        lichsh_table.addEventHandler(MouseEvent.MOUSE_CLICKED, t -> {
////            if (t.getButton() == MouseButton.SECONDARY) {
////                cm.show(lichsh_table, t.getScreenX(), t.getScreenY());
////            }
////            if (t.getButton() == MouseButton.PRIMARY) {
////                cm.hide();
////            }
////        });
////        chiTiet.setOnAction(actionEvent -> {
////
////        });
    }


}
