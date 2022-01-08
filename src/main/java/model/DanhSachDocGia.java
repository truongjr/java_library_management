package model;

import javax.print.Doc;
import java.util.ArrayList;

public class DanhSachDocGia {
    private ArrayList<DocGia> danhSachDocGia;

    public DanhSachDocGia(ArrayList<DocGia> danhSachDocGia) {
        this.danhSachDocGia = danhSachDocGia;
    }

    public ArrayList<DocGia> getDanhSachDocGia() {
        return danhSachDocGia;
    }

    public void setDanhSachDocGia(ArrayList<DocGia> danhSachDocGia) {
        this.danhSachDocGia = danhSachDocGia;
    }
}
