package process;

import DataAccessObject.SQLConnection;
import UI.TrangChu;
import model.DocGia;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XuLyQuanLyDocGia {
    private ArrayList<DocGia> danhSachDocGia;
    public static int LOI_HO = 0;
    public static int LOI_TEN = 1;
    public static int LOI_EMAIL = 2;
    public static int LOI_NGAY_SINH = 3;
    public static int LOI_SDT = 4;
    public static int THANH_CONG = 5;
    public static int LOI_BAT_DINH = 6;
    public XuLyQuanLyDocGia() {
        danhSachDocGia = new ArrayList<DocGia>();
        Connection connection = SQLConnection.openConnection();
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM dbo.DOCGIA");
            while(rs.next()) {
                danhSachDocGia.add(new DocGia(Integer.parseInt(rs.getString("MaDocGia")),
                        rs.getString("Ho"),
                        rs.getString("Ten"),
                        rs.getString("GioiTinh").equals("1"),
                        rs.getString("NgaySinh"),
                        rs.getString("Email"),
                        rs.getString("SDT"),
                        rs.getString("TrangThai").equals("1")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DocGia> getDanhSachDocGia() {
        return danhSachDocGia;
    }

    private boolean checkInList(String maDocGia) {
        for (DocGia docGia: danhSachDocGia) {
            if (docGia.getMaDocGia() == Integer.parseInt(maDocGia)) {
                return true;
            }
        }
        return false;
    }
    public int themDocGia(String ho, String ten, boolean gioiTinh, String ngaySinh, String email, String sdt) {
        ChuanHoaChuoi chuanHoa = new ChuanHoaChuoi(ho);
        chuanHoa.chuanHoaCap3();
        ho = chuanHoa.getString();
        if (ho.equals("")) {
            return LOI_HO;
        }
        chuanHoa.setString(ten);
        chuanHoa.chuanHoaCap3();
        ten = chuanHoa.getString();
        if (ten.equals("")) {
            return LOI_TEN;
        }
        chuanHoa.setString(ngaySinh);
        chuanHoa.chuanHoaCap1();
        ngaySinh = chuanHoa.getString();
        if (ngaySinh.equals("")) {
            return LOI_NGAY_SINH;
        }
        chuanHoa.setString(email);
        chuanHoa.chuanHoaCap1();
        email = chuanHoa.getString();
        if (email.equals("")) {
            return LOI_EMAIL;
        } else {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (!email.matches(regex)) {
                return LOI_EMAIL;
            }
        }
        chuanHoa.setString(sdt);
        chuanHoa.chuanHoaCap1();
        sdt = chuanHoa.getString();
        if (sdt.equals("")) {
            return LOI_SDT;
        } else {
            if (sdt.length() < 10 || sdt.length() > 11) {
                return LOI_SDT;
            }
        }
        Random random = new Random();
        String maDocGia = "";
        while(!checkInList(maDocGia) || maDocGia.equals("")) {
            maDocGia = String.valueOf(random.nextInt(99999) + 10000);
        }
        danhSachDocGia.add(new DocGia(Integer.parseInt(maDocGia), ho, ten, gioiTinh, email, ngaySinh, sdt, true));
        String query = "INSERT INTO dbo.DOCGIA VALUES (?,?,?,?,?,?,?,?)";
        Connection con = SQLConnection.openConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, maDocGia);
            pstmt.setString(2, ho);
            pstmt.setString(3, ten);
            pstmt.setString(4, gioiTinh ? "1": "0");
            pstmt.setString(5, email);
            pstmt.setString(6, ngaySinh);
            pstmt.setString(7, sdt);
            pstmt.setString(8,"1");
            pstmt.executeUpdate();
            return THANH_CONG;
        } catch (SQLException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
            return LOI_BAT_DINH;
        }
    }
    public int chinhSuaDocGia(int index, String ho, String ten, boolean gioiTinh, String ngaySinh, String email, String sdt, boolean hoatDong) {
        ChuanHoaChuoi chuanHoa = new ChuanHoaChuoi(ho);
        chuanHoa.chuanHoaCap3();
        ho = chuanHoa.getString();
        if (ho.equals("")) {
            return LOI_HO;
        }
        chuanHoa.setString(ten);
        chuanHoa.chuanHoaCap3();
        ten = chuanHoa.getString();
        if (ten.equals("")) {
            return LOI_TEN;
        }
        if (ngaySinh.equals("")) {
            return LOI_NGAY_SINH;
        }
        chuanHoa.setString(email);
        chuanHoa.chuanHoaCap1();
        email = chuanHoa.getString();
        if (email.equals("")) {
            return LOI_EMAIL;
        } else {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if (!email.matches(regex)) {
                return LOI_EMAIL;
            }
        }
        chuanHoa.setString(sdt);
        chuanHoa.chuanHoaCap1();
        sdt = chuanHoa.getString();
        if (sdt.equals("")) {
            return LOI_SDT;
        } else {
            if (sdt.length() < 10 || sdt.length() > 11) {
                return LOI_SDT;
            }
        }
        int maDocGia = danhSachDocGia.get(index).getMaDocGia();
        danhSachDocGia.add(new DocGia(maDocGia, ho, ten, gioiTinh, email, ngaySinh, sdt, hoatDong));
        Connection con = SQLConnection.openConnection();
        String query = "UPDATE dbo.DOCGIA SET Ho=?, Ten=?, GioiTinh=?, NgaySinh=?, Email=?, SDT=?, TrangThai=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, ho);
            pstmt.setString(2, ten);
            pstmt.setString(3, gioiTinh ? "1": "0");
            pstmt.setString(4, ngaySinh);
            pstmt.setString(5, email);
            pstmt.setString(6, sdt);
            pstmt.setString(7, hoatDong ? "1" : "0");
            pstmt.executeUpdate();
            return THANH_CONG;
        } catch (SQLException ex) {
            Logger.getLogger(TrangChu.class.getName()).log(Level.SEVERE, null, ex);
            return LOI_BAT_DINH;
        }
    }
    public int xoaDocGia(int index) {
        Connection con = SQLConnection.openConnection();
        String maDocGia = String.valueOf(danhSachDocGia.get(index).getMaDocGia());
        String query = "DELETE FROM dbo.DAUSACH WHERE MaDocGia=?";
        try {
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, maDocGia);
            pstmt.executeUpdate();
            danhSachDocGia.remove(index);
            return THANH_CONG;
        } catch (SQLException e) {
            e.printStackTrace();
            return LOI_BAT_DINH;
        }
    }
}