package process;

import DataAccessObject.SQLConnection;
import UI.DanhMucSach;
import model.*;

import java.sql.*;
import java.util.ArrayList;

public class XuLyDanhMucSach {
    private ArrayList<DanhMucSachModel> danhsachDanhMucSach;
    public static int THANH_CONG = 1;
    public static int LOI = 0;
    public static int LOI_XOA_SACH = 2;
    private String maDauSach;
    public XuLyDanhMucSach(String maDauSachParent) {
        this.maDauSach = maDauSachParent;
        danhsachDanhMucSach = new ArrayList<>();
        Connection con = SQLConnection.openConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM DANHMUCSACH WHERE ISBN  = '" + maDauSachParent + "'");
            while(res.next()){
                danhsachDanhMucSach.add(new DanhMucSachModel(res.getString("ISBN"), res.getString("MaSach"), res.getString("TrangThai").equals("0") ? 0 : res.getString("TrangThai").equals("1") ? 1 : 2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DanhMucSachModel> getDanhsachDanhMucSach() {
        return danhsachDanhMucSach;
    }

    public int themDanhMucSach(){
        if(danhsachDanhMucSach.size() > 0){
            danhsachDanhMucSach.add(new DanhMucSachModel(danhsachDanhMucSach.get(0).getMaDauSach(),danhsachDanhMucSach.get(0).getMaDanhMucSach() + "-" + String.valueOf(danhsachDanhMucSach.size() - 1),  0));
        } else {
            danhsachDanhMucSach.add(new DanhMucSachModel( maDauSach, maDauSach + "-0", 0));
        }
        int soLuongDanhMucSach = danhsachDanhMucSach.size();
        System.out.println(soLuongDanhMucSach);
        Connection con = SQLConnection.openConnection();
        String query = "INSERT INTO dbo.DANHMUCSACH(MaSach, ISBN, TrangThai) VALUES(?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, danhsachDanhMucSach.get(soLuongDanhMucSach - 1).getMaDanhMucSach());
            pstmt.setString(2, danhsachDanhMucSach.get(soLuongDanhMucSach - 1).getMaDauSach());
            pstmt.setInt(3, 0);
            pstmt.executeUpdate();
            return THANH_CONG;
        } catch (SQLException e) {
            e.printStackTrace();
            return LOI;
        }
    }
    public int chinhSuaDanhMucSach(int index, int selected){
        danhsachDanhMucSach.get(index).setTrangThaiSach(selected);
        Connection con = SQLConnection.openConnection();
        String query = "UPDATE dbo.DANHMUCSACH SET TrangThai = ? WHERE MaSach = ?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, String.valueOf(selected));
            pstmt.setString(2, danhsachDanhMucSach.get(index).getMaDanhMucSach());
            pstmt.executeUpdate();
            return THANH_CONG;

        } catch (SQLException e) {
            e.printStackTrace();
            return LOI;
        }
    }
    public int xoaDanhMucSach(int index){
        if(danhsachDanhMucSach.get(index).getTrangThaiSach() != 2){
            return LOI_XOA_SACH;
        }
        danhsachDanhMucSach.remove(index);
        Connection con = SQLConnection.openConnection();
        String query = "DELETE FROM dbo.DANHMUCSACH WHERE MaSach=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, danhsachDanhMucSach.get(index).getMaDanhMucSach());
            pstmt.executeUpdate();
            danhsachDanhMucSach.remove(index);
            return THANH_CONG;
        } catch (SQLException e) {
            e.printStackTrace();
            return LOI;
        }
    }
}
