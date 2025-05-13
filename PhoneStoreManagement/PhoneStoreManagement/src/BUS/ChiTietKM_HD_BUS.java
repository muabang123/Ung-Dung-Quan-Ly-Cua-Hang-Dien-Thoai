/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietKM_HD_DAO;
import DTO.ChiTietCTKM_HD_DTO;
import DTO.ChuongTrinhKMDTO;
import java.time.LocalDate;

import java.util.ArrayList;

public class ChiTietKM_HD_BUS {
    private final ChiTietKM_HD_DAO dao = new ChiTietKM_HD_DAO();
    private ArrayList<ChiTietCTKM_HD_DTO> danhSach;

    public ChiTietKM_HD_BUS() {
        danhSach = dao.layDanhSachChiTietKM_HD();
    }

    public ArrayList<ChiTietCTKM_HD_DTO> getDanhSach() {
        return danhSach;
    }

    public boolean themChiTietKM_HD(ChiTietCTKM_HD_DTO dto) {
        int result = dao.themChiTietKM_HD(dto);
        if (result > 0) {
            danhSach.add(dto);
            return true;
        }
        return false;
    }

    public boolean xoaChiTietKM_HD(int maChuongTrinhKM) {
        int result = dao.xoaChiTietKM_HD(maChuongTrinhKM);
        if (result > 0) {
            danhSach.removeIf(dto -> dto.getMaChuongTrinhKM() == maChuongTrinhKM);
            return true;
        }
        return false;
    }

    public boolean suaChiTietKM_HD(ChiTietCTKM_HD_DTO dto) {
        int result = dao.suaChiTietKM_HD(dto);
        if (result > 0) {
            for (int i = 0; i < danhSach.size(); i++) {
                if (danhSach.get(i).getMaChuongTrinhKM() == dto.getMaChuongTrinhKM()) {
                    danhSach.set(i, dto);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public ChiTietCTKM_HD_DTO timTheoMaCTKM(int maCTKM) {
        for (ChiTietCTKM_HD_DTO dto : danhSach) {
            if (dto.getMaChuongTrinhKM() == maCTKM) {
                return dto;
            }
        }
        return null;
    }
    public double kiemTraVaLayPhanTramGiamGia(double tongTienHoaDon) {
    double maxGiamGia = 0;
    LocalDate today = LocalDate.now();
    ChuongTrinhKMBUS ctkmBUS = new ChuongTrinhKMBUS();
    ArrayList<ChuongTrinhKMDTO> danhSachCTKM = ctkmBUS.getDanhSachChuongTrinhKM();

    for (ChiTietCTKM_HD_DTO dto : danhSach) {
        ChuongTrinhKMDTO ct = null;

        // Lấy thông tin chương trình khuyến mãi tương ứng
        for (ChuongTrinhKMDTO km : danhSachCTKM) {
            if (km.getMaChuongTrinhKM() == dto.getMaChuongTrinhKM()) {
                ct = km;
                break;
            }
        }

        if (ct != null) {
            LocalDate ngayBatDau = ct.getNgayBatDau().toLocalDate();
            LocalDate ngayKetThuc = ct.getNgayKetThuc().toLocalDate();

            // Nếu hóa đơn nằm trong thời gian khuyến mãi và đạt điều kiện về số tiền
            if ((today.isEqual(ngayBatDau) || today.isAfter(ngayBatDau)) &&
                (today.isEqual(ngayKetThuc) || today.isBefore(ngayKetThuc))) {
                if (tongTienHoaDon >= dto.getSoTienHoaDon() && dto.getPhanTramGiamGia() > maxGiamGia) {
                    maxGiamGia = dto.getPhanTramGiamGia();
                }
            }
        }
    }
    return maxGiamGia;
}
}
