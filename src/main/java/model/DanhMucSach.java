/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nguye
 */
public class DanhMucSach {
    private final String maDauSach;
    private final String maDanhMucSach;
    private int trangThaiSach;

    public DanhMucSach(String maDauSach, String maDanhMucSach, int trangThaiSach) {
        this.maDauSach = maDauSach;
        this.maDanhMucSach = maDanhMucSach;
        this.trangThaiSach = trangThaiSach;
    }

    public String getMaDauSach() {
        return maDauSach;
    }

    public String getMaDanhMucSach() {
        return maDanhMucSach;
    }


    public int getTrangThaiSach() {
        return trangThaiSach;
    }

    public void setTrangThaiSach(int trangThaiSach) {
        this.trangThaiSach = trangThaiSach;
    }
}
