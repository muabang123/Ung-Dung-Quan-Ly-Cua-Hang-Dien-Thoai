/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import config.JDBCUtil;
import DTO.ChuongTrinhKMDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChuongTrinhKMDAO {

    public ArrayList<ChuongTrinhKMDTO> layDanhSachChuongTrinhKM() {
        ArrayList<ChuongTrinhKMDTO> ds = new ArrayList<>();
        Connection c = JDBCUtil.getConnection();
        String sql = "SELECT * FROM chuongtrinhkm";

        try (PreparedStatement pst = c.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                ChuongTrinhKMDTO ct = new ChuongTrinhKMDTO();
                ct.setMaChuongTrinhKM(rs.getInt("MaChuongTrinhKM"));
                ct.setNgayBatDau(rs.getDate("NgayBatDau"));
                ct.setNgayKetThuc(rs.getDate("NgayKetThuc"));
                ds.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(c);
        }

        return ds;
    }

    public boolean themChuongTrinhKM(ChuongTrinhKMDTO ct) {
        boolean ketQua = false;
        Connection c = JDBCUtil.getConnection();
        String sql = "INSERT INTO chuongtrinhkm (NgayBatDau, NgayKetThuc) VALUES (?, ?)";

        try (PreparedStatement pst = c.prepareStatement(sql)) {
            pst.setDate(1, ct.getNgayBatDau());
            pst.setDate(2, ct.getNgayKetThuc());
            ketQua = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(c);
        }

        return ketQua;
    }

    public boolean capNhatChuongTrinhKM(ChuongTrinhKMDTO ct) {
        boolean ketQua = false;
        Connection c = JDBCUtil.getConnection();
        String sql = "UPDATE chuongtrinhkm SET NgayBatDau = ?, NgayKetThuc = ? WHERE MaChuongTrinhKM = ?";

        try (PreparedStatement pst = c.prepareStatement(sql)) {
            pst.setDate(1, ct.getNgayBatDau());
            pst.setDate(2, ct.getNgayKetThuc());
            pst.setInt(3, ct.getMaChuongTrinhKM());
            ketQua = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(c);
        }

        return ketQua;
    }

    public boolean xoaChuongTrinhKM(int maChuongTrinh) {
        boolean ketQua = false;
        Connection c = JDBCUtil.getConnection();
        String sql = "DELETE FROM chuongtrinhkm WHERE MaChuongTrinhKM = ?";

        try (PreparedStatement pst = c.prepareStatement(sql)) {
            pst.setInt(1, maChuongTrinh);
            ketQua = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(c);
        }

        return ketQua;
    }
    public int getMaCTKMMax() {
        int max = -1;
        try {
            Connection conn = JDBCUtil.getConnection();
            String sql = "SELECT MAX(MaChuongTrinhKM) AS MaxMa FROM chuongtrinhkm";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                max = rs.getInt("MaxMa");
            }
            JDBCUtil.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
}

}

