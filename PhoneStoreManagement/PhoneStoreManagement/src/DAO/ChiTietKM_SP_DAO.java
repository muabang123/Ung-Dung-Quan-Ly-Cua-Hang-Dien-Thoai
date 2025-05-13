/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietCTKM_SP_DTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietKM_SP_DAO {

    public ArrayList<ChiTietCTKM_SP_DTO> layDanhSachChiTietKM_SP() {
        ArrayList<ChiTietCTKM_SP_DTO> ds = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietctkm_sp";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ChiTietCTKM_SP_DTO dto = new ChiTietCTKM_SP_DTO(
                    rs.getInt("MaChuongTrinhKM"),
                    rs.getInt("MaSanPham"),
                    rs.getDouble("PhanTramGiamGia")
                );
                ds.add(dto);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_SP_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    public int themChiTietKM_SP(ChiTietCTKM_SP_DTO dto) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO chitietctkm_sp (MaChuongTrinhKM, MaSanPham, PhanTramGiamGia) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dto.getMaChuongTrinhKM());
            stmt.setInt(2, dto.getMaSanPham());
            stmt.setDouble(3, dto.getPhanTramGiamGia());
            result = stmt.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_SP_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int xoaChiTietKM_SP(int maChuongTrinhKM, int maSanPham) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM chitietctkm_sp WHERE MaChuongTrinhKM = ? AND MaSanPham = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maChuongTrinhKM);
            stmt.setInt(2, maSanPham);
            result = stmt.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_SP_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int suaChiTietKM_SP(ChiTietCTKM_SP_DTO dto) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE chitietctkm_sp SET PhanTramGiamGia = ? WHERE MaChuongTrinhKM = ? AND MaSanPham = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, dto.getPhanTramGiamGia());
            stmt.setInt(2, dto.getMaChuongTrinhKM());
            stmt.setInt(3, dto.getMaSanPham());
            result = stmt.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_SP_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

