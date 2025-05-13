package DAO;

import DTO.SanPhamDTO;
import config.JDBCUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO implements DAOinterface<SanPhamDTO> {

    public static SanPhamDAO getInstance() {
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO sanpham (MaSanPham, TenSanPham, SoLuong, DonGia, DonViTinh, HinhAnh, MaLoai) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t.getMaSanPham());
            pst.setString(2, t.getTenSanPham());
            pst.setInt(3, t.getSoLuong());
            pst.setBigDecimal(4, t.getDonGia());  // Sử dụng BigDecimal cho DonGia
            pst.setString(5, t.getDonViTinh());
            pst.setString(6, t.getHinhAnh());
            pst.setInt(7, t.getMaLoai());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE sanpham SET TenSanPham=?, SoLuong=?, DonGia=?, DonViTinh=?, HinhAnh=?, MaLoai=? WHERE MaSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTenSanPham());
            pst.setInt(2, t.getSoLuong());
            pst.setBigDecimal(3, t.getDonGia());  // Cập nhật BigDecimal cho DonGia
            pst.setString(4, t.getDonViTinh());
            pst.setString(5, t.getHinhAnh());
            pst.setInt(6, t.getMaLoai());
            pst.setInt(7, t.getMaSanPham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM sanpham WHERE MaSanPham = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham ORDER BY MaSanPham ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maSanPham = rs.getInt("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuong = rs.getInt("SoLuong");
                BigDecimal donGia = rs.getBigDecimal("DonGia");
                String donViTinh = rs.getString("DonViTinh");
                String hinhAnh = rs.getString("HinhAnh");
                int maLoai = rs.getInt("MaLoai");
                SanPhamDTO sp = new SanPhamDTO(maSanPham, tenSanPham, soLuong, donGia, donViTinh, hinhAnh, maLoai);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE MaSanPham=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maSanPham = rs.getInt("MaSanPham");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuong = rs.getInt("SoLuong");
                BigDecimal donGia = rs.getBigDecimal("DonGia");  // Sử dụng BigDecimal cho DonGia
                String donViTinh = rs.getString("DonViTinh");
                String hinhAnh = rs.getString("HinhAnh");
                int maLoai = rs.getInt("MaLoai");
                result = new SanPhamDTO(maSanPham, tenSanPham, soLuong, donGia, donViTinh, hinhAnh, maLoai);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
   // Kiểm tra mã sản phẩm có tồn tại trong cơ sở dữ liệu hay không
    public boolean isMaSanPhamExist(int maSanPham) {
        boolean exists = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT COUNT(*) FROM sanpham WHERE MaSanPham = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, maSanPham);
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                exists = true; // Mã sản phẩm tồn tại
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exists;
    }

    

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND TABLE_NAME = 'sanpham'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public ArrayList<SanPhamDTO> getDanhSachSanPham() {
        ArrayList<SanPhamDTO> ds = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection()) {
            String sql = "SELECT MaSanPham, TenSanPham FROM sanpham";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMaSanPham(rs.getInt("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                ds.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }

}
