/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author minhminh
 */
public class ChiTietHoaDonDTO {
    private String maHoaDon;     // Mã hóa đơn
    private int maSanPham;       // Mã sản phẩm
    private int soLuong;         // Số lượng sản phẩm
    private String ram;          // RAM của sản phẩm
    private String rom;          // ROM của sản phẩm
    private String mauSac;       // Màu sắc của sản phẩm
    private double donGia;       // Đơn giá của sản phẩm
    private double thanhTien;    // Thành tiền (số tiền = soLuong * donGia)
    private double soTienConLai; // Số tiền còn lại

    // Constructor mặc định
    public ChiTietHoaDonDTO() {
    }

    // Constructor với tham số
    public ChiTietHoaDonDTO(String maHoaDon, int maSanPham, int soLuong, String ram, String rom, String mauSac, double donGia, double thanhTien, double soTienConLai) {
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.ram = ram;
        this.rom = rom;
        this.mauSac = mauSac;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.soTienConLai = soTienConLai;
    }

    // Getter và Setter cho các thuộc tính
    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRom() {
        return rom;
    }

    public void setRom(String rom) {
        this.rom = rom;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getSoTienConLai() {
        return soTienConLai;
    }

    public void setSoTienConLai(double soTienConLai) {
        this.soTienConLai = soTienConLai;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDonDTO{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", maSanPham=" + maSanPham +
                ", soLuong=" + soLuong +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", mauSac='" + mauSac + '\'' +
                ", donGia=" + donGia +
                ", thanhTien=" + thanhTien +
                ", soTienConLai=" + soTienConLai +
                '}';
    }
}
