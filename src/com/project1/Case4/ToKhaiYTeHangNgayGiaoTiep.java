package com.project1.Case4;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Giao tiếp với database, lấy dữ liệu từ bảng tokhaiytehangngay
 */
public class ToKhaiYTeHangNgayGiaoTiep {
    /**
     * connection được lấy từ lớp com.project1.Main.Controller sau khi nhập xong tài khoản, mật khẩu.
     */
    private static Connection connection;

    public ToKhaiYTeHangNgayGiaoTiep() {
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        ToKhaiYTeHangNgayGiaoTiep.connection = connection;
    }

    /**
     * Lấy danh sách tờ khai y tế vào ArrayList
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<ToKhaiYTeHangNgay> getToKhaiYTeHangNgay() throws SQLException {
        String sql = "select * from tokhaiytehangngay";

        Statement querySTM = connection.createStatement();
        ResultSet resultSet = querySTM.executeQuery(sql);

        ArrayList<ToKhaiYTeHangNgay> arrayList = new ArrayList<>();
        while (resultSet.next()) {
            arrayList.add(new ToKhaiYTeHangNgay(resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5),
                    resultSet.getBoolean(6), resultSet.getBoolean(7),
                    resultSet.getString(8), resultSet.getBoolean(9)));
        }
        return arrayList;
    }

    /**
     * chèn tờ khai mới vào bảng tokhaiytehangngay
     *
     * @param toKhai
     * @throws SQLException
     */
    public static void insertToKhaiYTeHangNgay(ToKhaiYTeHangNgay toKhai) throws SQLException {
        String sql = "insert into tokhaiytehangngay (ngayNop, hoVaTen, idNhanKhau, soDienThoai, denNoiCoDich, tiepXuc, trieuChung)"
                + " values ('" + toKhai.getNgayNop() + "', '" + toKhai.getHoVaTen() + "', '"
                + toKhai.getIdNhanKhau() + "', '" + toKhai.getSoDienThoai() + "', "
                + toKhai.getDenNoiCoDich() + ", " + toKhai.getTiepXuc() + ", '"
                + toKhai.getTrieuChung() + "')";

        Statement updateSTM = connection.createStatement();
        updateSTM.executeUpdate(sql);
    }

    public static void updateDaXem(ToKhaiYTeHangNgay toKhai) throws SQLException {
        String sql = "update tokhaiytehangngay set daXem = true where idNhanKhau = '" + toKhai.getIdNhanKhau() + "'";

        Statement updateSTM = connection.createStatement();

        updateSTM.executeUpdate(sql);
    }
}
