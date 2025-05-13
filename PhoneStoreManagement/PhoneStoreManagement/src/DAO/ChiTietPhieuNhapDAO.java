package DAO;

import DTO.ChiTietPhieuNhapDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class ChiTietPhieuNhapDAO implements ChiTietInterface<ChiTietPhieuNhapDTO> {

    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietPhieuNhapDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = JDBCUtil.getConnection();
                String sql = "INSERT INTO chitietphieunhap (MaPhieuNhap, MaSanPham, SoLuong, Ram, Rom, MauSac, DonGia) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, t.get(i).getMaPhieuNhap());
                pst.setInt(2, t.get(i).getMaSanPham());
                pst.setInt(3, t.get(i).getSoLuong());
                pst.setString(4, t.get(i).getRam());
                pst.setString(5, t.get(i).getRom());
                pst.setString(6, t.get(i).getMauSac());
                pst.setDouble(7, t.get(i).getDonGia());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM chitietphieunhap WHERE MaPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<ChiTietPhieuNhapDTO> t, String pk) {
        int result = this.delete(pk);
        if (result != 0) {
            result = this.insert(t);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietPhieuNhapDTO> selectAll(String t) {
        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM chitietphieunhap WHERE MaPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maPhieuNhap = rs.getString("MaPhieuNhap");
                int maSanPham = rs.getInt("MaSanPham");
                int soLuong = rs.getInt("SoLuong");
                String ram = rs.getString("Ram");
                String rom = rs.getString("Rom");
                String mauSac = rs.getString("MauSac");
                double donGia = rs.getDouble("DonGia");
                
                ChiTietPhieuNhapDTO ctPhieuNhap = new ChiTietPhieuNhapDTO(maPhieuNhap, maSanPham, soLuong, ram, rom, mauSac, donGia);
                result.add(ctPhieuNhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

}
