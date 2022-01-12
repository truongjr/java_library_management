package process;

import DataAccessObject.SQLConnection;
import UI.TrangChu;
import model.DanhMucSachModel;
import model.DauSach;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XuLyQuanLySach {
    private ArrayList<DauSach> danhSachDauSach;
    private ArrayList<DanhMucSachModel> danhSachDanhMucSach;
    public static int LOI_ISBN = 0;
    public static int LOI_ISBN_TRUNG = 8;
    public static int LOI_TEN_SACH = 1;
    public static int LOI_THE_LOAI = 2;
    public static int LOI_TAC_GIA = 3;
    public static int LOI_NHA_XUAT_BAN = 4;
    public static int LOI_NAM_XUAT_BAN = 5;
    public static int THANH_CONG = 6;
    public static int LOI_BAT_DINH = 7;
    public static int LOI_XOA = 9;
    public XuLyQuanLySach() {
        danhSachDanhMucSach = new ArrayList<>();
        danhSachDauSach = new ArrayList<>();

        Connection connection = SQLConnection.openConnection();
        try {
            Statement stm = connection.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM dbo.DAUSACH");
            while(res.next()){
                danhSachDauSach.add(new DauSach(res.getString("ISBN"), res.getString("TenDauSach"), res.getString("TenLoaiSach"), res.getString("TacGia"), res.getString("NhaXuatBan"), res.getInt("NamXuatBan")));
            }
            res = stm.executeQuery("SELECT * FROM dbo.DANHMUCSACH");
            while(res.next()){
                danhSachDanhMucSach.add(new DanhMucSachModel(res.getString("MaDanhMucSach"), res.getString("ISBN"), Integer.parseInt(res.getString("TrangThai"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DauSach> getDanhSachDauSach() {
        return danhSachDauSach;
    }

    public ArrayList<DanhMucSachModel> getDanhSachDanhMucSach() {
        return danhSachDanhMucSach;
    }

    public int themSach(String ISBN, String tenDauSach, String tenLoaiSach, String tacGia, String nhaXuatBan, String namXuatBan){
        if(ISBN.equals("")){
            return LOI_ISBN;
        }
        if(tenDauSach.equals("")){
            return LOI_TEN_SACH;
        }
        if(tenLoaiSach.equals("")){
            return LOI_THE_LOAI;
        }
        if(tacGia.equals("")){
            return LOI_TAC_GIA;
        }
        if(nhaXuatBan.equals("")){
            return LOI_NHA_XUAT_BAN;
        }
        if(namXuatBan.equals("")){
            return LOI_NAM_XUAT_BAN;
        }
        for(DauSach it:danhSachDauSach){
            if(ISBN.equals(it.getISBN())){
                return LOI_ISBN_TRUNG;
            }
        }
        danhSachDauSach.add(new DauSach(ISBN, tenDauSach, tenLoaiSach, tacGia, nhaXuatBan, Integer.parseInt(namXuatBan)));
        Connection con = SQLConnection.openConnection();
        String query = "INSERT INTO dbo.DAUSACH(ISBN, TheLoaiSach, TenDauSach, TacGia, NhaXuatBan, NamXuatBan) VALUES(?,?,?,?,?,?)";
        try {
            assert con != null;
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, ISBN);
            pstmt.setString(2, tenLoaiSach);
            pstmt.setString(3, tenDauSach);
            pstmt.setString(4, tacGia);
            pstmt.setString(5, nhaXuatBan);
            pstmt.setInt(6, Integer.parseInt(namXuatBan));
            int addSuccess = pstmt.executeUpdate();
            if(addSuccess == 1) {
                return THANH_CONG;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LOI_BAT_DINH;
    }
    public int chinhSuaSach(int index, String ISBN, String tenSach, String theLoai, String tacGia, String nhaXuatBan, String namXuatBan){
        if(ISBN.equals("")){
            return LOI_ISBN;
        }
        if(tenSach.equals("")){
            return LOI_TEN_SACH;
        }
        if(theLoai.equals("")){
            return LOI_THE_LOAI;
        }
        if(tacGia.equals("")){
            return LOI_TAC_GIA;
        }
        if(nhaXuatBan.equals("")){
            return LOI_NHA_XUAT_BAN;
        }
        if(namXuatBan.equals("")){
            return LOI_NAM_XUAT_BAN;
        }
        for(DauSach it:danhSachDauSach){
            if(ISBN.equals(it.getISBN())){
                return LOI_ISBN_TRUNG;
            }
        }
        danhSachDauSach.set(index, new DauSach(ISBN, tenSach, theLoai, tacGia, nhaXuatBan, Integer.parseInt(namXuatBan)));
        Connection con = SQLConnection.openConnection();
        String query = "UPDATE dbo.DAUSACH SET TheLoai=?, TenDauSach=?, TacGia=?, NhaXuatBan=?, NamXuatBan=? WHERE ISBN = ?";
        try {
            assert con != null;
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(6, ISBN);
            pstmt.setString(1, theLoai);
            pstmt.setString(2, tenSach);
            pstmt.setString(3, tacGia);
            pstmt.setString(4, nhaXuatBan);
            pstmt.setInt(5, Integer.parseInt(namXuatBan));
            pstmt.executeUpdate();
            return THANH_CONG;
        } catch (SQLException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
            return LOI_BAT_DINH;
        }
    }
    public int xoaSach(int index){
        Connection con = SQLConnection.openConnection();
        int soLuongDanhMucSach = 0;
        try {
            assert con != null;
            PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(MaDanhMucSach) FROM dbo.DANHMUCSACH WHERE ISBN =?");
            pstmt.setString(1, danhSachDanhMucSach.get(index).getMaDauSach());
            ResultSet res = pstmt.executeQuery();// + danhSachDanhMucSach.get(index).getMaDauSach() + "'");
            if(res.next()){
                soLuongDanhMucSach = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(soLuongDanhMucSach > 0){
            return LOI_XOA;
        }
        String query = "DELETE FROM dbo.DAUSACH WHERE ISBN=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, danhSachDauSach.get(index).getISBN());
            pstmt.executeUpdate();
            danhSachDauSach.remove(index);
            return THANH_CONG;
        } catch (SQLException e) {
            e.printStackTrace();
            return LOI_BAT_DINH;
        }
    }
}
