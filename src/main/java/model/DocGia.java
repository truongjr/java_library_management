/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class DocGia {
    private final int maDocGia;
    private String hoDocGia;
    private String tenDocGia;
    private String gioiTinhDocGia;
    private String emailDocGia;
    private String ngaySinhDocGia;
    private String sdtDocGia;
    private boolean hoatDong;

    public DocGia(int maDocGia, String hoDocGia, String tenDocGia, String gioiTinhDocGia, String emailDocGia, String ngaySinhDocGia, String sdtDocGia, boolean hoatDong) {
        this.maDocGia = maDocGia;
        this.hoDocGia = hoDocGia;
        this.tenDocGia = tenDocGia;
        this.gioiTinhDocGia = gioiTinhDocGia;
        this.emailDocGia = emailDocGia;
        this.ngaySinhDocGia = ngaySinhDocGia;
        this.sdtDocGia = sdtDocGia;
        this.hoatDong = hoatDong;
    }

    public int getMaDocGia() {
        return maDocGia;
    }

    public String getHoDocGia() {
        return hoDocGia;
    }

    public void setHoDocGia(String hoDocGia) {
        this.hoDocGia = hoDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public String getGioiTinhDocGia() {
        return gioiTinhDocGia;
    }

    public void setGioiTinhDocGia(String gioiTinhDocGia) {
        this.gioiTinhDocGia = gioiTinhDocGia;
    }

    public String getEmailDocGia() {
        return emailDocGia;
    }

    public void setEmailDocGia(String emailDocGia) {
        this.emailDocGia = emailDocGia;
    }

    public String getNgaySinhDocGia() {
        return ngaySinhDocGia;
    }

    public void setNgaySinhDocGia(String ngaySinhDocGia) {
        this.ngaySinhDocGia = ngaySinhDocGia;
    }

    public String getSdtDocGia() {
        return sdtDocGia;
    }

    public void setSdtDocGia(String sdtDocGia) {
        this.sdtDocGia = sdtDocGia;
    }

    public boolean isHoatDong() {
        return hoatDong;
    }

    public void setHoatDong(boolean hoatDong) {
        this.hoatDong = hoatDong;
    }
}
