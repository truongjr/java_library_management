/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class ChiTietPhieuMuon {
    private final String phieuMuonID;
    private final int maSach;
    private int trangThaiPhieuMuon;
    private String ngayMuon;
    private String ngayTra;

    public ChiTietPhieuMuon(String maPhieuMuon, int maDocGia, String ngayMuon, String ngayTra, int trangThaiPhieuMuon) {
        this.phieuMuonID = maPhieuMuon;
        this.maSach = maDocGia;
        this.trangThaiPhieuMuon = trangThaiPhieuMuon;
        this.ngayMuon = ngayMuon;
        this.ngayTra = ngayTra;
    }

    public String getPhieuMuonID() {
        return phieuMuonID;
    }

    public int getMaSach() {
        return maSach;
    }

    public int getTrangThaiPhieuMuon() {
        return trangThaiPhieuMuon;
    }

    public void setTrangThaiPhieuMuon(int trangThaiPhieuMuon) {
        this.trangThaiPhieuMuon = trangThaiPhieuMuon;
    }

    public String getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(String ngayTra) {
        this.ngayTra = ngayTra;
    }
}
