package DTO;

import java.math.BigDecimal;

public class SanPhamDTO {

    private int MaSanPham;
    private String TenSanPham;
    private int SoLuong;
    private BigDecimal DonGia;  // Chuyển từ double sang BigDecimal
    private String DonViTinh;
    private String HinhAnh;
    private int MaLoai;

    // Constructor mặc định
    public SanPhamDTO() {
    }

    // Constructor có tham số
    public SanPhamDTO(int MaSanPham, String TenSanPham, int SoLuong, BigDecimal DonGia, String DonViTinh, String HinhAnh, int MaLoai) {
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.DonViTinh = DonViTinh;
        this.HinhAnh = HinhAnh;
        this.MaLoai = MaLoai;
    }

    // Getter và Setter
    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getDonGia() {
        return DonGia;
    }

    public void setDonGia(BigDecimal DonGia) {
        this.DonGia = DonGia;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" +
                "MaSanPham=" + MaSanPham +
                ", TenSanPham='" + TenSanPham + '\'' +
                ", SoLuong=" + SoLuong +
                ", DonGia=" + DonGia +
                ", DonViTinh='" + DonViTinh + '\'' +
                ", HinhAnh='" + HinhAnh + '\'' +
                ", MaLoai=" + MaLoai +
                '}';
    }
}
    