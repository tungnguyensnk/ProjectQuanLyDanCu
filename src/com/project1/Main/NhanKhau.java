package com.project1.Main;

/**
 * lưu các thuộc tính của nhân khẩu
 */
public class NhanKhau {
    int idho;
    int id;
    String quanhech;
    String hoten;
    String gioitinh;
    String ngaysinh;
    String noisinh;
    String nguyenquan;
    String dantoc;
    String nghenghiep;
    String noilamviec;
    String cmnd;
    String ngaycap;
    String noicap;
    String ndkthuongtru;
    String dcthuongtrutrc;
    String ghichu;

    public int getIdho() {
        return idho;
    }

    public void setIdho(int idho) {
        this.idho = idho;
    }

    public String getQuanhech() {
        return quanhech;
    }

    public void setQuanhech(String quanhech) {
        this.quanhech = quanhech;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public String getNguyenquan() {
        return nguyenquan;
    }

    public void setNguyenquan(String nguyenquan) {
        this.nguyenquan = nguyenquan;
    }

    public String getDantoc() {
        return dantoc;
    }

    public void setDantoc(String dantoc) {
        this.dantoc = dantoc;
    }

    public String getNghenghiep() {
        return nghenghiep;
    }

    public void setNghenghiep(String nghenghiep) {
        this.nghenghiep = nghenghiep;
    }

    public String getNoilamviec() {
        return noilamviec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoilamviec(String noilamviec) {
        this.noilamviec = noilamviec;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgaycap() {
        return ngaycap;
    }

    public void setNgaycap(String ngaycap) {
        this.ngaycap = ngaycap;
    }

    public String getNoicap() {
        return noicap;
    }

    public void setNoicap(String noicap) {
        this.noicap = noicap;
    }

    public String getNdkthuongtru() {
        return ndkthuongtru;
    }

    public void setNdkthuongtru(String ndkthuongtru) {
        this.ndkthuongtru = ndkthuongtru;
    }

    public String getDcthuongtrutrc() {
        return dcthuongtrutrc;
    }

    public void setDcthuongtrutrc(String dcthuongtrutrc) {
        this.dcthuongtrutrc = dcthuongtrutrc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public NhanKhau(int idho, String quanhech, String hoten, String gioitinh, String ngaysinh, String noisinh,
                    String nguyenquan, String dantoc, String nghenghiep, String noilamviec, String cmnd, String ngaycap,
                    String noicap, String ndkthuongtru, String dcthuongtrutrc, String ghichu) {
        this.idho = idho;
        this.quanhech = quanhech;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.nguyenquan = nguyenquan;
        this.dantoc = dantoc;
        this.nghenghiep = nghenghiep;
        this.noilamviec = noilamviec;
        this.cmnd = cmnd;
        this.ngaycap = ngaycap;
        this.noicap = noicap;
        this.ndkthuongtru = ndkthuongtru;
        this.dcthuongtrutrc = dcthuongtrutrc;
        this.ghichu = ghichu;
    }

    public NhanKhau(int idho, int id, String quanhech, String hoten, String gioitinh, String ngaysinh, String noisinh,
                    String nguyenquan, String dantoc, String nghenghiep, String noilamviec, String cmnd, String ngaycap,
                    String noicap, String ndkthuongtru, String dcthuongtrutrc, String ghichu) {
        this.idho = idho;
        this.id = id;
        this.quanhech = quanhech;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.nguyenquan = nguyenquan;
        this.dantoc = dantoc;
        this.nghenghiep = nghenghiep;
        this.noilamviec = noilamviec;
        this.cmnd = cmnd;
        this.ngaycap = ngaycap;
        this.noicap = noicap;
        this.ndkthuongtru = ndkthuongtru;
        this.dcthuongtrutrc = dcthuongtrutrc;
        this.ghichu = ghichu;
    }

    public NhanKhau(int idho, String hoten, String gioitinh, String ngaysinh, String noisinh, String cmnd, String ghichu) {
        this.idho = idho;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.cmnd = cmnd;
        this.ghichu = ghichu;
        this.nguyenquan = "";
        this.dantoc = "";
        this.nghenghiep = "";
        this.noilamviec = "";
        this.ngaycap = "";
        this.noicap = "";
        this.ndkthuongtru = "";
        this.dcthuongtrutrc = "";
        this.quanhech = "";
    }

    @Override
    public String toString() {
        return "NhanKhau{" +
                "idho=" + idho +
                ", quanhech='" + quanhech + '\'' +
                ", hoten='" + hoten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", noisinh='" + noisinh + '\'' +
                ", nguyenquan='" + nguyenquan + '\'' +
                ", dantoc='" + dantoc + '\'' +
                ", nghenghiep='" + nghenghiep + '\'' +
                ", noilamviec='" + noilamviec + '\'' +
                ", cmnd='" + cmnd + '\'' +
                ", ngaycap='" + ngaycap + '\'' +
                ", noicap='" + noicap + '\'' +
                ", ndkthuongtru='" + ndkthuongtru + '\'' +
                ", dcthuongtrutrc='" + dcthuongtrutrc + '\'' +
                ", ghichu='" + ghichu + '\'' +
                '}';
    }
}
