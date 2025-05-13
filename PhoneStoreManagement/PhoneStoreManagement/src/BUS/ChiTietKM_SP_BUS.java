/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietKM_SP_DAO;
import DTO.ChiTietCTKM_SP_DTO;
import DTO.ChuongTrinhKMDTO;
import java.time.LocalDate;

import java.util.ArrayList;

public class ChiTietKM_SP_BUS {
    private final ChiTietKM_SP_DAO dao = new ChiTietKM_SP_DAO();
    private ArrayList<ChiTietCTKM_SP_DTO> danhSach;

    public ChiTietKM_SP_BUS() {
        danhSach = dao.layDanhSachChiTietKM_SP();
    }

    public ArrayList<ChiTietCTKM_SP_DTO> getDanhSach() {
        return danhSach;
    }

    public boolean themChiTietKM_SP(ChiTietCTKM_SP_DTO dto) {
        int result = dao.themChiTietKM_SP(dto);
        if (result > 0) {
            danhSach.add(dto);
            return true;
        }
        return false;
    }

    public boolean xoaChiTietKM_SP(int maCTKM, int maSP) {
        int result = dao.xoaChiTietKM_SP(maCTKM, maSP);
        if (result > 0) {
            danhSach.removeIf(dto -> dto.getMaChuongTrinhKM() == maCTKM && dto.getMaSanPham() == maSP);
            return true;
        }
        return false;
    }

    public boolean suaChiTietKM_SP(ChiTietCTKM_SP_DTO dto) {
        int result = dao.suaChiTietKM_SP(dto);
        if (result > 0) {
            for (int i = 0; i < danhSach.size(); i++) {
                ChiTietCTKM_SP_DTO current = danhSach.get(i);
                if (current.getMaChuongTrinhKM() == dto.getMaChuongTrinhKM() &&
                    current.getMaSanPham() == dto.getMaSanPham()) {
                    danhSach.set(i, dto);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public ChiTietCTKM_SP_DTO timTheoMaCTKMVaMaSP(int maCTKM, int maSP) {
        for (ChiTietCTKM_SP_DTO dto : danhSach) {
            if (dto.getMaChuongTrinhKM() == maCTKM && dto.getMaSanPham() == maSP) {
                return dto;
            }
        }
        return null;
    }
    public double layPhanTramGiamGiaTheoMaSP(int maSP) {
    LocalDate today = LocalDate.now();
    ChuongTrinhKMBUS ctkmBUS = new ChuongTrinhKMBUS();
    ArrayList<ChuongTrinhKMDTO> danhSachCTKM = ctkmBUS.getDanhSachChuongTrinhKM();

    for (ChiTietCTKM_SP_DTO dto : danhSach) {
        if (dto.getMaSanPham() == maSP) {
            ChuongTrinhKMDTO ct = null;
             for (ChuongTrinhKMDTO km : danhSachCTKM) {
                if (km.getMaChuongTrinhKM() == dto.getMaChuongTrinhKM()) {
                    ct = km;
                    break;
                }
            }

            if (ct != null) {
                LocalDate ngayBatDau = ct.getNgayBatDau().toLocalDate();
                LocalDate ngayKetThuc = ct.getNgayKetThuc().toLocalDate();

                if ((today.isEqual(ngayBatDau) || today.isAfter(ngayBatDau)) &&
                    (today.isEqual(ngayKetThuc) || today.isBefore(ngayKetThuc))) {
                    return dto.getPhanTramGiamGia();
                }
            }
        }
    }
    return 0; // Không có khuyến mãi hợp lệ
}
}

