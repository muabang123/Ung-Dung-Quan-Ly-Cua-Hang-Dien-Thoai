/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import DTO.HoaDonDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minhminh
 */
public class HoaDonDAO implements DAOinterface<HoaDonDTO>{
     public static HoaDonDAO getInstance(){
        return new HoaDonDAO();
    }

    @Override
    public int insert(HoaDonDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `hoadon`(`MaHoaDon`, `NgayLapHoaDon`, `MaNhanVien`, `MaKhachHang`, `TongTien`, `TongTienPhaiTra`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getMaHoaDon());
            pst.setTimestamp(2, t.getNgayLapHoaDon());
            pst.setString(3, t.getMaNhanVien());
            pst.setString(4, t.getMaKhachHang());
            pst.setDouble(5, t.getTongTien());
            pst.setDouble(6, t.getTongTienPhaiTra());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(HoaDonDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `hoadon` SET `NgayLapHoaDon`=?,`MaNhanVien`=?,`MaKhachHang`=?,`TongTien`=?,`TongTienPhaiTra`=? WHERE `MaHoaDon`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, t.getNgayLapHoaDon());
            pst.setString(2, t.getMaNhanVien());
            pst.setString(3, t.getMaKhachHang());
            pst.setDouble(4, t.getTongTien());
            pst.setDouble(5, t.getTongTienPhaiTra());
            pst.setString(6, t.getMaHoaDon());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM `HoaDon` WHERE MaHoaDon = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<HoaDonDTO> selectAll() {
        ArrayList<HoaDonDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM hoadon ORDER BY MaHoaDon ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maHoaDon = rs.getString("MaHoaDon");
                Timestamp ngayLapHoaDon = rs.getTimestamp("NgayLapHoaDon");
                String maNhanVien = rs.getString("MaNhanVien");
                String maKhachHang = rs.getString("MaKhachHang");
                long tongTien = rs.getLong("TongTien");
                long tongTienPhaiTra = rs.getLong("TongTienPhaiTra");
                HoaDonDTO hoaDon = new HoaDonDTO(maHoaDon, ngayLapHoaDon, maNhanVien, maKhachHang, tongTien, tongTienPhaiTra);
                result.add(hoaDon);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public HoaDonDTO selectById(String t) {
        HoaDonDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM hoadon WHERE MaHoaDon=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String maHoaDon = rs.getString("MaHoaDon");
                Timestamp ngayLapHoaDon = rs.getTimestamp("NgayLapHoaDon");
                String maNhanVien = rs.getString("MaNhanVien");
                String maKhachHang = rs.getString("MaKhachHang");
                long tongTien = rs.getLong("TongTien");
                long tongTienPhaiTra = rs.getLong("TongTienPhaiTra");
                result = new HoaDonDTO(maHoaDon, ngayLapHoaDon, maNhanVien, maKhachHang, tongTien, tongTienPhaiTra);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND TABLE_NAME = 'hoadon'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs.next()) {
                    result = rs.getInt("AUTO_INCREMENT");
                }
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public String getLastMaHoaDon() {
        String lastId = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT MaHoaDon FROM hoadon ORDER BY MaHoaDon DESC LIMIT 1";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                lastId = rs.getString("MaHoaDon");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(PhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastId;
    }
}
