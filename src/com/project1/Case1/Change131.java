package com.project1.Case1;

import com.project1.Main.Alert;
import com.project1.Main.Menu;
import com.project1.Main.*;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * chức năng 1-3-1: thêm nhân khẩu
 */
public class Change131 implements Initializable {
    public Button troVe;
    public TextField hoTenField;
    public ChoiceBox<String> soHoKhauChoice;
    public DatePicker ngaySinhPick;
    public RadioButton namRadioButton;
    public RadioButton nuRadioButton;
    public TextField noiSinhField;
    public TextField nguyenQuanField;
    public TextField danTocField;
    public TextField tonGiaoField;
    public TextField ngheNghiepField;
    public TextField noiLamViecField;
    public TextField CMNDField;
    public TextField noiCapField;
    public DatePicker ngayCapPick;
    public DatePicker ndkThuongTruField;
    public TextField quanHeCHField;
    public TextField diaChiThuongTrutruocField;
    public Button themButton;
    public ToggleGroup sex = new ToggleGroup();
    com.project1.Main.Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        namRadioButton.setToggleGroup(sex);
        nuRadioButton.setToggleGroup(sex);
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ArrayList<HoKhau> hoKhauArrayList = GiaoTiep.getHoKhau();
            for (HoKhau hoKhau : hoKhauArrayList) {
                arrayList.add(String.valueOf(hoKhau.getIdho()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ObservableList<String> idList = FXCollections.observableList(arrayList);
        soHoKhauChoice.setItems(idList);
        BooleanBinding hoTenVaid = Bindings.createBooleanBinding(() ->
                !hoTenField.getText().trim().isEmpty(), hoTenField.textProperty());
        BooleanBinding soHoKhauValid = Bindings.createBooleanBinding(() ->
                soHoKhauChoice.getValue() != null, soHoKhauChoice.valueProperty());
        BooleanBinding ngaySinhValid = Bindings.createBooleanBinding(() ->
                ngaySinhPick.getValue() != null, ngaySinhPick.valueProperty());
        namRadioButton.setSelected(true);
        BooleanBinding noiSinhValid = Bindings.createBooleanBinding(() ->
                !noiSinhField.getText().trim().isEmpty(), noiSinhField.textProperty());
        themButton.disableProperty().bind(soHoKhauValid.not().or(ngaySinhValid.not().
                or(noiSinhValid).not().or(hoTenVaid.not())));
    }

    public void troVe(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void them(ActionEvent actionEvent) throws SQLException, IOException {
        String gioi;
        if (namRadioButton.isSelected())
            gioi = "Nam";
        else
            gioi = "Nữ";
        String nc, ndk;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (ngayCapPick.getValue() != null)
            nc = ngayCapPick.getValue().format(dateTimeFormatter);
        else
            nc = "";
        if (ndkThuongTruField.getValue() != null)
            ndk = ndkThuongTruField.getValue().format(dateTimeFormatter);
        else
            ndk = "";

        GiaoTiep.themNhanKhau(new NhanKhau(Integer.parseInt(soHoKhauChoice.getValue()), quanHeCHField.getText(), hoTenField.getText(), gioi, ngaySinhPick.getValue().format(dateTimeFormatter),
                noiSinhField.getText(), noiSinhField.getText(), danTocField.getText(), ngheNghiepField.getText(), noiLamViecField.getText(),
                CMNDField.getText(), nc, noiCapField.getText(), ndk,
                diaChiThuongTrutruocField.getText(), ""));
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Thêm nhân khẩu thành công");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(themButton.getScene().getWindow().getX() + 430);
        alert1.setY(themButton.getScene().getWindow().getY() + 400);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
    }
}
