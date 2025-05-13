package DAO;


import DTO.NhaCungCapDTO;
import config.JDBCUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhaCungCapDAO implements DAOinterface<NhaCungCapDTO> {

    public static NhaCungCapDAO getInstance() {
        return new NhaCungCapDAO();
    }

    @Override
    public int insert(NhaCungCapDTO ncc) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO NhaCungCap (MaNhaCungCap, TenNhaCungCap, DiaChi, SoDienThoai, Email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, ncc.getMaNCC());
            pst.setString(2, ncc.getTenNCC());
            pst.setString(3, ncc.getDiaChi());
            pst.setString(4, ncc.getSDT());
            pst.setString(5, ncc.getEmail());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhaCungCapDTO ncc) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE NhaCungCap SET TenNhaCungCap=?, DiaChi=?, SoDienThoai=?, Email=? WHERE MaNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ncc.getTenNCC());
            pst.setString(2, ncc.getDiaChi());
            pst.setString(3, ncc.getSDT());
            pst.setString(4, ncc.getEmail());
            pst.setInt(5, ncc.getMaNCC());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String id) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM NhaCungCap WHERE MaNhaCungCap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhaCungCapDTO> selectAll() {
        ArrayList<NhaCungCapDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhaCungCap ORDER BY MaNhaCungCap ASC";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int maNCC = rs.getInt("MaNhaCungCap");
                String tenNCC = rs.getString("TenNhaCungCap");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");
                NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaChi, sdt, email);
                result.add(ncc);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public NhaCungCapDTO selectById(String id) {
        NhaCungCapDTO result = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM NhaCungCap WHERE MaNhaCungCap=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int maNCC = rs.getInt("MaNCC");
                String tenNCC = rs.getString("TenNCC");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("SDT");
                String email = rs.getString("Email");
                result = new NhaCungCapDTO(maNCC, tenNCC, diaChi, sdt, email);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlibanhang' AND TABLE_NAME = 'NhaCungCap'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("AUTO_INCREMENT");
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhaCungCapDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
