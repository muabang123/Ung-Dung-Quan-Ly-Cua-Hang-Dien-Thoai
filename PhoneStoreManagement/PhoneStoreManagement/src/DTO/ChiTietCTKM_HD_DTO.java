/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class ChiTietCTKM_HD_DTO {
    private int maChuongTrinhKM;
    private double soTienHoaDon;
    private double phanTramGiamGia;

    public ChiTietCTKM_HD_DTO() {}

    public ChiTietCTKM_HD_DTO(int maChuongTrinhKM, double soTienHoaDon, double phanTramGiamGia) {
        this.maChuongTrinhKM = maChuongTrinhKM;
        this.soTienHoaDon = soTienHoaDon;
        this.phanTramGiamGia = phanTramGiamGia;
    }

    public int getMaChuongTrinhKM() {
        return maChuongTrinhKM;
    }

    public void setMaChuongTrinhKM(int maChuongTrinhKM) {
        this.maChuongTrinhKM = maChuongTrinhKM;
    }

    public double getSoTienHoaDon() {
        return soTienHoaDon;
    }

    public void setSoTienHoaDon(double soTienHoaDon) {
        this.soTienHoaDon = soTienHoaDon;
    }

    public double getPhanTramGiamGia() {
        return phanTramGiamGia;
    }

    public void setPhanTramGiamGia(double phanTramGiamGia) {
        this.phanTramGiamGia = phanTramGiamGia;
    }
}

