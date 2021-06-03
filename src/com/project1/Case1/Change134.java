package com.project1.Case1;

import com.project1.Main.Alert;
import com.project1.Main.GiaoTiep;
import com.project1.Main.Menu;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * chức năng 1-3-4: tạm trú tạm vắng
 */
public class Change134 implements Initializable {
    public Button troVe;
    public Label tamVangLabel;
    public TextField hoTenField;
    public DatePicker ngaySinhPick;
    public RadioButton namRadioButton;
    public RadioButton nuRadioButton;
    public TextField noiThuongTruField;
    public TextField CMNDField;
    public TextField hoChieuField;
    public TextField quocTichField;
    public DatePicker tuNgay;
    public DatePicker denNgay;
    public TextField lyDoField;
    public Button pdfButton;
    public Label texttmp;
    public Button xacNhanButton;
    public ToggleGroup sex = new ToggleGroup();
    int mode = 0;

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    com.project1.Main.Menu menu;

    public void setMenu(Menu controller) {
        this.menu = controller;
    }

    public void troVe() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("change13.fxml"));
        Parent pr = loader.load();
        Change13 controller = loader.getController();
        controller.setMenu(menu);
        menu.contentRoot.getChildren().clear();
        menu.contentRoot.getChildren().add(pr);
    }

    public void xacNhan() throws SQLException, IOException {
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        GiaoTiep.setTamTruTamVang(hoTenField.getText(), ngaySinhPick.getEditor().getText(), ((RadioButton)sex.getSelectedToggle()).getText(),
                noiThuongTruField.getText(), CMNDField.getText(), quocTichField.getText(), hoChieuField.getText(), tuNgay.getEditor().getText(),
                denNgay.getEditor().getText(), lyDoField.getText(), mode);

        //thông báo thêm thành công
        Stage alert1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Main/alert.fxml"));
        Parent pr = loader.load();
        Alert controller = loader.getController();
        controller.setTextAlert("Khai báo thành công");
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

    /**
     * in sang docx
     * @throws IOException
     */
    public void inpdf() throws IOException {
        try (XWPFDocument doc = new XWPFDocument()) {

            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun r1 = p1.createRun();
            r1.setFontSize(13);
            r1.setBold(true);
            r1.setFontFamily("Times New Roman");
            r1.setText("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
            r1.addBreak();

            XWPFRun r2 = p1.createRun();
            r2.setFontSize(14);
            r2.setBold(true);
            r2.setFontFamily("Times New Roman");
            r2.setText("Độc lập - Tự do - Hạnh phúc");
            r2.addBreak();

            XWPFRun r3 = p1.createRun();
            r3.setFontSize(10);
            r3.setBold(true);
            r3.setFontFamily("Times New Roman");
            r3.setText("----------------");
            r3.addBreak();
            r3.addBreak();
            r3.addBreak();

            XWPFRun r4 = p1.createRun();
            r4.setFontSize(14);
            r4.setBold(true);
            r4.setFontFamily("Times New Roman");
            r4.setText("PHIẾU KHAI BÁO TẠM VẮNG");
            r4.addBreak();
            r4.setText("(Phần cấp cho người tạm vắng)");

            XWPFParagraph p2 = doc.createParagraph();
            p2.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r5 = p2.createRun();
            r5.setFontSize(14);
            r5.setFontFamily("Times New Roman");
            r5.setText("Họ và tên: "+hoTenField.getText());
            r5.addBreak();
            r5.setText("Ngày, tháng, năm sinh: " +ngaySinhPick.getEditor().getText()+ "    Giới tính: " +((RadioButton)sex.getSelectedToggle()).getText()+ "  Quốc tịch: "+quocTichField.getText());
            r5.addBreak();
            r5.setText("CMND số: " +CMNDField.getText()+ " Hộ chiếu số: "+hoChieuField.getText());
            r5.addBreak();
            r5.setText("Nơi thường trú, tạm trú: "+noiThuongTruField.getText());
            r5.addBreak();
            r5.setText("Tạm vắng từ ngày, tháng, năm: " +tuNgay.getEditor().getText()+ " đến ngày "+denNgay.getEditor().getText());
            r5.addBreak();
            r5.setText("Lý do tạm vắng và nơi đến:");
            r5.addBreak();
            r5.setText(lyDoField.getText());
            r5.addBreak();
            r5.addBreak();

            XWPFParagraph p3 = doc.createParagraph();
            p3.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r6 = p3.createRun();
            r6.setFontSize(14);
            r6.setItalic(true);
            r6.setFontFamily("Times New Roman");
            r6.addTab();
            r6.setText("ngày " + LocalDate.now().getDayOfMonth()+" tháng " +LocalDate.now().getMonth().getValue()+ " năm "+LocalDate.now().getYear());

            XWPFParagraph p4 = doc.createParagraph();
            p4.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r7 = p4.createRun();
            r7.setFontSize(13);
            r7.setFontFamily("Times New Roman");
            r7.addTab();
            r7.addTab();
            r7.setText("TỔ TRƯỞNG");
            r7.addTab();
            r7.addTab();
            r7.addTab();
            r7.addTab();
            r7.setText("NGƯỜI KHAI BÁO");

            XWPFParagraph p5 = doc.createParagraph();
            p5.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun r8 = p5.createRun();
            r8.setItalic(true);
            r8.setFontSize(13);
            r8.setFontFamily("Times New Roman");
            r8.addTab();
            r8.setText("(Ký, ghi rõ họ tên và đóng dấu)");
            r8.addTab();
            r8.addTab();
            r8.addTab();
            r8.setText("(Ký, ghi rõ họ tên)");
            try (OutputStream os = new FileOutputStream(System.getProperty("user.dir")+"//text.docx")) {
                doc.write(os);
            }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tamVangLabel.setOnMouseClicked(mouseEvent -> {
            if (getMode() == 0) {
                setMode(1);
                tamVangLabel.setText("Tạm trú");
                noiThuongTruField.setPromptText("Nơi thường trú");
                texttmp.setText("Tạm trú");
                lyDoField.setPromptText("Lý do tạm trú và nơi ở");
            } else {
                setMode(0);
                tamVangLabel.setText("Tạm vắng");
                noiThuongTruField.setPromptText("Nơi thường trú, tạm trú");
                texttmp.setText("Tạm vắng");
                lyDoField.setPromptText("Lý do tạm vắng và nơi đến");
            }
        });

        namRadioButton.setToggleGroup(sex);
        nuRadioButton.setToggleGroup(sex);
        namRadioButton.setSelected(true);
    }
}
