/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietHoaDonDAO;
import DTO.ChiTietHoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonBUS {
    private ChiTietHoaDonDAO chiTietHoaDonDAO;

    public ChiTietHoaDonBUS() {
        chiTietHoaDonDAO = ChiTietHoaDonDAO.getInstance();
    }

    // Thêm danh sách chi tiết hóa đơn
    public boolean themChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> danhSachCTHD) {
        int result = chiTietHoaDonDAO.insert(danhSachCTHD);
        return result > 0;
    }

    // Xoá chi tiết hóa đơn theo mã hóa đơn
    public boolean xoaChiTietHoaDon(String maHoaDon) {
        int result = chiTietHoaDonDAO.delete(maHoaDon);
        return result > 0;
    }

    // Cập nhật chi tiết hóa đơn: xoá cũ và thêm mới
    public boolean capNhatChiTietHoaDon(ArrayList<ChiTietHoaDonDTO> danhSachMoi, String maHoaDonCu) {
        int result = chiTietHoaDonDAO.update(danhSachMoi, maHoaDonCu);
        return result > 0;
    }

    // Lấy danh sách chi tiết hóa đơn theo mã hóa đơn
    public ArrayList<ChiTietHoaDonDTO> layDanhSachChiTietTheoMa(String maHoaDon) {
        return chiTietHoaDonDAO.selectAll(maHoaDon);
    }
}
