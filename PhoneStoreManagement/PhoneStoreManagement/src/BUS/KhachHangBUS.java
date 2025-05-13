package BUS;


import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;

public class KhachHangBUS {
    private ArrayList<KhachHangDTO> danhSachKhachHang;

    public KhachHangBUS() {
        danhSachKhachHang = KhachHangDAO.getInstance().selectAll();
    }

    public ArrayList<KhachHangDTO> getDanhSachKhachHang() {
        return danhSachKhachHang;
    }

    public KhachHangDTO getKhachHangById(int maKH) {
        for (KhachHangDTO kh : danhSachKhachHang) {
            if (kh.getMaKhachHang() == maKH) {
                return kh;
            }
        }
        return null;
    }

    public boolean themKhachHang(KhachHangDTO kh) {
        // Check nếu đã tồn tại
        if (getKhachHangById(kh.getMaKhachHang()) != null) {
            return false; // Trùng mã khách hàng
        }
        if (KhachHangDAO.getInstance().insert(kh) > 0) {
            danhSachKhachHang.add(kh);
            return true;
        }
        return false;
    }

    public boolean suaKhachHang(KhachHangDTO kh) {
        if (KhachHangDAO.getInstance().update(kh) > 0) {
            for (int i = 0; i < danhSachKhachHang.size(); i++) {
                if (danhSachKhachHang.get(i).getMaKhachHang() == kh.getMaKhachHang()) {
                    danhSachKhachHang.set(i, kh);
                    return true;
                }
            }
        }
        return false;
    }
    public ArrayList<KhachHangDTO> timKiemKhachHang(String tuKhoa) {
        ArrayList<KhachHangDTO> ketQua = new ArrayList<>();
        for (KhachHangDTO kh : danhSachKhachHang) {
            if (kh.getHoTenKhachHang().toLowerCase().contains(tuKhoa.toLowerCase()) ||
                kh.getDiaChi().toLowerCase().contains(tuKhoa.toLowerCase()) ||
                kh.getSoDienThoai().toLowerCase().contains(tuKhoa.toLowerCase()) ||
                kh.getEmail().toLowerCase().contains(tuKhoa.toLowerCase())) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }

    public boolean xoaKhachHang(int maKH) {
        if (KhachHangDAO.getInstance().delete(String.valueOf(maKH)) > 0) {
            danhSachKhachHang.removeIf(kh -> kh.getMaKhachHang() == maKH);
            return true;
        }
        return false;
    }

    public int getNextID() {
        return KhachHangDAO.getInstance().getAutoIncrement();
    }

    public void refreshData() {
        danhSachKhachHang = KhachHangDAO.getInstance().selectAll();
    }
}
