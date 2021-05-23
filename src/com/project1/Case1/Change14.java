package com.project1.Case1;

import com.project1.Main.GiaoTiep;
import com.project1.Main.Menu;
import com.project1.Main.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Change14 implements Initializable {
    public PieChart tkGioi;
    public BarChart<String, Number> barChart;
    public NumberAxis yAxis = new NumberAxis();
    public CategoryAxis xAxis = new CategoryAxis();
    public Button troVe;
    Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int nam = 0, nu = 0, khac, duoild = 0, ld = 0, ngoaild = 0;
        LocalDate cur = LocalDate.now();
        ArrayList<NhanKhau> arrayList = new ArrayList<>();
        try {
            arrayList = GiaoTiep.getNhanKhau();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (NhanKhau nhanKhau : arrayList) {
            String[] date = nhanKhau.getNgaysinh().split("/");
            LocalDate tmp = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
            int year = Period.between(tmp, cur).getYears();
            if (year < 15)
                duoild++;
            else if (year < 65)
                ld++;
            else
                ngoaild++;
            if (nhanKhau.getGioitinh().equals("Nam")) {
                nam++;
            }
            if (nhanKhau.getGioitinh().equals("Nữ")) {
                nu++;
            }
        }
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        int sum = ngoaild + duoild + ld;
        data.getData().add(new XYChart.Data<>("Dưới lao động (" + duoild + ")", duoild * 100 / sum));
        data.getData().add(new XYChart.Data<>("Lao động (" + ld + ")", ld * 100 / sum));
        data.getData().add(new XYChart.Data<>("Ngoài lao động (" + ngoaild + ")", ngoaild * 100 / sum));
        barChart.getData().addAll(data);
        khac = arrayList.size() - nam - nu;
        double pnam = ((double) nam * 100) / (nam + nu + khac);
        double pnu = ((double) nu * 100) / (nam + nu + khac);
        DecimalFormat df = new DecimalFormat("#.##");
        tkGioi.getData().clear();
        tkGioi.getData().addAll(new PieChart.Data(nam + " Nam (" + df.format(pnam) + "%)", nam), new PieChart.Data(nu + " Nữ (" + df.format(pnu) + "%)", nu));
        if (khac != 0) {
            double pkhac = ((double) khac * 100) / (nam + nu + khac);
            tkGioi.getData().add(new PieChart.Data(khac + " Khác(" + df.format(pkhac) + "%)", khac));
        }

    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change1.fxml"));
        Parent pr = loader.load();
        Change1 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }
}
