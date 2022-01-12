package process;

import DataAccessObject.SQLConnection;
import model.DauSach;

import java.sql.*;
import java.util.ArrayList;

public class XuLyTraCuu {
    private final ArrayList<DauSach> danhSachDauSach;
    public XuLyTraCuu() {
        danhSachDauSach = new ArrayList<>();
        Connection connection = SQLConnection.openConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dbo.DAUSACH");
            while(resultSet.next()) {
                danhSachDauSach.add(new DauSach(
                        resultSet.getString("ISBN"),
                        resultSet.getString("TenDauSach"),
                        resultSet.getString("TenLoaiSach"),
                        resultSet.getString("TacGia"),
                        resultSet.getString("NhaXuatBan"),
                        resultSet.getInt("NamXuatBan")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DauSach> getDanhSachDauSach() {
        return danhSachDauSach;
    }

    // trả về danh sách đã được lọc theo yêu cầu. lưu ý:
    // - để thể hiện chuỗi rỗng thì khi gọi hàm chỉ cần đặt ""
    // - để thể hiện năm xuất bản không có thì khi gọi hàm ta truyền tham số -1 vào
    public ArrayList<DauSach> danhSachDauSachLocTheo(String tenDauSach, String tenLoaiSach, int namXuatBan, String nhaXuatBan, String tenTacGia) {
        ArrayList<DauSach> danhSachDauSachDaLoc = new ArrayList<>();
        if (!tenDauSach.isEmpty()) {
            if (danhSachDauSachDaLoc.isEmpty()) {
                for (DauSach dauSach : danhSachDauSach) {
                    if (dauSach.getTenDauSach().startsWith(tenDauSach)) {
                        danhSachDauSachDaLoc.add(dauSach);
                    }
                }
            } else {
                danhSachDauSachDaLoc.removeIf(e -> (!e.getTenDauSach().startsWith(tenDauSach)));
            }
        }
        if (!tenLoaiSach.isEmpty()) {
            if (danhSachDauSachDaLoc.isEmpty()) {
                for (DauSach dauSach : danhSachDauSach) {
                    if (dauSach.getTenLoaiSach().equals(tenLoaiSach)) {
                        danhSachDauSachDaLoc.add(dauSach);
                    }
                }
            } else {
                danhSachDauSachDaLoc.removeIf(e -> (!e.getTenLoaiSach().equals(tenLoaiSach)));
            }
        }
        if (namXuatBan != -1) {
            if (danhSachDauSachDaLoc.isEmpty()) {
                for (DauSach dauSach : danhSachDauSach) {
                    if (dauSach.getNamXuatBan() == namXuatBan) {
                        danhSachDauSachDaLoc.add(dauSach);
                    }
                }
            } else {
                danhSachDauSachDaLoc.removeIf(e -> (e.getNamXuatBan() != namXuatBan));
            }
        }
        if (!nhaXuatBan.isEmpty()) {
            if (danhSachDauSachDaLoc.isEmpty()) {
                for (DauSach dauSach : danhSachDauSach) {
                    if (dauSach.getNhaXuatBan().equals(nhaXuatBan)) {
                        danhSachDauSachDaLoc.add(dauSach);
                    }
                }
            } else {
                danhSachDauSachDaLoc.removeIf(e -> (!e.getNhaXuatBan().equals(nhaXuatBan)));
            }
        }
        if (!tenTacGia.isEmpty()) {
            if (danhSachDauSachDaLoc.isEmpty()) {
                for (DauSach dauSach : danhSachDauSach) {
                    if (dauSach.getTacGia().equals(tenTacGia)) {
                        danhSachDauSachDaLoc.add(dauSach);
                    }
                }
            } else {
                danhSachDauSachDaLoc.removeIf(e -> (!e.getTacGia().equals(tenTacGia)));
            }
        }
        return danhSachDauSachDaLoc;
    }
}