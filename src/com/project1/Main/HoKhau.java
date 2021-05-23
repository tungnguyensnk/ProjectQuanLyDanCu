package com.project1.Main;

public class HoKhau {
    int idho;
    String hotenchu;

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getHotenchu() {
        return hotenchu;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setHotenchu(String hotenchu) {
        this.hotenchu = hotenchu;
    }

    public int getIdho() {
        return idho;
    }

    public void setIdho(int idho) {
        this.idho = idho;
    }

    String diachi;
    String ghichu;

    public HoKhau(int idho, String hotenchu, String diachi, String ghichu) {
        this.idho = idho;
        this.hotenchu = hotenchu;
        this.diachi = diachi;
        this.ghichu = ghichu;
    }

    @Override
    public String toString() {
        return "HoKhau{" +
                "idho=" + idho +
                ", hotenchu='" + hotenchu + '\'' +
                ", diachi='" + diachi + '\'' +
                ", ghichu='" + ghichu + '\'' +
                '}';
    }
}
