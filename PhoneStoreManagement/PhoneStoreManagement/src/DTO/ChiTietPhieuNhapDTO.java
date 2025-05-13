/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author minhminh
 */
public class ChiTietPhieuNhapDTO {
    private String maPhieuNhap;
    private int maSanPham;
    private int soLuong;
    private String ram;
    private String rom;
    private String mauSac;
    private double donGia;

    // Constructor mặc định
    public ChiTietPhieuNhapDTO() {
    }

    // Constructor với tham số
    public ChiTietPhieuNhapDTO(String maPhieuNhap, int maSanPham, int soLuong, String ram, String rom, String mauSac, double donGia) {
        this.maPhieuNhap = maPhieuNhap;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.ram = ram;
        this.rom = rom;
        this.mauSac = mauSac;
        this.donGia = donGia;
    }

    // Getter và Setter cho các thuộc tính
    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
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

    @Override
    public String toString() {
        return "ChiTietPhieuNhapDTO{" +
                "maPhieuNhap=" + maPhieuNhap +
                ", maSanPham=" + maSanPham +
                ", soLuong=" + soLuong +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", mauSac='" + mauSac + '\'' +
                ", donGia=" + donGia +
                '}';
    }
}
