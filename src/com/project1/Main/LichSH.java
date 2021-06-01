package com.project1.Main;

import java.util.Objects;

public class LichSH {
    private int stt;
    private String NgayThang;
    private String DiaDiem;
    private String NoiDung;
    private String ThongBao;


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
