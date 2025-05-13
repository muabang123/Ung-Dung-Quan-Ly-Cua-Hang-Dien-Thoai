package DAO;

import DTO.PhieuNhapDTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhieuNhapDAO implements DAOinterface<PhieuNhapDTO> {

    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }

    @Override
    public int insert(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO phieunhap (MaPhieuNhap, MaNhanVien, MaNhaCungCap, NgayNhap, TongTien) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaPhieuNhap());
            pst.setString(2, t.getMaNhanVien());
            pst.setString(3, t.getMaNhaCungCap());
            pst.setTimestamp(4, t.getNgayNhap());
            pst.setDouble(5, t.getTongTien());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(PhieuNhapDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE phieunhap SET MaNhanVien=?, MaNhaCungCap=?, NgayNhap=?, TongTien=? WHERE MaPhieuNhap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaNhanVien());
            pst.setString(2, t.getMaNhaCungCap());
            pst.setTimestamp(3, t.getNgayNhap());
            pst.setDouble(4, t.getTongTien());
            pst.setString(5, t.getMaPhieuNhap());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM phieunhap WHERE MaPhieuNhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuNhap ORDER BY MaPhieuNhap ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                PhieuNhapDTO pn = new PhieuNhapDTO(
                    rs.getString("MaPhieuNhap"),
                    rs.getString("MaNhanVien"),
                    rs.getString("MaNhaCungCap"),
                    rs.getTimestamp("NgayNhap"),
                    rs.getLong("TongTien")
                );
                result.add(pn);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public PhieuNhapDTO selectById(String t) {
        PhieuNhapDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE MaPhieuNhap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = new PhieuNhapDTO(
                    rs.getString("MaPhieuNhap"),
                    rs.getString("MaNhanVien"),
                    rs.getString("MaNhaCungCap"),
                    rs.getTimestamp("NgayNhap"),
                    rs.getLong("TongTien")
                );
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlibanhang' AND TABLE_NAME = 'PhieuNhap'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
        public String getLastMaPhieuNhap() {
        String lastId = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT MaPhieuNhap FROM PhieuNhap ORDER BY MaPhieuNhap DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lastId = rs.getString("MaPhieuNhap");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;
    }

}
