package com.project1.Case1;

import com.project1.Main.Alert;
import com.project1.Main.Menu;
import com.project1.Main.*;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
    public Label tlabel;

    public ArrayList getArr() {
        return arr;
    }

    public void setArr(ArrayList<String> arr) {
        this.arr = arr;
    }

    ArrayList<String> arr = new ArrayList<>();

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    String tmp = "";
    com.project1.Main.Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // tạo 2 RadioButton nam nữ, đồng bộ nó với Group sex
        namRadioButton.setToggleGroup(sex);
        nuRadioButton.setToggleGroup(sex);
        //tạo 1 ArrayList chứa tất cả số hộ khẩu
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ArrayList<HoKhau> hoKhauArrayList = GiaoTiep.getHoKhau();
            for (HoKhau hoKhau : hoKhauArrayList) {
                arrayList.add(String.valueOf(hoKhau.getIdho()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // gán list vào ChoiceBox
        ObservableList<String> idList = FXCollections.observableList(arrayList);
        soHoKhauChoice.setItems(idList);
        /**
         * binding
         * disable themButton nếu 1 trong các Field sau chưa có data
         */
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

        //tạo 1 luồng để gợi ý địa điểm
        Task<Void> task = new Task<>() {
            @Override
            public Void call() throws InterruptedException {
                for (int i = 0; i < 1000000; i++) {
                    Thread.sleep(1500);
                    if (!noiSinhField.getText().isEmpty() && !noiSinhField.getText().equals(getTmp())) {
                        setArr(GiaoTiep.getDanhSachViTri(noiSinhField.getText()));
                        setTmp(noiSinhField.getText());
                    }
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        //lắng nghe nội dung của noiSinhField, hiển thị gợi ý sau 2s
        noiSinhField.textProperty().addListener((observableValue, s, t1) -> {
            if (getArr().size() != 0 && !tlabel.getText().replaceAll("Gợi ý: ","").equals(noiSinhField.getText())) {
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> tlabel.setText("Gợi ý: "+ getArr().get(0)));
                delay.play();
            }
        });

        //nhấn vào Label thì seo điền vào noiSinhField
        tlabel.setOnMouseClicked(mouseEvent -> {
            noiSinhField.setText(tlabel.getText().substring(7));
            tlabel.setText("");
        });
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void them(ActionEvent actionEvent) throws SQLException, IOException {

        // có thể dùng sex.getSelectedToggle() để lấy data giới tính, ko hiểu sao dùng cách này :))
        String gioi;
        if (namRadioButton.isSelected())
            gioi = "Nam";
        else
            gioi = "Nữ";
        String nc, ndk;

        //tránh getValue trả về null
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (ngayCapPick.getValue() != null)
            nc = ngayCapPick.getValue().format(dateTimeFormatter);
        else
            nc = "";

        //tránh getValue trả về null
        if (ndkThuongTruField.getValue() != null)
            ndk = ndkThuongTruField.getValue().format(dateTimeFormatter);
        else
            ndk = "";

        //gửi lệnh thêm nhân khẩu với DB
        GiaoTiep.themNhanKhau(new NhanKhau(Integer.parseInt(soHoKhauChoice.getValue()), quanHeCHField.getText(), hoTenField.getText(), gioi, ngaySinhPick.getValue().format(dateTimeFormatter),
                noiSinhField.getText(), noiSinhField.getText(), danTocField.getText(), ngheNghiepField.getText(), noiLamViecField.getText(),
                CMNDField.getText(), nc, noiCapField.getText(), ndk,
                diaChiThuongTrutruocField.getText(), ""));

        //thông báo thêm thành công
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
