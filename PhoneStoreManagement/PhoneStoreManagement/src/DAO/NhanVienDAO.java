/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.NhanVienDTO;
import config.JDBCUtil;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO {

    public static NhanVienDAO getInstance() {
        return new NhanVienDAO();
    }

    public int themNhanVien(NhanVienDTO nv) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO NhanVien (MaNhanVien, HoTen, ChucVu, NgayVaoLam, Luong) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nv.getMaNhanVien());
            pst.setString(2, nv.getHoTen());
            pst.setString(3, nv.getChucVu());
            pst.setDate(4, Date.valueOf(nv.getNgayVaoLam()));
            pst.setDouble(5, nv.getLuong());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int suaNhanVien(NhanVienDTO nv) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE NhanVien SET HoTen=?, ChucVu=?, NgayVaoLam=?, Luong=? WHERE MaNhanVien=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nv.getHoTen());
            pst.setString(2, nv.getChucVu());
            pst.setDate(3, Date.valueOf(nv.getNgayVaoLam()));
            pst.setDouble(4, nv.getLuong());
            pst.setInt(5, nv.getMaNhanVien());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int xoaNhanVien(String id) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM NhanVien WHERE MaNhanVien = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<NhanVienDTO> layDanhSachNhanVien() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhanVien ORDER BY MaNhanVien ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNV = rs.getInt("MaNhanVien");
                String hoTen = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
                double luong = rs.getDouble("Luong");
                NhanVienDTO nv = new NhanVienDTO(maNV, hoTen, chucVu, ngayVaoLam, luong);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public NhanVienDTO timNhanVienTheoMa(String id) {
        NhanVienDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE MaNhanVien=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNV = rs.getInt("MaNhanVien");
                String hoTen = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
                double luong = rs.getDouble("Luong");
                result = new NhanVienDTO(maNV, hoTen, chucVu, ngayVaoLam, luong);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhanVien ORDER BY MaNhanVien ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNV = rs.getInt("MaNhanVien");
                String hoTen = rs.getString("HoTen");
                String chucVu = rs.getString("ChucVu");
                LocalDate ngayVaoLam = rs.getDate("NgayVaoLam").toLocalDate();
                double luong = rs.getDouble("Luong");

                NhanVienDTO nv = new NhanVienDTO(maNV, hoTen, chucVu, ngayVaoLam, luong);
                result.add(nv);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }




    /*public int layMaTuDongTang() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlibanhang' AND TABLE_NAME = 'NhanVien'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }*/
    
    public int layMaTuDongTang() {
        int maMoi = 1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT MaNhanVien FROM NhanVien ORDER BY MaNhanVien ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            // Duyệt danh sách mã để tìm khoảng trống
            while (rs.next()) {
                int maHienTai = rs.getInt("MaNhanVien");
                if (maHienTai != maMoi) {
                    // Nếu mã hiện tại không khớp với dự đoán => tìm thấy khoảng trống
                 break;
                }
                maMoi++;
            }

            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maMoi;
    }
     public ArrayList<NhanVienDTO> getDanhSachNhanVien() {
        ArrayList<NhanVienDTO> ds = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection()) {
            String sql = "SELECT MaNhanVien, HoTen FROM nhanvien";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVienDTO nv = new NhanVienDTO();
                nv.setMaNhanVien(rs.getInt("MaNhanVien"));
                nv.setHoTen(rs.getString("HoTen"));
                ds.add(nv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
