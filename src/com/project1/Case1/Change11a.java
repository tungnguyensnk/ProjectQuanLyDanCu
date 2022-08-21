package com.project1.Case1;

import com.project1.Main.Alert;
import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import com.project1.Main.Menu;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * chức năng 1-1a: hiển thị dạng bảng
 */
public class Change11a implements Initializable {
    public TableView<HoKhau> table;
    public Button troVe;
    public TableColumn<HoKhau, Integer> soHoKhauCol;
    public TableColumn<HoKhau, String> hoTenCol;
    public TableColumn<HoKhau, String> diaChiCol;
    public ObservableList<HoKhau> hoKhauList;
    public TextField filterField;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    void setItems() {
        /**
         * lấy dữ liệu hộ khẩu và tạo bảng
         */
        try {
            if(GiaoTiep.getUserName().equals("totruong"))
                hoKhauList = FXCollections.observableArrayList(GiaoTiep.getHoKhau());
            else
                hoKhauList = FXCollections.observableArrayList(GiaoTiep.getHoKhau(Integer.parseInt(GiaoTiep.getUserName())));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //khai báo kiểu của column và set giá trị điền vào từ hoKhauList
        soHoKhauCol.setCellValueFactory(new PropertyValueFactory<>("idho"));
        soHoKhauCol.setStyle("-fx-alignment: CENTER;");
        hoTenCol.setCellValueFactory(new PropertyValueFactory<>("hotenchu"));
        hoTenCol.setStyle("-fx-alignment: CENTER;");
        diaChiCol.setCellValueFactory(new PropertyValueFactory<>("diachi"));
        diaChiCol.setStyle("-fx-alignment: CENTER;");
        table.setItems(hoKhauList);

        //set style cho bảng
        table.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../table.css")).toExternalForm());
        //lắng nghe dữ liệu từ filterField và check dữ liệu hợp lệ
        FilteredList<HoKhau> filteredList = new FilteredList<>(hoKhauList, b -> true);
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
        table.setItems(sortedList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setItems();
        //menu chuột phải
        ContextMenu cm = new ContextMenu();
        MenuItem chiTiet = new MenuItem("Chi tiết");
        MenuItem chinhSua = new MenuItem("Chỉnh sửa");
        MenuItem map = new MenuItem("Map");
        cm.getItems().addAll(chiTiet, chinhSua, map);

        table.addEventHandler(MouseEvent.MOUSE_CLICKED, context -> {
            if (context.getButton() == MouseButton.SECONDARY) {
                cm.show(table, context.getScreenX(), context.getScreenY());
            }
            if (context.getButton() == MouseButton.PRIMARY) {
                cm.hide();
            }
        });

        //chức năng chi tiết chuột phải
        chiTiet.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("change12a.fxml"));
            Parent pr = null;
            try {
                pr = loader.load();
            } catch (IOException ignored) {
            }
            Change12a controller = loader.getController();
            controller.setMenu(menu);
            controller.filterField.setText(table.getSelectionModel().getSelectedItems().get(0).getIdho() + "");
            menu.contentRoot.getChildren().clear();
            menu.contentRoot.getChildren().add(pr);
        });

        //chức năng chỉnh sửa chuột phải
        chinhSua.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chinhSua.fxml"));
            Parent pr = null;
            try {
                pr = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChinhSua controller = loader.getController();
            controller.setIdho(table.getSelectionModel().getSelectedItems().get(0).getIdho());
            controller.setChange11a(this);
            Scene sc = new Scene(pr, 300, 400);
            sc.setFill(Color.TRANSPARENT);
            Stage mini = new Stage();
            mini.initStyle(StageStyle.TRANSPARENT);
            mini.setScene(sc);
            mini.initModality(Modality.APPLICATION_MODAL);
            mini.setX(cm.getX());
            mini.setY(cm.getY());
            mini.show();
        });

        //chức năng hiển thị map chuột phải
        map.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("map.fxml"));
            Parent pr;
            try {
                pr = loader.load();
                Mapht controller = loader.getController();
                ArrayList<Double> arrayList = GiaoTiep.getXY(table.getSelectionModel().getSelectedItems().get(0).getPlaceid());
                controller.setXY(arrayList.get(0), arrayList.get(1));
                Scene sc = new Scene(pr, 450, 350);
                sc.setFill(Color.TRANSPARENT);
                Stage mini = new Stage();
                mini.initStyle(StageStyle.TRANSPARENT);
                mini.setScene(sc);
                mini.initModality(Modality.APPLICATION_MODAL);
                mini.setX(cm.getX());
                mini.setY(cm.getY());
                mini.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    /**
     * in file Excel
     */
    public void inExcel() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet xssfSheet = workbook.createSheet("Danh Sách Hộ Khẩu");
            List<HoKhau> list = GiaoTiep.getHoKhau();

            int rownum = 0;
            Cell cell;
            Row row;

            XSSFCellStyle border = workbook.createCellStyle();
            border.setBorderTop(BorderStyle.THIN);
            border.setBorderBottom(BorderStyle.THIN);
            border.setBorderLeft(BorderStyle.THIN);
            border.setBorderRight(BorderStyle.THIN);
            border.setAlignment(HorizontalAlignment.CENTER);
            border.setVerticalAlignment(VerticalAlignment.CENTER);

            XSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.cloneStyleFrom(border);

            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerStyle.setFillBackgroundColor(IndexedColors.SEA_GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.FINE_DOTS);

            row = xssfSheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Số hộ khẩu");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Họ tên chủ hộ");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Địa chỉ");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Ghi chú");
            cell.setCellStyle(headerStyle);

            xssfSheet.setColumnWidth(0, 3000);
            xssfSheet.setColumnWidth(1, 7000);
            xssfSheet.setColumnWidth(2, 15000);
            xssfSheet.setColumnWidth(3, 10000);

            for (HoKhau hoKhau : list) {
                rownum++;
                row = xssfSheet.createRow(rownum);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(hoKhau.getIdho());
                cell.setCellStyle(border);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(hoKhau.getHotenchu());
                cell.setCellStyle(border);

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(hoKhau.getDiachi());
                cell.setCellStyle(border);

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(hoKhau.getGhichu());
                cell.setCellStyle(border);
            }
            File file = new File(System.getProperty("user.dir") + "//hokhau.xlsx");
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                workbook.write(fileOutputStream);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        //thông báo thêm thành công
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Tạo File thành công");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(troVe.getScene().getWindow().getX() + 430);
        alert1.setY(troVe.getScene().getWindow().getY() + 400);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
    }
}
