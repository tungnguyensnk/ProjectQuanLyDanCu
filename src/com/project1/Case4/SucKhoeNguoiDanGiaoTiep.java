package com.project1.Case4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SucKhoeNguoiDanGiaoTiep {
    /**
     * Kết nối đến database
     */
    private static Connection connection;

    public SucKhoeNguoiDanGiaoTiep() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        SucKhoeNguoiDanGiaoTiep.connection = connection;
    }

    /**
     * Lấy danh sách Sức Khỏe Người Dân
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<SucKhoeNguoiDan> get() throws SQLException {
        String sql = "select * from suckhoenguoidan";

        Statement querySTM = connection.createStatement();
        ResultSet resultSet = querySTM.executeQuery(sql);

        ArrayList<SucKhoeNguoiDan> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(new SucKhoeNguoiDan(resultSet.getInt(2), resultSet.getInt(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getString(6), resultSet.getString(7),
                    resultSet.getInt(8), resultSet.getString(9),
                    resultSet.getString(10), resultSet.getString(11),
                    resultSet.getString(12), resultSet.getString(13),
                    resultSet.getInt(14), resultSet.getInt(15),
                    resultSet.getInt(16)));
        }

        return arrayList;
    }

    /**
     * Chèn thêm 1 bản ghi Sức khỏe người dân
     *
     * @param cur
     * @throws SQLException
     */
    public static void insert(SucKhoeNguoiDan cur) throws SQLException {
        String sql1 = "insert into suckhoenguoidan (idNhanKhau, idHoKhau, hoVaTen, gioiTinh, ngaySinh";
        String sql2 = " values (" + cur.getIdNhanKhau() + ", " + cur.getIdHoKhau() + ", '" + cur.getHoVaTen() + "', '"
                + cur.getGioiTinh() + "', '" + cur.getNgaySinh() + "'";

        if (cur.getCmnd_cccd() != null) {
            sql1 += ", cmnd_cccd";
            sql2 += ", '" + cur.getCmnd_cccd() + "'";
        }

        if (cur.getNguonLayCovid19() != null) {
            sql1 += ", nguonLay";
            sql2 += ", '" + cur.getNguonLayCovid19() + "'";
        }

        if (cur.getNoiCachLy() != null) {
            sql1 += ", noiCachLy";
            sql2 += ", '" + cur.getNoiCachLy() + "'";
        }

        if (cur.getNgayBatDauCachLy() != null) {
            sql1 += ", ngayBatDauCachLy";
            sql2 += ", '" + cur.getNgayBatDauCachLy() + "'";
        }

        if (cur.getNoiDieuTri() != null) {
            sql1 += ", noiDieuTri";
            sql2 += ", '" + cur.getNoiDieuTri() + "'";
        }

        if (cur.getNgayBatDauDieuTri() != null) {
            sql1 += ", ngayBatDauDieuTri";
            sql2 += ", '" + cur.getNgayBatDauDieuTri() + "'";
        }

        sql1 += ")";
        sql2 += ")";
        String sql = sql1 + sql2;

        Statement updateSTM = connection.createStatement();

        updateSTM.executeUpdate(sql);
    }

    /**
     * Xóa 1 bản ghi Sức khỏe người dân
     *
     * @param cur
     * @throws SQLException
     */
    public static void delete(SucKhoeNguoiDan cur) throws SQLException {
        String sql = "delete from suckhoenguoidan where idNhanKhau = " + cur.getIdNhanKhau();

        Statement updateSTM = connection.createStatement();

        updateSTM.executeUpdate(sql);
    }

    /**
     * cập nhật bản ghi
     *
     * @param cur
     * @throws SQLException
     */
    public static void update(SucKhoeNguoiDan cur) throws SQLException {
        String sql = "update suckhoenguoidan set idHoKhau = " + cur.getIdHoKhau() + ", hoVaTen = '" + cur.getHoVaTen()
                + "', gioiTinh = '" + cur.getGioiTinh() + "', ngaySinh = '" + cur.getNgaySinh() + "'";

        if (cur.getCmnd_cccd() != null) {
            sql += ", cmnd_cccd = '" + cur.getCmnd_cccd() + "'";
        }

        sql += ", tinhTrang = " + cur.getTinhTrang();

        if (cur.getNguonLayCovid19() != null) {
            sql += ", nguonLay = '" + cur.getNguonLayCovid19() + "'";
        }

        if (cur.getNoiCachLy() != null) {
            sql += ", noiCachLy = '" + cur.getNoiCachLy() + "'";
        }

        if (cur.getNgayBatDauCachLy() != null) {
            sql += ", ngayBatDauCachLy = '" + cur.getNgayBatDauCachLy() + "'";
        }

        if (cur.getNoiDieuTri() != null) {
            sql += ", noiDieuTri = '" + cur.getNoiDieuTri() + "'";
        }

        if (cur.getNgayBatDauDieuTri() != null) {
            sql += ", ngayBatDauDieuTri = '" + cur.getNgayBatDauDieuTri() + "'";
        }

        sql += ", ketQuaTest1 = " + cur.getKetQuaTest1();

        sql += ", ketQuaTest2 = " + cur.getKetQuaTest2();

        sql += ", ketQuaTest3 = " + cur.getKetQuaTest3();

        sql += " where idNhanKhau = " + cur.getIdNhanKhau();

        Statement updateSTM = connection.createStatement();

        updateSTM.executeUpdate(sql);
    }
}
