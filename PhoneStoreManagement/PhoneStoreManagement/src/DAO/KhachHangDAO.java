package DAO;


import DTO.KhachHangDTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KhachHangDAO implements DAOinterface<KhachHangDTO> {

    public static KhachHangDAO getInstance() {
        return new KhachHangDAO();
    }

    @Override
    public int insert(KhachHangDTO kh) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO KhachHang (MaKhachHang, HoTenKhachHang, DiaChi, SoDienThoai, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, kh.getMaKhachHang());
            pst.setString(2, kh.getHoTenKhachHang());
            pst.setString(3, kh.getDiaChi());
            pst.setString(4, kh.getSoDienThoai());
            pst.setString(5, kh.getEmail());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(KhachHangDTO kh) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE KhachHang SET HoTenKhachHang=?, DiaChi=?, SoDienThoai=?, Email=? WHERE MaKhachHang=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, kh.getHoTenKhachHang());
            pst.setString(2, kh.getDiaChi());
            pst.setString(3, kh.getSoDienThoai());
            pst.setString(4, kh.getEmail());
            pst.setInt(5, kh.getMaKhachHang());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String id) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM KhachHang WHERE MaKhachHang = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    

    @Override
    public ArrayList<KhachHangDTO> selectAll() {
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM KhachHang ORDER BY MaKhachHang ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maKH = rs.getInt("MaKhachHang");
                String hoTen = rs.getString("HoTenKhachHang");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                KhachHangDTO kh = new KhachHangDTO(maKH, hoTen, diaChi, sdt, email);
                result.add(kh);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public KhachHangDTO selectById(String id) {
        KhachHangDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM KhachHang WHERE MaKhachHang=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maKH = rs.getInt("MaKhachHang");
                String hoTen = rs.getString("HoTenKhachHang");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                result = new KhachHangDTO(maKH, hoTen, diaChi, sdt, email);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlibanhang' AND TABLE_NAME = 'KhachHang'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     public ArrayList<KhachHangDTO> getDanhSachKhachHang() {
        ArrayList<KhachHangDTO> ds = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection()) {
            String sql = "SELECT MaKhachHang, HoTenKhachHang FROM khachhang";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KhachHangDTO kh = new KhachHangDTO(0, "", "", "", ""); // dùng constructor mặc định
                kh.setMaKhachHang(rs.getInt("MaKhachHang"));
                kh.setHoTenKhachHang(rs.getString("HoTenKhachHang"));
                ds.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ds;
    }


}
