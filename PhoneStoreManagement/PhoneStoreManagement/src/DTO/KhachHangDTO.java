package DTO;


public class KhachHangDTO {
    private int maKhachHang;
    private String hoTenKhachHang;
    private String diaChi;
    private String soDienThoai;
    private String email;

    public KhachHangDTO(int maKhachHang, String hoTenKhachHang, String diaChi, String soDienThoai, String email) {
        this.maKhachHang = maKhachHang;
        this.hoTenKhachHang = hoTenKhachHang;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    // Getters và Setters
    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getHoTenKhachHang() {
        return hoTenKhachHang;
    }

    public void setHoTenKhachHang(String hoTenKhachHang) {
        this.hoTenKhachHang = hoTenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return maKhachHang + " - " + hoTenKhachHang;
    }

}
