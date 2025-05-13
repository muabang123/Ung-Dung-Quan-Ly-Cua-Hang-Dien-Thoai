/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author MSI Gaming
 */
import DTO.LoaiSanPhamDTO;
import config.JDBCUtil;
import java.sql.*;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoaiSanPhamDAO {

    // Lấy mã loại tiếp theo (tự động tăng dần)
    public int getNextMaLoai() {
        int nextMaLoai = 1;  // Mặc định là 1 nếu không có dữ liệu trong CSDL
        try {
            // Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT MAX(MaLoai) AS MaxMaLoai FROM loaisanpham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nextMaLoai = rs.getInt("MaxMaLoai") + 1;  // Lấy giá trị lớn nhất và cộng thêm 1
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nextMaLoai;
    }

    // Thêm loại sản phẩm vào CSDL
    public boolean add(LoaiSanPhamDTO loaiSanPham) {
        try {
            // Kết nối với CSDL và thực hiện câu lệnh INSERT
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO loaisanpham (TenLoai) VALUES (?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, loaiSanPham.getTenLoai());  // Chỉ cần truyền tên loại sản phẩm
            int rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rowsAffected > 0;  // Trả về true nếu thêm thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Kiểm tra nếu tên loại sản phẩm đã tồn tại
    public boolean isTenLoaiExist(String tenLoai) {
        boolean exists = false;
        try {
            // Kết nối với CSDL và kiểm tra nếu tên loại đã tồn tại
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisanpham WHERE TenLoai = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, tenLoai);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                exists = true;  // Nếu tìm thấy tên loại sản phẩm trong CSDL
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }

    // Cập nhật loại sản phẩm trong CSDL
    public boolean update(LoaiSanPhamDTO loaiSanPham) {
        try {
            // Kết nối với CSDL và thực hiện câu lệnh UPDATE
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE loaisanpham SET TenLoai = ? WHERE MaLoai = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, loaiSanPham.getTenLoai());
            pst.setInt(2, loaiSanPham.getMaLoai());  // Dựa trên MaLoai hiện tại
            int rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rowsAffected > 0;  // Trả về true nếu cập nhật thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Xóa loại sản phẩm theo mã loại
    public boolean delete(int maLoai) {
        try {
            // Kết nối với CSDL và thực hiện câu lệnh DELETE
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM loaisanpham WHERE MaLoai = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maLoai);  // Xóa loại sản phẩm theo MaLoai
            int rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
            return rowsAffected > 0;  // Trả về true nếu xóa thành công
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả loại sản phẩm từ CSDL
    public ArrayList<LoaiSanPhamDTO> getAll() {
        ArrayList<LoaiSanPhamDTO> list = new ArrayList<>();
        try {
            // Kết nối với CSDL và thực hiện câu lệnh SELECT để lấy tất cả loại sản phẩm
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisanpham";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maLoai = rs.getInt("MaLoai");
                String tenLoai = rs.getString("TenLoai");
                LoaiSanPhamDTO loaiSanPham = new LoaiSanPhamDTO(maLoai, tenLoai);
                list.add(loaiSanPham);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    // Kiểm tra mã loại có tồn tại trong cơ sở dữ liệu hay không
    public boolean isMaLoaiExist(int maLoai) {
        boolean exists = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM loaisanpham WHERE MaLoai = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maLoai);
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true; // Mã loại tồn tại
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            
        }
        return exists;
    }

    
    public LoaiSanPhamDTO selectById(int maLoai) {
    LoaiSanPhamDTO loaiSanPham = null;
    try {
        Connection con = JDBCUtil.getConnection();
        String sql = "SELECT * FROM loaisanpham WHERE MaLoai=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, maLoai);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            loaiSanPham = new LoaiSanPhamDTO(
                rs.getInt("MaLoai"),
                rs.getString("TenLoai")
            );
        }
        JDBCUtil.closeConnection(con);
    } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Lỗi khi truy vấn loại sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    return loaiSanPham; // Trả về loại sản phẩm nếu tồn tại, hoặc null nếu không tìm thấy
}

  

    // Lấy loại sản phẩm theo mã loại
    public LoaiSanPhamDTO getById(int maLoai) {
        LoaiSanPhamDTO loaiSanPham = null;
        try {
            // Kết nối với CSDL và thực hiện câu lệnh SELECT để lấy loại sản phẩm theo MaLoai
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM loaisanpham WHERE MaLoai = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maLoai);  // Lấy loại sản phẩm theo MaLoai
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                loaiSanPham = new LoaiSanPhamDTO(rs.getInt("MaLoai"), rs.getString("TenLoai"));
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return loaiSanPham;
    }
}
