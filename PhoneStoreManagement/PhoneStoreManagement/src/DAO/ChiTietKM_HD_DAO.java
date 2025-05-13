/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietCTKM_HD_DTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChiTietKM_HD_DAO {
    
    public ArrayList<ChiTietCTKM_HD_DTO> layDanhSachChiTietKM_HD() {
        ArrayList<ChiTietCTKM_HD_DTO> ds = new ArrayList<>();
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietctkm_hd";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ChiTietCTKM_HD_DTO dto = new ChiTietCTKM_HD_DTO(
                    rs.getInt("MaChuongTrinhKM"),
                    rs.getDouble("SoTienHoaDon"),
                    rs.getDouble("PhanTramGiamGia")
                );
                ds.add(dto);
            }
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_HD_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }

    public int themChiTietKM_HD(ChiTietCTKM_HD_DTO dto) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO chitietctkm_hd (MaChuongTrinhKM, SoTienHoaDon, PhanTramGiamGia) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, dto.getMaChuongTrinhKM());
            stmt.setDouble(2, dto.getSoTienHoaDon());
            stmt.setDouble(3, dto.getPhanTramGiamGia());
            result = stmt.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_HD_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int xoaChiTietKM_HD(int maChuongTrinhKM) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM chitietctkm_hd WHERE MaChuongTrinhKM = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, maChuongTrinhKM);
            result = stmt.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_HD_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int suaChiTietKM_HD(ChiTietCTKM_HD_DTO dto) {
        int result = 0;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "UPDATE chitietctkm_hd SET SoTienHoaDon = ?, PhanTramGiamGia = ? WHERE MaChuongTrinhKM = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, dto.getSoTienHoaDon());
            stmt.setDouble(2, dto.getPhanTramGiamGia());
            stmt.setInt(3, dto.getMaChuongTrinhKM());
            result = stmt.executeUpdate();
            JDBCUtil.closeConnection(conn);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietKM_HD_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}

