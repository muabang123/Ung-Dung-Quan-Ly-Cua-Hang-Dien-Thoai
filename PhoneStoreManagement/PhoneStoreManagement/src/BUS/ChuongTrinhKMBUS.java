/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChuongTrinhKMDAO;
import DTO.ChuongTrinhKMDTO;
import java.util.ArrayList;

public class ChuongTrinhKMBUS {
    private ChuongTrinhKMDAO dao = new ChuongTrinhKMDAO();

    // Lấy danh sách chương trình khuyến mãi
    public ArrayList<ChuongTrinhKMDTO> getDanhSachChuongTrinhKM() {
        return dao.layDanhSachChuongTrinhKM();
    }

    // Thêm chương trình khuyến mãi
    public boolean themChuongTrinhKM(ChuongTrinhKMDTO ct) {
        return dao.themChuongTrinhKM(ct);
    }

    // Cập nhật chương trình khuyến mãi
    public boolean capNhatChuongTrinhKM(ChuongTrinhKMDTO ct) {
        return dao.capNhatChuongTrinhKM(ct);
    }

    // Xoá chương trình khuyến mãi
    public boolean xoaChuongTrinhKM(int maChuongTrinh) {
        return dao.xoaChuongTrinhKM(maChuongTrinh);
    }
    //lấy mã CTKM mới nhất
    public int getMaCTKMMax() {
        return dao.getMaCTKMMax();
    }

}
