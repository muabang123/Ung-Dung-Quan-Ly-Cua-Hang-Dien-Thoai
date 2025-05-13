/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author MSI Gaming
 */
public class HangDTO {

    private int maHang;
    private String tenHang;
    private String quocGia;

    // Constructor mặc định
    public HangDTO() {
    }

    // Constructor có tham số
    public HangDTO(int maHang, String tenHang, String quocGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.quocGia = quocGia;
    }

    // Getter và Setter
    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    // toString() để hiển thị thông tin đối tượng
    @Override
    public String toString() {
        return "HangDTO{" +
                "maHang=" + maHang +
                ", tenHang='" + tenHang + '\'' +
                ", quocGia='" + quocGia + '\'' +
                '}';
    }
}
