package com.project1.Main;

import com.project1.Case1.Change1;
import com.project1.Case3.Change3;
import com.project1.Case4.Case4MenuController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Menu implements Initializable {
    @FXML
    public Label name;
    public AnchorPane contentRoot;
    public AnchorPane root;
    public Button hoKhau;
    public Button thuPhi;
    public Button nhaVanHoa;
    public Button lichSinhHoat;
    public Button logOut;

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //khởi tạo kiểu button khi hover và hiển thị người dùng
        hoKhau.setOnMouseEntered(e -> hoKhau.setStyle("-fx-background-color: white; -fx-text-fill: #27c73c;"));
        hoKhau.setOnMouseExited(e -> hoKhau.setStyle("-fx-background-color: #1a6dd7; -fx-text-fill: white;"));
        thuPhi.setOnMouseEntered(e -> thuPhi.setStyle("-fx-background-color: white; -fx-text-fill: #27c73c; "));
        thuPhi.setOnMouseExited(e -> thuPhi.setStyle("-fx-background-color: #1a6dd7; -fx-text-fill: white;"));
        nhaVanHoa.setOnMouseEntered(e -> nhaVanHoa.setStyle("-fx-background-color: white; -fx-text-fill: #27c73c;"));
        nhaVanHoa.setOnMouseExited(e -> nhaVanHoa.setStyle("-fx-background-color: #1a6dd7; -fx-text-fill: white;"));
        lichSinhHoat.setOnMouseEntered(e -> lichSinhHoat.setStyle("-fx-background-color: white; -fx-text-fill: #27c73c;"));
        lichSinhHoat.setOnMouseExited(e -> lichSinhHoat.setStyle("-fx-background-color: #1a6dd7; -fx-text-fill: white;"));
        name.setText(GiaoTiep.getUserName().toUpperCase());
        try {
            change1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //làm cho stage movable
        root.setOnMousePressed(mouseEvent -> {
            xOffset = mouseEvent.getSceneX();
            yOffset = mouseEvent.getSceneY();
        });
        root.setOnMouseDragged(mouseEvent -> {
            contentRoot.getScene().getWindow().setX(mouseEvent.getScreenX() - xOffset);
            contentRoot.getScene().getWindow().setY(mouseEvent.getScreenY() - yOffset);
        });
    }

    /**
     * chức năng 1:quản lý nhân khẩu, hộ khẩu
     *
     * @throws IOException
     */
    public void change1() throws IOException {
        /**
         * nạp file design, set controler vào contentRoot và truyền Menu trước vì FXMLLoader tĩnh
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Case1/change1.fxml"));
        Parent pr = loader.load();
        Change1 controller = loader.getController();
        controller.setMenu(this);
        contentRoot.getChildren().clear();
        contentRoot.getChildren().add(pr);
    }

    public void change2() {
    }

    public void change3() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Case3/Change3.fxml"));
        Parent pr = loader.load();
        Change3 controller = loader.getController();
        controller.setMenu(this);
        contentRoot.getChildren().clear();
        contentRoot.getChildren().add(pr);
    }

    /**
     * chức năng 4: quản lí thông tin phòng tránh Covid-19
     */
    public void change4() throws IOException {
        FXMLLoader case4MenuLoader = new FXMLLoader();
        URL url = Objects.requireNonNull(getClass().getResource("../Case4/Case4Menu.fxml"));
        case4MenuLoader.setLocation(url);

        Parent parent = case4MenuLoader.load();

        Case4MenuController controller = case4MenuLoader.getController();
        controller.setMenu(this);

        contentRoot.getChildren().clear();
        contentRoot.getChildren().add(parent);
    }

    /**
     * đăng xuất
     *
     * @throws IOException
     */
    public void logOut() throws IOException {
        Stage st1 = (Stage) name.getScene().getWindow();
        st1.close();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Stage sc2 = new Stage();
        sc2.setScene(new Scene(root, 711, 400));
        sc2.initStyle(StageStyle.TRANSPARENT);
        sc2.show();
    }
}
