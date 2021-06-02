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
    String diaChi;
    String daXem;

    public String getDaXem() {
        return daXem;
    }

    public void setDaXem(String daXem) {
        this.daXem = daXem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

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

    public NoiDungPhanAnh(int id, String hoTen, String soDienThoai, String diaChi, String ngay, String phanLoai, String noiDung, String daXem) {
        this.id = id;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.ngay = ngay;
        this.phanLoai = phanLoai;
        this.noiDung = noiDung;
        this.diaChi = diaChi;
        this.daXem = daXem;
    }

    public NoiDungPhanAnh(String hoTen, String soDienThoai, String ngay, String phanLoai, String noiDung, String diaChi,String daXem) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.ngay = ngay;
        this.phanLoai = phanLoai;
        this.noiDung = noiDung;
        this.diaChi = diaChi;
        this.daXem=daXem;
    }
}
