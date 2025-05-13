/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author minhminh
 */
public class HoaDonDTO {
    private String maHoaDon;
    private Timestamp ngayLapHoaDon;
    private String maNhanVien;
    private String maKhachHang;
    private double tongTien;
    private double tongTienPhaiTra;

    // Constructor with all parameters
    public HoaDonDTO(String maHoaDon, Timestamp ngayLapHoaDon, String maNhanVien, String maKhachHang, double tongTien, double tongTienPhaiTra) {
        this.maHoaDon = maHoaDon;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.tongTien = tongTien;
        this.tongTienPhaiTra = tongTienPhaiTra;
    }

    // Getters and Setters
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Timestamp getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    public void setNgayLapHoaDon(Timestamp ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public double getTongTienPhaiTra() {
        return tongTienPhaiTra;
    }

    public void setTongTienPhaiTra(long tongTienPhaiTra) {
        this.tongTienPhaiTra = tongTienPhaiTra;
    }

    // Override hashCode, equals and toString methods

    @Override
    public String toString() {
        return "HoaDonDTO{" +
                "maHoaDon=" + maHoaDon +
                ", ngayLapHoaDon=" + ngayLapHoaDon +
                ", maNhanVien=" + maNhanVien +
                ", maKhachHang=" + maKhachHang +
                ", tongTien=" + tongTien +
                ", tongTienPhaiTra=" + tongTienPhaiTra +
                '}';
    }
}
