package com.project1.Case2;

public class NoiDungPhanAnh {
    int id;
    String hoTen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String soDienThoai;
    String ngay;
    String phanLoai;
    String noiDung;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public NoiDungPhanAnh(String hoTen, String soDienThoai, String ngay, String phanLoai, String noiDung) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.ngay = ngay;
        this.phanLoai = phanLoai;
        this.noiDung = noiDung;
    }

    public NoiDungPhanAnh(int id, String hoTen, String soDienThoai, String ngay, String phanLoai, String noiDung) {
        this.id = id;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.ngay = ngay;
        this.phanLoai = phanLoai;
        this.noiDung = noiDung;
    }
}
