package BUS;


import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
import java.util.ArrayList;

public class NhaCungCapBUS {
    private ArrayList<NhaCungCapDTO> danhSachNCC;

    public NhaCungCapBUS() {
        danhSachNCC = NhaCungCapDAO.getInstance().selectAll();
    }

    public ArrayList<NhaCungCapDTO> getDanhSachNCC() {
        return danhSachNCC;
    }

    public NhaCungCapDTO getNhaCungCapById(int maNCC) {
        for (NhaCungCapDTO ncc : danhSachNCC) {
            if (ncc.getMaNCC() == maNCC) {
                return ncc;
            }
        }
        return null;
    }
    public ArrayList<NhaCungCapDTO> timKiemNCC(String keyword) {
        ArrayList<NhaCungCapDTO> ketQua = new ArrayList<>();
        keyword = keyword.toLowerCase();

        for (NhaCungCapDTO ncc : danhSachNCC) {
            if (String.valueOf(ncc.getMaNCC()).contains(keyword)
                || ncc.getTenNCC().toLowerCase().contains(keyword)
                || ncc.getDiaChi().toLowerCase().contains(keyword)
                || ncc.getSDT().toLowerCase().contains(keyword)
                || ncc.getEmail().toLowerCase().contains(keyword)) {
                ketQua.add(ncc);
            }
        }

        return ketQua;
    }

    public boolean themNhaCungCap(NhaCungCapDTO ncc) {
        if (getNhaCungCapById(ncc.getMaNCC()) != null) {
            return false; // Trùng mã
        }
        if (NhaCungCapDAO.getInstance().insert(ncc) > 0) {
            danhSachNCC.add(ncc);
            return true;
        }
        return false;
    }

    public boolean suaNhaCungCap(NhaCungCapDTO ncc) {
        if (NhaCungCapDAO.getInstance().update(ncc) > 0) {
            for (int i = 0; i < danhSachNCC.size(); i++) {
                if (danhSachNCC.get(i).getMaNCC() == ncc.getMaNCC()) {
                    danhSachNCC.set(i, ncc);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean xoaNhaCungCap(int maNCC) {
        if (NhaCungCapDAO.getInstance().delete(String.valueOf(maNCC)) > 0) {
            danhSachNCC.removeIf(ncc -> ncc.getMaNCC() == maNCC);
            return true;
        }
        return false;
    }

    public int getNextID() {
        return NhaCungCapDAO.getInstance().getAutoIncrement();
    }

    public void refreshData() {
        danhSachNCC = NhaCungCapDAO.getInstance().selectAll();
    }

    public ArrayList<NhaCungCapDTO> timKiemTheoTen(String keyword) {
        ArrayList<NhaCungCapDTO> ketQua = new ArrayList<>();
        for (NhaCungCapDTO ncc : danhSachNCC) {
            if (ncc.getTenNCC().toLowerCase().contains(keyword.toLowerCase())) {
                ketQua.add(ncc);
            }
        }
        return ketQua;
    }
}
