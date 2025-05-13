package DTO;

import java.sql.Timestamp;

/**
 * DTO cho bảng Phiếu Nhập
 */
public class PhieuNhapDTO {
    private String MaPhieuNhap;
    private String MaNhanVien;
    private String MaNhaCungCap;
    private Timestamp NgayNhap;
    private double TongTien;

    public PhieuNhapDTO() {
    }

    public PhieuNhapDTO(String MaPhieuNhap, String MaNhanVien, String MaNhaCungCap, Timestamp NgayNhap, double TongTien) {
        this.MaPhieuNhap = MaPhieuNhap;
        this.MaNhanVien = MaNhanVien;
        this.MaNhaCungCap = MaNhaCungCap;
        this.NgayNhap = NgayNhap;
        this.TongTien = TongTien;
    }

    public String getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.MaPhieuNhap = maPhieuNhap;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.MaNhanVien = maNhanVien;
    }

    public String getMaNhaCungCap() {
        return MaNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.MaNhaCungCap = maNhaCungCap;
    }

    public Timestamp getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Timestamp ngayNhap) {
        this.NgayNhap = ngayNhap;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double tongTien) {
        this.TongTien = tongTien;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" +
                "maPhieuNhap=" + MaPhieuNhap +
                ", maNhanVien=" + MaNhanVien +
                ", maNhaCungCap=" + MaNhaCungCap +
                ", ngayNhap=" + NgayNhap +
                ", tongTien=" + TongTien +
                '}';
    }
}
