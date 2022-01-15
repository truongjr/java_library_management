package DataAccessObject;

import DataAccessObject.SQLConnection;
import model.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
// đã fix
public class XuLyThongKe {
    private final ArrayList<DocGia> danhSachDocGia;
    private final ArrayList<DauSach> danhSachDauSach;
    private final ArrayList<PhieuMuon> danhSachPhieuMuon;
    private final Map<String, Integer> thongKeSoLuotMuonSach;
    public XuLyThongKe() {
        danhSachDocGia = new ArrayList<>();
        danhSachDauSach = new ArrayList<>();
        danhSachPhieuMuon = new ArrayList<>();
        thongKeSoLuotMuonSach = new HashMap<>();
        Connection connection = SQLConnection.openConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM dbo.DAUSACH");
            while (res.next()) {
                danhSachDauSach.add(new DauSach(res.getString("ISBN"),
                        res.getString("TenDauSach"),
                        res.getString("TenLoaiSach"),
                        res.getString("TacGia"),
                        res.getString("NhaXuatBan"),
                        res.getInt("NamXuatBan")));
            }
            res = statement.executeQuery("SELECT * FROM dbo.PHIEUMUON");
            while(res.next()) {
                danhSachPhieuMuon.add(new PhieuMuon(res.getInt("MaPhieuMuon"),
                        res.getInt("MaDocGia"),
                        res.getString("MaDanhMucSach"),
                        res.getString("NgayMuon"),
                        res.getString("NgayTra"),
                        res.getInt("TrangThai")));
            }
            res = statement.executeQuery("SELECT * FROM dbo.DOCGIA");
            while(res.next()) {
                danhSachDocGia.add(new DocGia(res.getInt("MaDocGia"),
                        res.getString("Ho"),
                        res.getString("Ten"),
                        res.getBoolean("GioiTinh"),
                        res.getString("NgaySinh"),
                        res.getString("Email"),
                        res.getString("SDT"),
                        res.getBoolean("TrangThai")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (DauSach dauSach: danhSachDauSach) {
            thongKeSoLuotMuonSach.put(dauSach.getISBN(), 0);
        }
    }
    // tìm kiếm nhị phân trên danh sách Đầu Sách (được sắp xếp tăng dần theo mã Đầu sách) để tìm ra được mã đầu sách cần tìm
    private int timKiemNhiPhanTrongDanhSachDauSach(String maDauSach) {
        int l = 0, r = danhSachDauSach.size() - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            int res = maDauSach.compareTo(danhSachDauSach.get(m).getISBN());
            if (res == 0)
                return m;
            if (res > 0)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }
    // thống kê danh sách các sách đang được mượn, output là thông tin của đầu sách
    public ArrayList<DauSach> thongKeSachDangMuon() {
        ArrayList<DauSach> danhSachDangMuon = new ArrayList<>();
        Set<String> tapHopMaDauSach = new HashSet<>();
        for (PhieuMuon phieuMuon: danhSachPhieuMuon) {
            int maDocGia = phieuMuon.getMaDocGia();
            int indexDocGia = timKiemNhiPhanTrongDanhSachDocGia(maDocGia);
            if (phieuMuon.getTrangThai() == 0 && danhSachDocGia.get(indexDocGia).getHoatDong()) {
                tapHopMaDauSach.add(phieuMuon.getMaDanhMucSach().split("-")[0]);
            }
        }
        for (String maDauSach: tapHopMaDauSach) {
            int index = timKiemNhiPhanTrongDanhSachDauSach(maDauSach);
            danhSachDangMuon.add(danhSachDauSach.get(index));
        }
        return danhSachDangMuon;
    }
    // thống kê danh sách các sách được mượn nhiều nhất, output là thông tin của đầu sách
    public ArrayList<DauSach> thongKeSachMuonNhieuNhat() {
        ArrayList<DauSach> danhSachSoLuotMuonNhieuNhat = new ArrayList<>();
        for (PhieuMuon phieuMuon: danhSachPhieuMuon) {
            String maDauSach = phieuMuon.getMaDanhMucSach().split("-")[0];
            thongKeSoLuotMuonSach.put(maDauSach, thongKeSoLuotMuonSach.get(maDauSach) + 1);
        }
        SortedSet<Map.Entry<String, Integer>> tapHopTangDan = new TreeSet<>((e1, e2)->{
            int soSanh = e1.getValue().compareTo(e2.getValue());
            if (soSanh == 0)
                return e1.getKey().compareTo(e2.getKey());
            return soSanh * (-1);
        });
        tapHopTangDan.addAll(thongKeSoLuotMuonSach.entrySet());
        int count = 0;
        for (Map.Entry<String, Integer> entry: tapHopTangDan) {
            int index = timKiemNhiPhanTrongDanhSachDauSach(entry.getKey());
            danhSachSoLuotMuonNhieuNhat.add(danhSachDauSach.get(index));
            count++;
            if (count == 10)
                break;
        }
        return danhSachSoLuotMuonNhieuNhat;
    }
    // tìm kiếm nhị phân trong danh sách Độc Giả (được sắp xếp tăng dần theo mã độc giả)
    private int timKiemNhiPhanTrongDanhSachDocGia(int maDocGia) {
        int l = 0, r = danhSachDocGia.size() - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if (danhSachDocGia.get(m).getMaDocGia() == maDocGia)
                return m;
            if (danhSachDocGia.get(m).getMaDocGia() < maDocGia)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }
    // thống kê độc giả chưa trả sách, output thông tin của từng độc giả
    public ArrayList<DocGia> thongKeDocGiaChuaTraSach() {
        ArrayList<DocGia> danhSachDocGiaChuaTraSach = new ArrayList<>();
        Set<Integer> tapHopMaDocGia = new HashSet<>();
        for (PhieuMuon phieuMuon: danhSachPhieuMuon) {
            int maDocGia = phieuMuon.getMaDocGia();
            int indexDocGia = timKiemNhiPhanTrongDanhSachDocGia(maDocGia);
            if (phieuMuon.getNgayTra() == null && danhSachDocGia.get(indexDocGia).getHoatDong()) {
                tapHopMaDocGia.add(phieuMuon.getMaDocGia());
            }
        }
        for (int maDocGia: tapHopMaDocGia) {
            int index = timKiemNhiPhanTrongDanhSachDocGia(maDocGia);
            danhSachDocGiaChuaTraSach.add(danhSachDocGia.get(index));
        }
        return danhSachDocGiaChuaTraSach;
    }
    // kiểm tra xem ngày hôm nay có quá hạn với ngày trả không (mặc định là phải trả sách sau 7 ngày kể từ ngày mượn)
    private boolean kiemTraNgayQuaHan(String ngay) {
        Date homNay = new Date();
        try {
            Date ngayMuon = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(ngayMuon);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date hanTra = calendar.getTime();
            if (homNay.after(hanTra)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    // thống kê danh độc giả mượn quá hạn, output là danh sách thông tin độc giả mượn quá hạn
    public ArrayList<DocGia> thongKeDocGiaMuonQuaHan() {
        ArrayList<DocGia> danhSachDocGiaMuonQuaHan = new ArrayList<>();
        Set<Integer> tapHopMaDocGia = new HashSet<>();
        for (PhieuMuon phieuMuon: danhSachPhieuMuon) {
            int trangThai = phieuMuon.getTrangThai();
            int maDocGia = phieuMuon.getMaDocGia();
            int indexDocGia = timKiemNhiPhanTrongDanhSachDocGia(maDocGia);
            if (trangThai == 0 && kiemTraNgayQuaHan(phieuMuon.getNgayMuon()) && danhSachDocGia.get(indexDocGia).getHoatDong()) {
                tapHopMaDocGia.add(phieuMuon.getMaDocGia());
            }
        }
        for (int maDocGia: tapHopMaDocGia) {
            int index = timKiemNhiPhanTrongDanhSachDocGia(maDocGia);
            danhSachDocGiaMuonQuaHan.add(danhSachDocGia.get(index));
        }
        return danhSachDocGiaMuonQuaHan;
    }
}
