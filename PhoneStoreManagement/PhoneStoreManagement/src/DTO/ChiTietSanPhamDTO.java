package DTO;

public class ChiTietSanPhamDTO {

    private int MaSanPham;
    private int MaHang;
    private String Chip;
    private String Ram;
    private String Rom;
    private String Inch;
    private String DungLuongPin;
    private String MauSac;

    // Constructor mặc định
    public ChiTietSanPhamDTO() {
    }

    // Constructor có tham số
    public ChiTietSanPhamDTO(int MaSanPham, int MaHang, String Chip, String Ram, String Rom, String Inch, String DungLuongPin, String MauSac) {
        this.MaSanPham = MaSanPham;
        this.MaHang = MaHang;
        this.Chip = Chip;
        this.Ram = Ram;
        this.Rom = Rom;
        this.Inch = Inch;
        this.DungLuongPin = DungLuongPin;
        this.MauSac = MauSac;
    }

    // Getter và Setter
    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public int getMaHang() {
        return MaHang;
    }

    public void setMaHang(int MaHang) {
        this.MaHang = MaHang;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String Chip) {
        this.Chip = Chip;
    }

    public String getRam() {
        return Ram;
    }

    public void setRam(String Ram) {
        this.Ram = Ram;
    }

    public String getRom() {
        return Rom;
    }

    public void setRom(String Rom) {
        this.Rom = Rom;
    }

    public String getInch() {
        return Inch;
    }

    public void setInch(String Inch) {
        this.Inch = Inch;
    }

    public String getDungLuongPin() {
        return DungLuongPin;
    }

    public void setDungLuongPin(String DungLuongPin) {
        this.DungLuongPin = DungLuongPin;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    @Override
    public String toString() {
        return "ChiTietSanPhamDTO{" +
                "MaSanPham=" + MaSanPham +
                ", MaHang=" + MaHang +
                ", Chip='" + Chip + '\'' +
                ", Ram='" + Ram + '\'' +
                ", Rom='" + Rom + '\'' +
                ", Inch='" + Inch + '\'' +
                ", DungLuongPin='" + DungLuongPin + '\'' +
                ", MauSac='" + MauSac + '\'' +
                '}';
    }
}
