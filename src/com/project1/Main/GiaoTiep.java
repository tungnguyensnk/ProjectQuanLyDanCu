package com.project1.Main;

import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<HoKhau> getHoKhau() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from hokhau");
        ArrayList<HoKhau> arrayList = new ArrayList<HoKhau>();
        while (rs.next()) {
            arrayList.add(new HoKhau(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5)));
        }
        return arrayList;
    }

    public static ArrayList<NhanKhau> getNhanKhau() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from nhankhau");
        ArrayList<NhanKhau> arrayList = new ArrayList<NhanKhau>();
        while (rs.next()) {
            arrayList.add(new NhanKhau(rs.getInt(2),rs.getInt(1), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
                    rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
        }
        return arrayList;
    }
    public static void themNhanKhau(NhanKhau nhanKhau) throws SQLException {
        Statement stmt = con.createStatement();
        String sql1 = "INSERT INTO nhankhau(idho, hoten, gioitinh, ngaysinh, noisinh";
        String sql2 = ") VALUES('"+nhanKhau.getIdho()+"', '"+nhanKhau.getHoten()+"', '"+nhanKhau.getGioitinh()+"', '"+
                nhanKhau.getNgaysinh()+"', '"+nhanKhau.getNoisinh()+"'";
        if(!nhanKhau.getQuanhech().isEmpty()){
            sql1+=", quanhech";
            sql2+=", '"+nhanKhau.getQuanhech()+"'";
        }
        if(!nhanKhau.getNguyenquan().isEmpty()){
            sql1+=", nguyenquan";
            sql2+=", '"+nhanKhau.getNguyenquan()+"'";
        }
        if(!nhanKhau.getDantoc().isEmpty()){
            sql1+=", dantoc";
            sql2+=", '"+nhanKhau.getDantoc()+"'";
        }
        if(!nhanKhau.getNghenghiep().isEmpty()){
            sql1+=", nghenghiep";
            sql2+=", '"+nhanKhau.getNghenghiep()+"'";
        }
        if(!nhanKhau.getNoilamviec().isEmpty()){
            sql1+=", noilamviec";
            sql2+=", '"+nhanKhau.getNoilamviec()+"'";
        }
        if(!nhanKhau.getCmnd().isEmpty()){
            sql1+=", cmnd";
            sql2+=", '"+nhanKhau.getCmnd()+"'";
        }
        if(!nhanKhau.getNgaycap().isEmpty()){
            sql1+=", ngaycap";
            sql2+=", '"+nhanKhau.getNgaycap()+"'";
        }
        if(!nhanKhau.getNoicap().isEmpty()){
            sql1+=", noicap";
            sql2+=", '"+nhanKhau.getNoicap()+"'";
        }
        if(!nhanKhau.getNdkthuongtru().isEmpty()){
            sql1+=", ndkthuongtru";
            sql2+=", '"+nhanKhau.getNdkthuongtru()+"'";
        }
        if(!nhanKhau.getDcthuongtrutrc().isEmpty()){
            sql1+=", dcthuongtrutrc";
            sql2+=", '"+nhanKhau.getDcthuongtrutrc()+"'";
        }
        stmt.execute(sql1+sql2+");");
    }
    public static String getGhiChuNhanKhau(int id) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ghichu FROM nhankhau WHERE id = "+id+";");
        rs.next();
        if(rs.getString(1)==null)
            return "";
        return rs.getString(1);
    }
    public static void setGhiChuNhanKhau(int id,String ghiChu) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE nhankhau SET ghichu = '"+getGhiChuNhanKhau(id)+"\n"+ghiChu+"' WHERE ID = "+id+";");
    }
    public static int tachHo(ArrayList<String> listName,String diachi,int idho,int idhoMoi) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT hotenchu FROM hokhau WHERE idho = "+idho+";");
        rs.next();
        if(listName.get(listName.size()-1).equals(rs.getString(1)))
            return 0;
        for(int i=0;i<listName.size()-1;i++){
            if(listName.get(i).equals(rs.getString(1)))
                return 3;
        }
        if(idho==idhoMoi){
            stmt.execute("UPDATE hokhau SET hotenchu = '"+listName.get(listName.size()-1)+"' WHERE idho = "+idho+";");
            stmt.execute("UPDATE nhankhau SET quanhech = 'Người thân' WHERE idho = "+idho+";");
            stmt.execute("UPDATE nhankhau SET quanhech = 'Chủ' WHERE hoten = '"+listName.get(listName.size()-1)+"';");
            return 1;
        }
        stmt.execute("INSERT INTO hokhau (idho,hotenchu,diachi) VALUES ("+idhoMoi+", '"+listName.get(listName.size()-1)+"', '"+diachi+"')");
        String s = new String();
        s="'"+listName.get(0)+"'";
        for (int i=1;i<listName.size();i++){
            s+=", '"+listName.get(i)+"'";
        }
        stmt.execute("UPDATE nhankhau SET idho = '"+idhoMoi+"' WHERE hoten IN ("+s+");");
        stmt.execute("UPDATE nhankhau SET quanhech = 'Người thân' WHERE idho = "+idhoMoi+";");
        stmt.execute("UPDATE nhankhau SET quanhech = 'Chủ' WHERE hoten = '"+listName.get(listName.size()-1)+"';");
        return 2;
    }
}
