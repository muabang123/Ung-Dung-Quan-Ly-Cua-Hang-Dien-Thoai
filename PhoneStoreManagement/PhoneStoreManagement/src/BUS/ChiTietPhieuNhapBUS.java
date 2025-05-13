/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapBUS {
    private ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;

    public ChiTietPhieuNhapBUS() {
        chiTietPhieuNhapDAO = ChiTietPhieuNhapDAO.getInstance();
    }

    // Thêm danh sách chi tiết phiếu nhập
    public boolean themChiTietPhieuNhap(ArrayList<ChiTietPhieuNhapDTO> danhSachCTPN) {
        int result = chiTietPhieuNhapDAO.insert(danhSachCTPN);
        return result > 0;
    }

    // Xoá chi tiết phiếu nhập theo mã phiếu nhập
    public boolean xoaChiTietPhieuNhap(String maPhieuNhap) {
        int result = chiTietPhieuNhapDAO.delete(maPhieuNhap);
        return result > 0;
    }

    // Cập nhật chi tiết phiếu nhập: xoá cũ và thêm mới
    public boolean capNhatChiTietPhieuNhap(ArrayList<ChiTietPhieuNhapDTO> danhSachMoi, String maPhieuNhapCu) {
        int result = chiTietPhieuNhapDAO.update(danhSachMoi, maPhieuNhapCu);
        return result > 0;
    }

    // Lấy danh sách chi tiết phiếu nhập theo mã phiếu nhập
    public ArrayList<ChiTietPhieuNhapDTO> layDanhSachChiTietTheoMa(String maPhieuNhap) {
        return chiTietPhieuNhapDAO.selectAll(maPhieuNhap);
    }
}
