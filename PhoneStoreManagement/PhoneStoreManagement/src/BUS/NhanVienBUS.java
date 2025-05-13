/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;

public class NhanVienBUS {

    private final NhanVienDAO nhanVienDAO;
    private ArrayList<NhanVienDTO> danhSachNhanVien;

    public NhanVienBUS() {
        nhanVienDAO = new NhanVienDAO();
        danhSachNhanVien = NhanVienDAO.getInstance().layDanhSachNhanVien();
    }

    public ArrayList<NhanVienDTO> layDanhSachNhanVien() {
        return nhanVienDAO.layDanhSachNhanVien();
    }
    public NhanVienDTO getNhanVienById(int maNV) {
        for (NhanVienDTO nv : danhSachNhanVien) {
            if (nv.getMaNhanVien() == maNV) {
                return nv;
            }
        }
        return null;
    }
    public ArrayList<NhanVienDTO> timKiemNhanVien(String tuKhoa) {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        tuKhoa = tuKhoa.toLowerCase();

        for (NhanVienDTO nv : danhSachNhanVien) {
            if (String.valueOf(nv.getMaNhanVien()).contains(tuKhoa) ||
                nv.getHoTen().toLowerCase().contains(tuKhoa)
                ) {
                ketQua.add(nv);
            }
        }

        return ketQua;
    }


    public boolean themNhanVien(NhanVienDTO nv) {
        int result = nhanVienDAO.themNhanVien(nv);
        return result > 0;
    }

    public boolean suaNhanVien(NhanVienDTO nv) {
        int result = nhanVienDAO.suaNhanVien(nv);
        return result > 0;
    }

    public boolean xoaNhanVien(int maNhanVien) {
        if (NhanVienDAO.getInstance().xoaNhanVien(String.valueOf(maNhanVien)) > 0) {
            danhSachNhanVien.removeIf(kh -> kh.getMaNhanVien() == maNhanVien);
            return true;
        }
        return false;
    }

    public int layMaTuDong() {
        return nhanVienDAO.layMaTuDongTang();
    }
}


