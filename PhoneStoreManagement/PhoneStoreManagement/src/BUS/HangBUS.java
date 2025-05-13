/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HangDAO;
import DTO.HangDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class HangBUS {

    private HangDAO hangDAO;

    public HangBUS() {
        hangDAO = new HangDAO();
    }

 // Lấy mã hãng tự động tăng dần
    public int getNextMaHang() {
        return hangDAO.getNextMaHang();
    }
    
   // Thêm một hãng
  public boolean add(HangDTO hang) {
    // Nếu bạn dùng AUTO_INCREMENT, không cần kiểm tra mã hãng nữa
    // CSDL sẽ tự động sinh ra mã hãng mới, nên bạn có thể bỏ qua phần kiểm tra mã hãng
    return hangDAO.add(hang);
}


    // Lấy tất cả hãng
  public ArrayList<HangDTO> getAll() {
        return hangDAO.getAll();
    }

    // Cập nhật thông tin hãng
public boolean update(HangDTO hang) {
    // Chỉ cần gọi update trực tiếp mà không cần kiểm tra mã hãng
    boolean result = hangDAO.update(hang);

    if (result) {
        JOptionPane.showMessageDialog(null, "Cập nhật hãng thành công!");
    } else {
        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật hãng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    return result;
}

    // Xóa hãng
    public boolean delete(int maHang) {
        return hangDAO.delete(maHang);
    }
}
