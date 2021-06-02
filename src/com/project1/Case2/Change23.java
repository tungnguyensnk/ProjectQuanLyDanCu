package com.project1.Case2;

import com.project1.Main.GiaoTiep;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Change23 implements Initializable {
    public Button troVe;
    public PieChart thongKe;
    Menu menu;

    public void setMenu(Menu mainMenu) {
        this.menu = mainMenu;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int chuaxem=0,daxem=0,dagq=0;
        ArrayList<NoiDungPhanAnh> arrayList = new ArrayList<>();
        try {
            arrayList = GiaoTiep.getPhanAnh();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for(NoiDungPhanAnh noidung :arrayList){
            if(noidung.getDaXem().equals("Chưa xem"))
                chuaxem++;
            else if(noidung.getDaXem().equals("Đã xem"))
                daxem++;
            else
                dagq++;
        }
        thongKe.getData().clear();
        if(chuaxem!=0){
            thongKe.getData().addAll(new PieChart.Data("Chưa xem("+chuaxem+")",chuaxem));
        }
        if(daxem!=0){
            thongKe.getData().addAll(new PieChart.Data("Đã xem("+daxem+")",daxem));
        }
        if(dagq!=0){
            thongKe.getData().addAll(new PieChart.Data("Đã giải quyết("+dagq+")",dagq));
        }
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
