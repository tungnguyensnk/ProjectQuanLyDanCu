package com.project1.Case4;

public class SucKhoeNguoiDan {
    private int idNhanKhau;
    private int idHoKhau;
    private String hoVaTen;
    private String gioiTinh;
    private String ngaySinh;
    private String cmnd_cccd;
    private int tinhTrang;
    private String nguonLayCovid19;
    private String noiCachLy;
    private String ngayBatDauCachLy;
    private String noiDieuTri;
    private String ngayBatDauDieuTri;
    private int ketQuaTest1;
    private int ketQuaTest2;
    private int ketQuaTest3;

    public SucKhoeNguoiDan(int idNhanKhau, int idHoKhau, String hoVaTen, String gioiTinh, String ngaySinh,
                           String cmnd_cccd, int tinhTrang, String nguonLayCovid19, String noiCachLy,
                           String ngayBatDauCachLy, String noiDieuTri, String ngayBatDauDieuTri,
                           int ketQuaTest1, int ketQuaTest2, int ketQuaTest3) {
        this.idNhanKhau = idNhanKhau;
        this.idHoKhau = idHoKhau;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cmnd_cccd = cmnd_cccd;
        this.tinhTrang = tinhTrang;
        this.nguonLayCovid19 = nguonLayCovid19;
        this.noiCachLy = noiCachLy;
        this.ngayBatDauCachLy = ngayBatDauCachLy;
        this.noiDieuTri = noiDieuTri;
        this.ngayBatDauDieuTri = ngayBatDauDieuTri;
        this.ketQuaTest1 = ketQuaTest1;
        this.ketQuaTest2 = ketQuaTest2;
        this.ketQuaTest3 = ketQuaTest3;
    }

    public SucKhoeNguoiDan() {
    }

    public SucKhoeNguoiDan clone() {
        SucKhoeNguoiDan newOne = new SucKhoeNguoiDan(this.idNhanKhau, this.idHoKhau, this.hoVaTen, this.gioiTinh,
                this.ngaySinh, this.cmnd_cccd, this.tinhTrang, this.nguonLayCovid19, this.noiCachLy,
                this.ngayBatDauCachLy, this.noiDieuTri, this.ngayBatDauDieuTri, this.ketQuaTest1,
                this.ketQuaTest2, this.ketQuaTest3);
        return newOne;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd_cccd() {
        return cmnd_cccd;
    }

    public void setCmnd_cccd(String cmnd_cccd) {
        this.cmnd_cccd = cmnd_cccd;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNguonLayCovid19() {
        return nguonLayCovid19;
    }

    public void setNguonLayCovid19(String nguonLayCovid19) {
        this.nguonLayCovid19 = nguonLayCovid19;
    }

    public String getNoiCachLy() {
        return noiCachLy;
    }

    public void setNoiCachLy(String noiCachLy) {
        this.noiCachLy = noiCachLy;
    }

    public String getNgayBatDauCachLy() {
        return ngayBatDauCachLy;
    }

    public void setNgayBatDauCachLy(String ngayBatDauCachLy) {
        this.ngayBatDauCachLy = ngayBatDauCachLy;
    }

    public String getNoiDieuTri() {
        return noiDieuTri;
    }

    public void setNoiDieuTri(String noiDieuTri) {
        this.noiDieuTri = noiDieuTri;
    }

    public String getNgayBatDauDieuTri() {
        return ngayBatDauDieuTri;
    }

    public void setNgayBatDauDieuTri(String ngayBatDauDieuTri) {
        this.ngayBatDauDieuTri = ngayBatDauDieuTri;
    }

    public int getKetQuaTest1() {
        return ketQuaTest1;
    }

    public void setKetQuaTest1(int ketQuaTest1) {
        this.ketQuaTest1 = ketQuaTest1;
    }

    public int getKetQuaTest2() {
        return ketQuaTest2;
    }

    public void setKetQuaTest2(int ketQuaTest2) {
        this.ketQuaTest2 = ketQuaTest2;
    }

    public int getKetQuaTest3() {
        return ketQuaTest3;
    }

    public void setKetQuaTest3(int ketQuaTest3) {
        this.ketQuaTest3 = ketQuaTest3;
    }
}
