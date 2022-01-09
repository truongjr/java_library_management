/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author nguye
 */
public class ThongTinPhieuMuon extends ArrayList<ThongTinPhieuMuon> {
    private final String maPhieuMuon;
    private final String maDocGia;

    public ThongTinPhieuMuon(int initialCapacity, String maPhieuMuon, String maDocGia) {
        super(initialCapacity);
        this.maPhieuMuon = maPhieuMuon;
        this.maDocGia = maDocGia;
    }

    public ThongTinPhieuMuon(String phieuMuonID, String maDocGia) {
        this.maPhieuMuon = phieuMuonID;
        this.maDocGia = maDocGia;
    }

    public ThongTinPhieuMuon(Collection<? extends ThongTinPhieuMuon> c, String phieuMuonID, String maDocGia) {
        super(c);
        this.maPhieuMuon = phieuMuonID;
        this.maDocGia = maDocGia;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaDocGia() {
        return maDocGia;
    }
}
