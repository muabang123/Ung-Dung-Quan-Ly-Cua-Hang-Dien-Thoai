/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietHoaDonDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;


/**
 *
 * @author minhminh
 */
public class ChiTietHoaDonDAO implements ChiTietInterface<ChiTietHoaDonDTO>{
     public static ChiTietHoaDonDAO getInstance() {
        return new ChiTietHoaDonDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietHoaDonDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = JDBCUtil.getConnection();
                String sql = "INSERT INTO chitiethoadon (MaHoaDon, MaSanPham, SoLuong, Ram, Rom, MauSac, DonGia, ThanhTien, SoTienConLai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, t.get(i).getMaHoaDon());
                pst.setInt(2, t.get(i).getMaSanPham());
                pst.setInt(3, t.get(i).getSoLuong());
                pst.setString(4, t.get(i).getRam());
                pst.setString(5, t.get(i).getRom());
                pst.setString(6, t.get(i).getMauSac());
                pst.setDouble(7, t.get(i).getDonGia());
                pst.setDouble(8, t.get(i).getThanhTien());
                pst.setDouble(9, t.get(i).getSoTienConLai());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM chitiethoadon WHERE MaHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietHoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietHoaDonDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietHoaDonDTO> selectAll(String t) {
        ArrayList<ChiTietHoaDonDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitiethoadon WHERE MaHoaDon = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("MaHoaDon");
                int maSanPham = rs.getInt("MaSanPham");
                int soLuong = rs.getInt("SoLuong");
                String ram = rs.getString("Ram");
                String rom = rs.getString("Rom");
                String mauSac = rs.getString("MauSac");
                double donGia = rs.getDouble("DonGia");
                double thanhTien = rs.getDouble("ThanhTien");
                double soTienConLai = rs.getDouble("SoTienConLai");
                
                ChiTietHoaDonDTO ctHoaDon = new ChiTietHoaDonDTO(maHoaDon, maSanPham, soLuong, ram, rom, mauSac, donGia, thanhTien, soTienConLai);
                result.add(ctHoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
}
