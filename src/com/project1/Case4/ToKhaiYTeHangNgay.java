package com.project1.Case4;

public class ToKhaiYTeHangNgay {
    private String ngayNop;
    private String hoVaTen;
    private String idNhanKhau;
    private String soDienThoai;
    private Boolean denNoiCoDich;
    private Boolean tiepXuc;
    private String trieuChung;
    private Boolean daXem;

    public ToKhaiYTeHangNgay(String ngayNop, String hoVaTen, String idNhanKhau, String soDienThoai,
                             Boolean denNoiCoDich, Boolean tiepXuc, String trieuChung, Boolean daXem) {
        this.ngayNop = ngayNop;
        this.hoVaTen = hoVaTen;
        this.idNhanKhau = idNhanKhau;
        this.soDienThoai = soDienThoai;
        this.denNoiCoDich = denNoiCoDich;
        this.tiepXuc = tiepXuc;
        this.trieuChung = trieuChung;
        this.daXem = daXem;
    }

    public ToKhaiYTeHangNgay() {
    }

    public String getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(String ngayNop) {
        this.ngayNop = ngayNop;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(String idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Boolean getDenNoiCoDich() {
        return denNoiCoDich;
    }

    public void setDenNoiCoDich(Boolean denNoiCoDich) {
        this.denNoiCoDich = denNoiCoDich;
    }

    public Boolean getTiepXuc() {
        return tiepXuc;
    }

    public void setTiepXuc(Boolean tiepXuc) {
        this.tiepXuc = tiepXuc;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public Boolean getDaXem() {
        return daXem;
    }

    public void setDaXem(Boolean daXem) {
        this.daXem = daXem;
    }
}
