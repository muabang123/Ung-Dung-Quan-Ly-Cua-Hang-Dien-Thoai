/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class ChiTietCTKM_SP_DTO {
    private int maChuongTrinhKM;
    private int maSanPham;
    private double phanTramGiamGia;

    public ChiTietCTKM_SP_DTO() {}

    public ChiTietCTKM_SP_DTO(int maChuongTrinhKM, int maSanPham, double phanTramGiamGia) {
        this.maChuongTrinhKM = maChuongTrinhKM;
        this.maSanPham = maSanPham;
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public int getMaChuongTrinhKM() {
        return maChuongTrinhKM;
    }

    public void setMaChuongTrinhKM(int maChuongTrinhKM) {
        this.maChuongTrinhKM = maChuongTrinhKM;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(double phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }
}

