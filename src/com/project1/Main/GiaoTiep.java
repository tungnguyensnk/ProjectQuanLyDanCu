package com.project1.Main;

import javax.json.*;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

/**
 * class static để giao tiếp với MariaDB
 */
public class GiaoTiep {
    private static String USER_NAME = "";
    private static Connection con;
    public static void setUserName(String userName) {
        USER_NAME = userName;
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public GiaoTiep() {
    }

    /**
     * kết nối với DB và trả về Connection để kết nối sau này
     *
     * @param username Tài Khoản DB
     * @param password Mật Khẩu DB
     * @return Connection
     */
    public static Connection connect(String username, String password) {
        setUserName(username);
        con = null;
        try {
            String DB_URL = "jdbc:mysql://localhost:3306/danso";
            con = DriverManager.getConnection(DB_URL, username, password);
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return con;
    }

    /**
     * lấy thông tin tất cả hộ khẩu, trả kết quả dạng ArrayList
     *
     * @return ArrayList HoKhau
     * @throws SQLException
     */
    public static ArrayList<HoKhau> getHoKhau() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from hokhau");
        ArrayList<HoKhau> arrayList = new ArrayList<>();
        while (rs.next()) {
            arrayList.add(new HoKhau(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6)));
        }
        return arrayList;
    }

    /**
     * lấy thông tin 1 hộ khẩu
     * @param idho
     * @return
     * @throws SQLException
     */
    public static HoKhau getHoKhau(int idho) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from hokhau where idho = "+idho+";");
        HoKhau hoKhau = null;
        while (rs.next()) {
            hoKhau = new HoKhau(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
        }
        return hoKhau;
    }

