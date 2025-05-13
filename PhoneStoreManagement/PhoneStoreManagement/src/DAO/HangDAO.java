/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.HangDTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;

public class HangDAO {

    // Lấy mã hãng tự động tăng dần
    public int getNextMaHang() {
        int nextMaHang = 1;  // Mặc định là 1 nếu không có dữ liệu trong CSDL
        try {
            // Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT MAX(MaHang) AS MaxMaHang FROM hang";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nextMaHang = rs.getInt("MaxMaHang") + 1;  // Lấy giá trị lớn nhất và cộng thêm 1
            }
            JDBCUtil.closeConnection(con);  // Đóng kết nối CSDL
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return nextMaHang;  // Trả về mã hãng tiếp theo
    }

    // Thêm một hãng vào CSDL
    public boolean add(HangDTO hang) {
        try {
            // Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO hang (TenHang, QuocGia) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, hang.getTenHang());
            pst.setString(2, hang.getQuocGia());
            int rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);  // Đóng kết nối
            return rowsAffected > 0;  // Nếu có thay đổi thì trả về true
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;  // Nếu không thành công
    }

    // Cập nhật thông tin hãng
    public boolean update(HangDTO hang) {
        try {
            // Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE hang SET TenHang = ?, QuocGia = ? WHERE MaHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, hang.getTenHang());
            pst.setString(2, hang.getQuocGia());
            pst.setInt(3, hang.getMaHang());
            int rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);  // Đóng kết nối
            return rowsAffected > 0;  // Nếu có thay đổi thì trả về true
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;  // Nếu không thành công
    }

    // Xóa hãng theo mã hãng
    public boolean delete(int maHang) {
        try {
            // Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM hang WHERE MaHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maHang);  // Truyền vào mã hãng để xóa
            int rowsAffected = pst.executeUpdate();
            JDBCUtil.closeConnection(con);  // Đóng kết nối
            return rowsAffected > 0;  // Nếu có thay đổi thì trả về true
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;  // Nếu không thành công
    }

    // Lấy tất cả các hãng từ CSDL
    public ArrayList<HangDTO> getAll() {
        ArrayList<HangDTO> list = new ArrayList<>();
        try {
            // Kết nối với CSDL
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM hang";  // Lấy tất cả hãng
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maHang = rs.getInt("MaHang");
                String tenHang = rs.getString("TenHang");
                String quocGia = rs.getString("QuocGia");
                HangDTO hang = new HangDTO(maHang, tenHang, quocGia);
                list.add(hang);  // Thêm đối tượng HangDTO vào danh sách
            }
            JDBCUtil.closeConnection(con);  // Đóng kết nối
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;  // Trả về danh sách các hãng
    }

    
}
