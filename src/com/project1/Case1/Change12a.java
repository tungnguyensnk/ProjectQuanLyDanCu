package com.project1.Case1;

import com.project1.Main.Alert;
import com.project1.Main.GiaoTiep;
import com.project1.Main.HoKhau;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
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
import java.util.List;
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

    public void inExcel() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet xssfSheet = workbook.createSheet("Danh Sách Nhân Khẩu");
            List<NhanKhau> list = GiaoTiep.getNhanKhau();

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
            cell.setCellValue("STT");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Số hộ khẩu");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ tên");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới tính");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Nơi sinh");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Nguyên quán");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Dân tộc");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Nghề nghiệp");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Nơi làm việc");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("CMND");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Ngày cấp");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(12, CellType.STRING);
            cell.setCellValue("Nơi cấp");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(13, CellType.STRING);
            cell.setCellValue("Ngày ĐK thường trú");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(14, CellType.STRING);
            cell.setCellValue("Địa chỉ thường trú trước");
            cell.setCellStyle(headerStyle);

            cell = row.createCell(15, CellType.STRING);
            cell.setCellValue("Ghi chú");
            cell.setCellStyle(headerStyle);


            xssfSheet.setColumnWidth(0,2000);
            xssfSheet.setColumnWidth(1,3000);
            xssfSheet.setColumnWidth(2,7000);
            xssfSheet.setColumnWidth(3,3000);
            xssfSheet.setColumnWidth(4,5000);
            xssfSheet.setColumnWidth(5,15000);
            xssfSheet.setColumnWidth(6,15000);
            xssfSheet.setColumnWidth(7,5000);
            xssfSheet.setColumnWidth(8,7000);
            xssfSheet.setColumnWidth(9,15000);
            xssfSheet.setColumnWidth(10,5000);
            xssfSheet.setColumnWidth(11,5000);
            xssfSheet.setColumnWidth(12,15000);
            xssfSheet.setColumnWidth(13,5000);
            xssfSheet.setColumnWidth(14,14000);
            xssfSheet.setColumnWidth(15,10000);

            for (NhanKhau nhanKhau : list) {
                rownum++;
                row = xssfSheet.createRow(rownum);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(nhanKhau.getId());
                cell.setCellStyle(border);

                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(nhanKhau.getIdho());
                cell.setCellStyle(border);

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(nhanKhau.getHoten());
                cell.setCellStyle(border);

                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(nhanKhau.getGioitinh());
                cell.setCellStyle(border);

                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(nhanKhau.getNgaysinh());
                cell.setCellStyle(border);

                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(nhanKhau.getNoisinh());
                cell.setCellStyle(border);

                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(nhanKhau.getNguyenquan());
                cell.setCellStyle(border);

                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(nhanKhau.getDantoc());
                cell.setCellStyle(border);

                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(nhanKhau.getNghenghiep());
                cell.setCellStyle(border);

                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(nhanKhau.getNoilamviec());
                cell.setCellStyle(border);

                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(nhanKhau.getCmnd());
                cell.setCellStyle(border);

                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue(nhanKhau.getNgaycap());
                cell.setCellStyle(border);

                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue(nhanKhau.getNoicap());
                cell.setCellStyle(border);

                cell = row.createCell(13, CellType.STRING);
                cell.setCellValue(nhanKhau.getNdkthuongtru());
                cell.setCellStyle(border);

                cell = row.createCell(14, CellType.STRING);
                cell.setCellValue(nhanKhau.getDcthuongtrutrc());
                cell.setCellStyle(border);

                cell = row.createCell(15, CellType.STRING);
                cell.setCellValue(nhanKhau.getGhichu());
                cell.setCellStyle(border);
            }
            File file = new File(System.getProperty("user.dir") + "//nhankhau.xlsx");
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
