package process;

import DataAccessObject.SQLConnection;
import model.DanhMucSachModel;
import model.DauSach;
import model.PhieuMuon;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class XuLyMuon {
    private ArrayList<DauSach> danhSachDauSach;
    private ArrayList<DanhMucSachModel> danhSachDanhMucSach;
    private int maDocGia;
    public static int THANH_CONG = 0;
    public static int LOI = 1;
    public static int SACH_KHONG_SAN_SANG = 2;
    public static int MUON_HON_3_CUON = 3;
    private int soLuongDangMuon;
    public XuLyMuon(int maDocGia) {
        danhSachDauSach = new ArrayList<>();
        danhSachDanhMucSach = new ArrayList<>();
        this.maDocGia = maDocGia;
        soLuongDangMuon = 0;
        Connection con = SQLConnection.openConnection();
        String query = "SELECT * FROM dbo.DAUSACH";
        try {
            assert con != null;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()){
                danhSachDauSach.add(new DauSach(res.getString("ISBN"), res.getString("TenDauSach"), res.getString("TenLoaiSach"), res.getString("TacGia"), res.getString("NhaXuatBan"), res.getInt("NamXuatBan")));
            }
            query = "SELECT * FROM dbo.DANHMUCSACH";
            preparedStatement = con.prepareStatement(query);
            res = preparedStatement.executeQuery();
            while (res.next()){
                danhSachDanhMucSach.add(new DanhMucSachModel(res.getString("MaDanhMucSach"), res.getString("ISBN"), res.getInt("TrangThai")));
            }
            query = "SELECT * FROM dbo.PHIEUMUON WHERE MaDocGia = ? AND TrangThai = 0";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, maDocGia);
            res = preparedStatement.executeQuery();
            while(res.next()){
                soLuongDangMuon++;
            }
            res.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DauSach> getDanhSachDauSach() {
        return danhSachDauSach;
    }

    public int muonSach(String ISBN){
        if(soLuongDangMuon <= 3){
            for(DanhMucSachModel item:danhSachDanhMucSach){
                if(item.getMaDauSach().equals(ISBN) && item.getTrangThaiSach() == 0){
                    item.setTrangThaiSach(1);
                    Connection con = SQLConnection.openConnection();
                    String query = "INSERT INTO dbo.PHIEUMUON(MaDocGia, MaDanhMucSach, NgayMuon, TrangThai) VALUES(?, ?, ?, ?)";
                    try {
                        assert con != null;
                        PreparedStatement preparedStatement = con.prepareStatement(query);
                        preparedStatement.setInt(1, maDocGia);
                        preparedStatement.setString(2, item.getMaDanhMucSach());
                        preparedStatement.setDate(3, new java.sql.Date((new Date()).getTime()));
                        //                    preparedStatement.setDate(5, null);
                        preparedStatement.setInt(4, 1);
                        int c = preparedStatement.executeUpdate();
                        if(c == 1) System.out.println("YES");
                        query = "UPDATE dbo.DANHMUCSACH SET TrangThai = ? WHERE MaDanhMucSach = ?";
                        preparedStatement = con.prepareStatement(query);
                        preparedStatement.setInt(1, 1);
                        preparedStatement.setString(2, item.getMaDanhMucSach());
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                        con.close();
                        return THANH_CONG;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return LOI;
                    }
                }
            }
            return SACH_KHONG_SAN_SANG;
        } else {
            return MUON_HON_3_CUON;
        }
    }
}