    public static void setHoKhau(HoKhau hoKhau) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE hokhau SET diachi = '"+hoKhau.getDiachi()+"', ghichu ='"+hoKhau.getGhichu()+"', placeid = '"+hoKhau.getPlaceid()+"' WHERE idho = " + hoKhau.getIdho() + ";");
    }
    /**
     * lấy thông tin tất cả nhân khẩu, trả kết quả dạng ArrayList
     *
     * @return ArrayList NhanKhau
     * @throws SQLException
     */
    public static ArrayList<NhanKhau> getNhanKhau() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from nhankhau");
        ArrayList<NhanKhau> arrayList = new ArrayList<NhanKhau>();
        while (rs.next()) {
            arrayList.add(new NhanKhau(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
                    rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
        }
        return arrayList;
    }

    /**
     * lấy thông tin 1 nhân khẩu
     * @param id
     * @return
     * @throws SQLException
     */
    public static NhanKhau getNhanKhau(int id) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from nhankhau where id = "+id+";");
        NhanKhau nhanKhau = null;
        while (rs.next()) {
            nhanKhau = new NhanKhau(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
                    rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17));
        }
        return nhanKhau;
    }

    /**
     * thêm 1 nhân khẩu mới
     *
     * @param nhanKhau Nhân khẩu
     * @throws SQLException
     */
    public static void themNhanKhau(NhanKhau nhanKhau) throws SQLException {
        Statement stmt = con.createStatement();
        String sql1 = "INSERT INTO nhankhau(idho, hoten, gioitinh, ngaysinh, noisinh";
        String sql2 = ") VALUES('" + nhanKhau.getIdho() + "', '" + nhanKhau.getHoten() + "', '" + nhanKhau.getGioitinh() + "', '" +
                nhanKhau.getNgaysinh() + "', '" + nhanKhau.getNoisinh() + "'";
        if (!nhanKhau.getQuanhech().isEmpty()) {
            sql1 += ", quanhech";
            sql2 += ", '" + nhanKhau.getQuanhech() + "'";
        }
        if (!nhanKhau.getNguyenquan().isEmpty()) {
            sql1 += ", nguyenquan";
            sql2 += ", '" + nhanKhau.getNguyenquan() + "'";
        }
        if (!nhanKhau.getDantoc().isEmpty()) {
            sql1 += ", dantoc";
            sql2 += ", '" + nhanKhau.getDantoc() + "'";
        }
        if (!nhanKhau.getNghenghiep().isEmpty()) {
            sql1 += ", nghenghiep";
            sql2 += ", '" + nhanKhau.getNghenghiep() + "'";
        }
        if (!nhanKhau.getNoilamviec().isEmpty()) {
            sql1 += ", noilamviec";
            sql2 += ", '" + nhanKhau.getNoilamviec() + "'";
        }
        if (!nhanKhau.getCmnd().isEmpty()) {
            sql1 += ", cmnd";
            sql2 += ", '" + nhanKhau.getCmnd() + "'";
        }
        if (!nhanKhau.getNgaycap().isEmpty()) {
            sql1 += ", ngaycap";
            sql2 += ", '" + nhanKhau.getNgaycap() + "'";
        }
        if (!nhanKhau.getNoicap().isEmpty()) {
            sql1 += ", noicap";
            sql2 += ", '" + nhanKhau.getNoicap() + "'";
        }
        if (!nhanKhau.getNdkthuongtru().isEmpty()) {
            sql1 += ", ndkthuongtru";
            sql2 += ", '" + nhanKhau.getNdkthuongtru() + "'";
        }
        if (!nhanKhau.getDcthuongtrutrc().isEmpty()) {
            sql1 += ", dcthuongtrutrc";
            sql2 += ", '" + nhanKhau.getDcthuongtrutrc() + "'";
        }
        stmt.execute(sql1 + sql2 + ");");
    }

    /**
     * lấy dữ liệu ghi chú của 1 nhân khẩu
     *
     * @param id số thứ tự nhân khẩu
     * @return String ghi chú
     * @throws SQLException
     */
    public static String getGhiChuNhanKhau(int id) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ghichu FROM nhankhau WHERE id = " + id + ";");
        rs.next();
        if (rs.getString(1) == null)
            return "";
        return rs.getString(1);
    }

    /**
     * thêm ghi chú 1 nhân khẩu
     *
     * @param id     số thứ tự nhân khẩu
     * @param ghiChu ghi chú
     * @throws SQLException
     */
    public static void setGhiChuNhanKhau(int id, String ghiChu) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE nhankhau SET ghichu = '" + getGhiChuNhanKhau(id) + "\n" + ghiChu + "' WHERE ID = " + id + ";");
    }

    /**
     * Tách hộ
     *
     * @param listName ArrayList về tên những người tách, người cuối cùng là chủ tách
     * @param diachi   địa chỉ hộ khẩu mới
     * @param idho     số hộ khẩu cũ
     * @param idhoMoi  số hộ khẩu mới
     * @return 0:đang là chủ hộ, 1:thay chủ hộ, 2:tách hộ, 3: tách hộ cùng 1 chủ hộ
     * @throws SQLException
     */
    public static int tachHo(ArrayList<String> listName, String diachi, String placeid, int idho, int idhoMoi) throws SQLException {
        Statement stmt = con.createStatement();
        /**
         * kiểm tra xem người tách hộ có đang là chủ hộ không
         */
        ResultSet rs = stmt.executeQuery("SELECT hotenchu FROM hokhau WHERE idho = " + idho + ";");
        rs.next();
        if (listName.get(listName.size() - 1).equals(rs.getString(1)))
            return 0;
        /**
         * kiểm tra xem người tách cùng có ai là chủ hộ không
         */
        for (int i = 0; i < listName.size() - 1; i++) {
            if (listName.get(i).equals(rs.getString(1)))
                return 3;
        }
        /**
         * kiểm tra xem 2 số hộ khẩu trùng nhau không, trùng là đổi chủ hộ
         * đổi tên chủ hộ
         * đổi tất cả thành viên trong hộ có quan hệ thành người thân
         * đặt quan hệ là chủ với chủ hộ
         */
        if (idho == idhoMoi) {
            stmt.execute("UPDATE hokhau SET hotenchu = '" + listName.get(listName.size() - 1) + "' WHERE idho = " + idho + ";");
            stmt.execute("UPDATE nhankhau SET quanhech = 'Người thân' WHERE idho = " + idho + ";");
            stmt.execute("UPDATE nhankhau SET quanhech = 'Chủ' WHERE hoten = '" + listName.get(listName.size() - 1) + "';");
            return 1;
        }
        /**
         * tách hộ
         * thêm hộ khẩu mới
         * đổi số hộ khẩu của những ai cùng tách
         * đổi tất cả thành viên cùng tách có quan hệ thành người thân
         * đặt quan hệ là chủ với chủ hộ
         */
        stmt.execute("INSERT INTO hokhau (idho,hotenchu,diachi,placeid) VALUES (" + idhoMoi + ", '" + listName.get(listName.size() - 1) + "', '" + diachi + "', '"+placeid+"')");
        String s = new String();
        s = "'" + listName.get(0) + "'";
        for (int i = 1; i < listName.size(); i++) {
            s += ", '" + listName.get(i) + "'";
        }
        stmt.execute("UPDATE nhankhau SET idho = '" + idhoMoi + "' WHERE hoten IN (" + s + ");");
        stmt.execute("UPDATE nhankhau SET quanhech = 'Người thân' WHERE idho = " + idhoMoi + ";");
        stmt.execute("UPDATE nhankhau SET quanhech = 'Chủ' WHERE hoten = '" + listName.get(listName.size() - 1) + "';");
        return 2;
    }

    /**
     * lấy vị trí và id vị trí
     * @param data
     * @return
     */
    public static ArrayList<String> getDanhSachViTri(String data) {
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            URL url = new URL("https://maps.googleapis.com/maps/api/place/autocomplete/json?input="+data.replaceAll(" ","%20")+"&key=AIzaSyC4I" +
                    "QWtwBPMTTWxY82Qohy5C" +
                    "-ddKnSBuwM");
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();
            JsonArray jsonArray = jsonObject.getJsonArray("predictions");
            arrayList.add(jsonArray.get(0).asJsonObject().getString("description"));
            arrayList.add(jsonArray.get(0).asJsonObject().getString("place_id"));
            return arrayList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public static void setTamTruTamVang(String hoTen, String ngaySinh, String gioiTinh, String noiThuongTru, String cmnd, String quocTich,
    String hoChieu, String tuNgay, String denNgay, String lyDo, int mode) throws SQLException {
        String ghichu="";
        if(quocTich.trim().length()!=0)
            ghichu+="Quốc tịch: "+quocTich+". ";
        if(hoChieu.trim().length()!=0)
            ghichu+="Hộ chiếu: "+hoChieu+". ";
        if(mode==0)
            ghichu+="Tạm vắng ";
        else
            ghichu+="Tạm trú ";
        if(tuNgay.trim().length()!=0)
            ghichu+="từ ngày "+tuNgay+" ";
        if(denNgay.trim().length()!=0)
            ghichu+="đến ngày "+tuNgay+" ";
        ghichu+=lyDo;
        themNhanKhau(new NhanKhau(0,hoTen,gioiTinh,ngaySinh,noiThuongTru,cmnd,ghichu));
    }
    public  static ArrayList<Double> getXY(String placeid){
        try {
            ArrayList<Double> arrayList = new ArrayList<>();
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?place_id=" +
                    placeid +
                    "&key=AIzaSyC4IQWtwB" +
                    "PMTTWxY82Qohy5C-" +
                    "ddKnSBuwM");
            InputStream inputStream = url.openStream();
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonObject jsonObject = jsonReader.readObject();
            JsonArray jsonArray = jsonObject.getJsonArray("results");
            arrayList.add(Double.parseDouble(jsonArray.get(0).asJsonObject().getJsonObject("geometry").getJsonObject("location").get("lat").toString()));
            arrayList.add(Double.parseDouble(jsonArray.get(0).asJsonObject().getJsonObject("geometry").getJsonObject("location").get("lng").toString()));
            return arrayList;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
