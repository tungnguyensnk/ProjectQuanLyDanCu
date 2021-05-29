package com.project1.Case1;

import com.project1.Main.*;
import com.project1.Main.Alert;
import com.project1.Main.Menu;
import javafx.animation.PauseTransition;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * chức năng 1-3-2: nhân khẩu chuyển đi
 */
public class Change132 implements Initializable {
    public Button troVe;
    public ChoiceBox soHoKhau1Choice;
    public ChoiceBox<String> hoTen1Choice;
    public DatePicker ngayDiPick;
    public TextField noiDiPick;
    public ChoiceBox soHoKhau2Choice;
    public ChoiceBox<String> hoTen2Choice;
    public Button xacNhan2Button;
    public Button xacNhan1Button;
    public Label tlabel;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * tạo ArrayList chứa thông tin số hộ khẩu
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

        /**
         * thêm list vào ChoiceBox
         */
        ObservableList<String> idList = FXCollections.observableList(arrayList);
        soHoKhau1Choice.setItems(idList);
        soHoKhau2Choice.setItems(idList);

        /**
         * lắng nghe, tạo list danh sánh thành viên nhân khẩu theo số hộ khẩu chọn ở ChoiceBox trên
         */
        ArrayList<String> nhanKhau1 = new ArrayList<>();
        soHoKhau1Choice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                nhanKhau1.clear();
                ArrayList<NhanKhau> nhanKhauArrayList = GiaoTiep.getNhanKhau();
                for (NhanKhau nhanKhau : nhanKhauArrayList) {
                    if (t1.equals(String.valueOf(nhanKhau.getIdho()))) {
                        nhanKhau1.add(nhanKhau.getHoten());
                    }
                }
                //lẽ ra có mỗi dòng 2, nhưng nó không thay đổi mục trong ChoiceBox mặc dù nó vẫn không ấn được
                hoTen1Choice.setItems(idList);                                  //1
                hoTen1Choice.setItems(FXCollections.observableList(nhanKhau1)); //2
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        /**
         * tương tự thằng trên, do có 2 chức năng như nhau
         */
        ArrayList<String> nhanKhau2 = new ArrayList<>();
        soHoKhau2Choice.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            try {
                nhanKhau2.clear();
                ArrayList<NhanKhau> nhanKhauArrayList = GiaoTiep.getNhanKhau();
                for (NhanKhau nhanKhau : nhanKhauArrayList) {
                    if (t1.equals(String.valueOf(nhanKhau.getIdho()))) {
                        nhanKhau2.add(nhanKhau.getHoten());
                    }
                }
                hoTen2Choice.setItems(idList);
                hoTen2Choice.setItems(FXCollections.observableList(nhanKhau2));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        //tạo 1 luồng để gợi ý địa điểm
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                for (int i = 0; i < 1000000; i++) {
                    Thread.sleep(1500);
                    if (!noiDiPick.getText().isEmpty() && !noiDiPick.getText().equals(getTmp())) {
                        setArr(GiaoTiep.getDanhSachViTri(noiDiPick.getText()));
                        setTmp(noiDiPick.getText());
                    }
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

        //lắng nghe nội dung của noiDiPick, hiển thị gợi ý sau 2s
        noiDiPick.textProperty().addListener((observableValue, s, t1) -> {
            if (getArr().size() != 0 && !tlabel.getText().replaceAll("Gợi ý: ","").equals(noiDiPick.getText())) {
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> tlabel.setText("Gợi ý: "+ getArr().get(0)));
                delay.play();
            }
        });

        //nhấn vào Label thì seo điền vào noiSinhField
        tlabel.setOnMouseClicked(mouseEvent -> {
            noiDiPick.setText(tlabel.getText().substring(7));
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

    /**
     * gửi ghi chú 1 lên DB
     *
     * @throws SQLException
     * @throws IOException
     */
    public void xacNhan1() throws SQLException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanKhau nhanKhau : GiaoTiep.getNhanKhau()) {
            if (hoTen1Choice.getValue().equals(nhanKhau.getHoten())) {
                GiaoTiep.setGhiChuNhanKhau(nhanKhau.getId(), "Chuyển đến " + noiDiPick.getText() + " ngày " +
                        ngayDiPick.getValue().format(dateTimeFormatter));
            }
        }

        //thông báo thành công, đáng ra phải viết 1 hàm mà lười quá
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Ghi chú chuyến đi thành công!");
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
    }

    /**
     * gửi ghi chú 2 lên DB
     *
     * @throws SQLException
     * @throws IOException
     */
    public void xacNhan2() throws SQLException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanKhau nhanKhau : GiaoTiep.getNhanKhau()) {
            if (hoTen2Choice.getValue().equals(nhanKhau.getHoten())) {
                GiaoTiep.setGhiChuNhanKhau(nhanKhau.getId(), "Qua đời ngày " + LocalDate.now().format(dateTimeFormatter));
            }
        }

        //thông báo
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Xin chia buồn!");
        Scene sc1 = new Scene(pr);
        alert1.setScene(sc1);
        sc1.setFill(Color.TRANSPARENT);
        alert1.initStyle(StageStyle.TRANSPARENT);
        alert1.setX(troVe.getScene().getWindow().getX() + 450);
        alert1.setY(troVe.getScene().getWindow().getY() + 450);
        alert1.setAlwaysOnTop(true);
        alert1.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> alert1.close());
        delay.play();
    }
}
