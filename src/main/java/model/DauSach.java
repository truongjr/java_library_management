/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class DauSach {
    private final String maDauSach;
    private String tenDauSach;
    private final String maLoaiSach;
    private String tacGia;
    private String nhaXuatBan;
    private String namXuatBan;

    public DauSach(String maDauSach, String tenDauSach, String maLoaiSach, String tacGia, String nhaXuatBan, String namXuatBan) {
        this.maDauSach = maDauSach;
        this.tenDauSach = tenDauSach;
        this.maLoaiSach = maLoaiSach;
        this.tacGia = tacGia;
        this.nhaXuatBan = nhaXuatBan;
        this.namXuatBan = namXuatBan;
    }

    public String getMaDauSach() {
        return maDauSach;
    }

    public String getTenDauSach() {
        return tenDauSach;
    }

    public void setTenDauSach(String tenDauSach) {
        this.tenDauSach = tenDauSach;
    }

    public String getMaLoaiSach() {
        return maLoaiSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(String namXuatBan) {
        this.namXuatBan = namXuatBan;
    }
}
