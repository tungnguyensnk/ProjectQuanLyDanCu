package com.project1.Case1;

import com.project1.Main.*;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * chức năng 1-3-3: đổi chủ và tách hộ
 */
public class Change133 implements Initializable {
    public Button troVe;
    public ChoiceBox soHoKhauChoice;
    public ChoiceBox hoTenChoice;
    public ChoiceBox nguoiChoice;
    public Label nguoiLabel;
    public Button xacNhanButton;
    public TextField soHoKhauMoi;
    public TextField diaChiMoi;
    int dem = 0;
    String s;
    ArrayList<String> nhanKhau1 = new ArrayList<>();
    ArrayList<String> nhanKhau2 = new ArrayList<>();
    ArrayList<String> listName = new ArrayList<>();

    /**
     * Danh sách tên sẽ hiển thị trên Label
     *
     * @param s
     */
    public void setS(String s) {
        listName.add(s);
        if (dem == 0) {
            this.s = s;
        } else
            this.s += ", " + s;
    }

    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void troVe(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * tạo ArrayList chứa danh sách số hộ khẩu
         */
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ArrayList<HoKhau> hoKhauArrayList = GiaoTiep.getHoKhau();
            for (HoKhau hoKhau : hoKhauArrayList) {
                arrayList.add(String.valueOf(hoKhau.getIdho()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //biến này để đếm xem có phải là tên đầu tiên được thêm không
        dem = 0;
        /**
         * lắng nghe
         * tạo list thành viên nhân khẩu theo số hộ khẩu đã chọn, truyền vào hoTenChoice
         */
        ObservableList<String> idList = FXCollections.observableList(arrayList);
        soHoKhauChoice.setItems(idList);
        soHoKhauChoice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                dem = 0;
                this.s = "";
                nguoiLabel.setText(s);
                getHoTenNhanKhau(t1.toString());
                hoTenChoice.setItems(idList);
                hoTenChoice.setItems(FXCollections.observableList(nhanKhau1));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        /**
         * lắng nghe
         * tạo danh sách những nhân khẩu còn lại sau khi đã chọn chủ
         * xóa dữ liệu trong String s và nguoiLabel khi chọn chủ khác
         */
        hoTenChoice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            dem = 0;
            this.s = "";
            nguoiLabel.setText(s);
            try {
                getHoTenNhanKhau(soHoKhauChoice.getValue().toString());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            nhanKhau2.remove(hoTenChoice.getValue());
            nguoiChoice.setItems(idList);
            nguoiChoice.setItems(FXCollections.observableList(nhanKhau2));
        });

        /**
         * lắng nghe
         * chọn nhân khẩu nào thì thêm nó vào String s và hiển thị ra màn hình qua nguoiLabel
         * xóa nhân khẩu đã thêm khỏi list
         */
        nguoiChoice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (t1 != null) {
                setS(t1.toString());
                dem = 1;
                nguoiLabel.setText(s);
                nhanKhau2.remove(nguoiChoice.getValue());
                nguoiChoice.setItems(idList);
                nguoiChoice.setItems(FXCollections.observableList(nhanKhau2));
            }
        });
    }

    /**
     * lấy dữ liệu nhân khẩu cùng số hộ khẩu
     *
     * @param id số hộ khẩu
     * @throws SQLException
     */
    void getHoTenNhanKhau(String id) throws SQLException {
        nhanKhau1.clear();
        nhanKhau2.clear();
        ArrayList<NhanKhau> nhanKhauArrayList = GiaoTiep.getNhanKhau();
        for (NhanKhau nhanKhau : nhanKhauArrayList) {
            if (id.equals(String.valueOf(nhanKhau.getIdho()))) {
                nhanKhau1.add(nhanKhau.getHoten());
                nhanKhau2.add(nhanKhau.getHoten());
            }
        }
    }

    public void xacNhan(ActionEvent actionEvent) throws SQLException, IOException {
        //thêm chủ là dữ liệu cuối của listName
        listName.add(hoTenChoice.getValue().toString());

        //truyền dữ liệu lên DB
        int n = GiaoTiep.tachHo(listName, diaChiMoi.getText(), Integer.parseInt(soHoKhauChoice.getValue().toString()), Integer.parseInt(soHoKhauMoi.getText()));

        //đưa thông báo tùy biến trả về
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        if (n == 0)
            controller.setTextAlert("Đang là chủ hộ!");
        else if (n == 1)
            controller.setTextAlert("Đổi chủ hộ thành công!");
        else if (n == 2)
            controller.setTextAlert("Tách hộ thành công!");
        else
            controller.setTextAlert("Không thể tách cùng chủ hộ!");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(troVe.getScene().getWindow().getX() + 430);
        alert1.setY(troVe.getScene().getWindow().getY() + 200);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
        s = "";
    }
}
