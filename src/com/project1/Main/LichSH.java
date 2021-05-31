package com.project1.Main;

import java.util.Objects;

public class LichSH {
    private int stt;     /// Số thứ tự
    private String NgayThang;    /// Thời gian
    private String DiaDiem;    /// Địa điểm
    private String NoiDung; //// Loại thông báo
    private String ThongBao;   //// Nội dung thông báo


    public int getStt() {
        return stt;
    }

    public void setStt(int Stt) {
        stt = Stt;
    }

    public String getNgayThang() {
        return NgayThang;
    }

    public void setNgayThang(String ngayThang) {
        NgayThang = ngayThang;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getDiaDiem() {  return DiaDiem; }

    public void setDiaDiem(String diaDiem) { DiaDiem = diaDiem; }

    public String getThongBao() { return ThongBao; }

    public void setThongBao(String thongBao) { ThongBao = thongBao; }

    public LichSH(int stt, String NgayThang, String DiaDiem, String NoiDung, String ThongBao) {
        this.stt = stt;
        this.NgayThang = NgayThang;
        this.NoiDung = NoiDung;
        this.ThongBao = ThongBao;
        this.DiaDiem = DiaDiem;
    }

    @Override
    public String toString() {
        return "LichSH{" +
                "stt=" + stt +
                ", NgayThang='" + NgayThang + '\'' +
                ", DiaDiem='" + DiaDiem + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", ThongBao='" + ThongBao + '\'' +
                '}';
    }
}
