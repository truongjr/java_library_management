package process;

import DataAccessObject.SQLConnection;
import model.PhieuMuon;
import model.DanhMucSachModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class XuLyTra {
    private ArrayList<DanhMucSachModel> danhSachDanhMucSachModel;
    private ArrayList<PhieuMuon> danhSachPhieuMuon;
    private ArrayList<ArrayList<String>> dataJoin;
    private final int maDocGia;
    public static int THANH_CONG = 0;
    public static int LOI = 1;
    public XuLyTra(int maDocGia) {
        dataJoin = new ArrayList<>();
        danhSachPhieuMuon = new ArrayList<>();
        danhSachDanhMucSachModel = new ArrayList<>();
        this.maDocGia = maDocGia;
        Connection con = SQLConnection.openConnection();
        String query = "SELECT B.MaPhieuMuon, DAUSACH.TenDauSach, B.MaDanhMucSach, B.NgayMuon, B.NgayTra, B.TrangThai\n" +
                "FROM \n" +
                "(SELECT A.MaPhieuMuon, A.MaDanhMucSach, DANHMUCSACH.ISBN, A.NgayMuon, A.NgayTra, A.TrangThai\n" +
                "\tFROM \n" +
                "\t\t(SELECT * \n" +
                "\t\t\tFROM PHIEUMUON \n" +
                "\t\t\t\tWHERE MaDocGia = ?) AS A  \n" +
                "\t\t\t\t\tINNER JOIN DANHMUCSACH ON A.MaDanhMucSach = DANHMUCSACH.MaDanhMucSach) AS B \n" +
                "\t\t\t\t\t\tINNER JOIN DAUSACH ON B.ISBN = DAUSACH.ISBN";
        try {
            assert con != null;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, maDocGia);
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                ArrayList<String> temp = new ArrayList<>();
                for(int i = 1; i <= 6; i++){
                    if(i == 6){
                        temp.add(res.getString(i).equals("1") ? "Đang mượn" : "Đã trả");
                    } else {
                        temp.add(res.getString(i));
                    }
                }
                dataJoin.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> getDataJoin() {
        return dataJoin;
    }

    public int traSach(String maDanhMucSach, int trangThaiSach){
        Connection con = SQLConnection.openConnection();
        String query = "UPDATE dbo.DANHMUCSACH SET TrangThai = ? WHERE MaDanhMucSach = ?";
        try {
            assert con != null;
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, trangThaiSach);
            preparedStatement.setString(2, maDanhMucSach);
            preparedStatement.executeUpdate();
            query = "UPDATE dbo.PHIEUMUON SET TrangThai = ?, NgayTra = ? WHERE MaDanhMucSach = ? AND MaDocGia = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, 0);
            preparedStatement.setDate(2, new java.sql.Date((new java.util.Date()).getTime()));
            preparedStatement.setString(3, maDanhMucSach);
            preparedStatement.setInt(4, maDocGia);
            preparedStatement.executeUpdate();
            for(DanhMucSachModel item:danhSachDanhMucSachModel){
                if(item.getMaDanhMucSach().equals(maDanhMucSach)){
                    item.setTrangThaiSach(trangThaiSach);
                }
            }
            for(ArrayList<String> item:dataJoin){
                if(item.get(2).equals(maDanhMucSach)){
                    item.set(5, "Đã trả");
                    item.set(4, String.valueOf((new java.util.Date()).getTime()));
                }
            }
            return THANH_CONG;
        } catch (SQLException e) {
            e.printStackTrace();
            return LOI;
        }
    }
}
