/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MSI Gaming
 */
import DAO.LoaiSanPhamDAO;
import DTO.LoaiSanPhamDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoaiSanPhamBUS {

    private LoaiSanPhamDAO loaiSanPhamDAO;

    // Constructor
    public LoaiSanPhamBUS() {
        loaiSanPhamDAO = new LoaiSanPhamDAO();  // Khởi tạo DAO
    }

    // Lấy mã loại tiếp theo (tự động tăng dần)
    public int getNextMaLoai() {
        return loaiSanPhamDAO.getNextMaLoai();  // Lấy mã loại tiếp theo từ DAO
    }

    // Thêm loại sản phẩm vào CSDL
    public boolean add(LoaiSanPhamDTO loaiSanPham) {
        // Kiểm tra nếu tên loại sản phẩm đã tồn tại
        if (isTenLoaiExist(loaiSanPham.getTenLoai())) {
            JOptionPane.showMessageDialog(null, "Tên loại sản phẩm đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;  // Không cho phép thêm nếu tên loại đã tồn tại
        }
        // Thực hiện thêm loại sản phẩm vào CSDL
        return loaiSanPhamDAO.add(loaiSanPham);
    }

    // Kiểm tra nếu tên loại sản phẩm đã tồn tại
    public boolean isTenLoaiExist(String tenLoai) {
        return loaiSanPhamDAO.isTenLoaiExist(tenLoai);  // Gọi phương thức kiểm tra tên loại trong DAO
    }
    
    public boolean isMaLoaiExist(int maLoai) {
        return loaiSanPhamDAO.selectById(maLoai) != null; // Kiểm tra mã loại có tồn tại trong CSDL
    }

    // Cập nhật loại sản phẩm
    public boolean update(LoaiSanPhamDTO loaiSanPham) {
        return loaiSanPhamDAO.update(loaiSanPham);  // Gọi phương thức update trong DAO để cập nhật loại sản phẩm
    }

    // Xóa loại sản phẩm theo mã loại
    public boolean delete(int maLoai) {
        return loaiSanPhamDAO.delete(maLoai);  // Gọi phương thức delete trong DAO để xóa loại sản phẩm
    }

    // Lấy tất cả loại sản phẩm từ CSDL
    public ArrayList<LoaiSanPhamDTO> getAll() {
        return loaiSanPhamDAO.getAll();  // Gọi phương thức getAll trong DAO để lấy tất cả loại sản phẩm
    }
}
