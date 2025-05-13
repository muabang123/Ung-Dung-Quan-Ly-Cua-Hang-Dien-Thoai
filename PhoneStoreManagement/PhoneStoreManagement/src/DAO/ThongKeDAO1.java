/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ThongKeDAO1 {

    public static ThongKeDAO1 getInstance() {
        return new ThongKeDAO1();
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                WITH RECURSIVE years(year) AS (
                    SELECT ? AS start_year
                    UNION ALL
                    SELECT year + 1
                    FROM years
                    WHERE year < ?
                )
                SELECT 
                    years.year AS nam,
                    COALESCE(SUM(ctpn.SoLuong * ctpn.DonGia), 0) AS chiphi,
                    COALESCE(SUM(hd.TongTienPhaiTra), 0) AS doanhthu
                FROM years
                LEFT JOIN hoadon hd ON YEAR(hd.NgayLapHoaDon) = years.year
                LEFT JOIN chitiethoadon cthd ON hd.MaHoaDon = cthd.MaHoaDon
                LEFT JOIN phieunhap pn ON YEAR(pn.NgayNhap) = years.year
                LEFT JOIN chitietphieunhap ctpn ON pn.MaPhieuNhap = ctpn.MaPhieuNhap
                GROUP BY years.year
                ORDER BY years.year;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, year_start);
            pst.setInt(2, year_end);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("nam");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
                result.add(x);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ThongKeDAO1.class.getName()).log(Level.SEVERE, "Error in getDoanhThuTheoTungNam", e);
        }
        return result;
    }

    public ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeEnd.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                WITH kh AS (
                    SELECT khachhang.MaKhachHang, khachhang.HoTenKhachHang, 
                           COUNT(hoadon.MaHoaDon) AS tongsophieu, 
                           SUM(hoadon.TongTienPhaiTra) AS tongsotien
                    FROM khachhang
                    JOIN hoadon ON khachhang.MaKhachHang = hoadon.MaKhachHang
                    WHERE hoadon.NgayLapHoaDon BETWEEN ? AND ?
                    GROUP BY khachhang.MaKhachHang, khachhang.HoTenKhachHang
                )
                SELECT MaKhachHang, HoTenKhachHang, 
                       COALESCE(kh.tongsophieu, 0) AS soluong, 
                       COALESCE(kh.tongsotien, 0) AS total
                FROM kh 
                WHERE HoTenKhachHang LIKE ? OR MaKhachHang LIKE ?;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(calendar.getTimeInMillis()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("MaKhachHang");
                String tenkh = rs.getString("HoTenKhachHang");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getLong("total");
                ThongKeKhachHangDTO x = new ThongKeKhachHangDTO(makh, tenkh, soluong, tongtien);
                result.add(x);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ThongKeDAO1.class.getName()).log(Level.SEVERE, "Error in getThongKeKhachHang", e);
        }
        return result;
    }

    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        ArrayList<ThongKeTheoThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                SELECT months.month AS thang,
                       COALESCE(SUM(ctpn.SoLuong * ctpn.DonGia), 0) AS chiphi,
                       COALESCE(SUM(hd.TongTienPhaiTra), 0) AS doanhthu
                FROM (
                    SELECT 1 AS month UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4
                    UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8
                    UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12
                ) AS months
                LEFT JOIN hoadon hd ON MONTH(hd.NgayLapHoaDon) = months.month AND YEAR(hd.NgayLapHoaDon) = ?
                LEFT JOIN chitiethoadon cthd ON hd.MaHoaDon = cthd.MaHoaDon
                LEFT JOIN phieunhap pn ON MONTH(pn.NgayNhap) = months.month AND YEAR(pn.NgayNhap) = ?
                LEFT JOIN chitietphieunhap ctpn ON pn.MaPhieuNhap = ctpn.MaPhieuNhap
                GROUP BY months.month
                ORDER BY months.month;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nam);
            pst.setInt(2, nam);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int thang = rs.getInt("thang");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                ThongKeTheoThangDTO thongke = new ThongKeTheoThangDTO(thang, chiphi, doanhthu, loinhuan);
                result.add(thongke);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ThongKeDAO1.class.getName()).log(Level.SEVERE, "Error in getThongKeTheoThang", e);
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                SELECT 
                    dates.date AS ngay,
                    COALESCE(SUM(ctpn.SoLuong * ctpn.DonGia), 0) AS chiphi,
                    COALESCE(SUM(hd.TongTienPhaiTra), 0) AS doanhthu
                FROM (
                    SELECT DATE(?) + INTERVAL c.number DAY AS date
                    FROM (
                        SELECT a.N AS number
                        FROM (
                            SELECT 0 AS N UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3
                            UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7
                            UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10 UNION ALL SELECT 11
                            UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
                            UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19
                            UNION ALL SELECT 20 UNION ALL SELECT 21 UNION ALL SELECT 22 UNION ALL SELECT 23
                            UNION ALL SELECT 24 UNION ALL SELECT 25 UNION ALL SELECT 26 UNION ALL SELECT 27
                            UNION ALL SELECT 28 UNION ALL SELECT 29 UNION ALL SELECT 30
                        ) AS a
                    ) AS c
                    WHERE DATE(?) + INTERVAL c.number DAY <= LAST_DAY(?)
                ) AS dates
                LEFT JOIN hoadon hd ON DATE(hd.NgayLapHoaDon) = dates.date
                LEFT JOIN chitiethoadon cthd ON hd.MaHoaDon = cthd.MaHoaDon
                LEFT JOIN phieunhap pn ON DATE(pn.NgayNhap) = dates.date
                LEFT JOIN chitietphieunhap ctpn ON pn.MaPhieuNhap = ctpn.MaPhieuNhap
                GROUP BY dates.date
                ORDER BY dates.date;
            """;
            String ngayString = String.format("%d-%02d-01", nam, thang);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, ngayString);
            pst.setString(2, ngayString);
            pst.setString(3, ngayString);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ThongKeDAO1.class.getName()).log(Level.SEVERE, "Error in getThongKeTungNgayTrongThang", e);
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                WITH RECURSIVE dates(date) AS (
                    SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY)
                    UNION ALL
                    SELECT DATE_ADD(date, INTERVAL 1 DAY)
                    FROM dates
                    WHERE date < CURDATE()
                )
                SELECT 
                    dates.date AS ngay,
                    COALESCE(SUM(ctpn.SoLuong * ctpn.DonGia), 0) AS chiphi,
                    COALESCE(SUM(hd.TongTienPhaiTra), 0) AS doanhthu
                FROM dates
                LEFT JOIN hoadon hd ON DATE(hd.NgayLapHoaDon) = dates.date
                LEFT JOIN chitiethoadon cthd ON hd.MaHoaDon = cthd.MaHoaDon
                LEFT JOIN phieunhap pn ON DATE(pn.NgayNhap) = dates.date
                LEFT JOIN chitietphieunhap ctpn ON pn.MaPhieuNhap = ctpn.MaPhieuNhap
                GROUP BY dates.date
                ORDER BY dates.date;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ThongKeDAO1.class.getName()).log(Level.SEVERE, "Error in getThongKe7NgayGanNhat", e);
        }
        return result;
    }

    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(String start, String end) {
        ArrayList<ThongKeTungNgayTrongThangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                WITH RECURSIVE dates(date) AS (
                    SELECT ? AS start_date
                    UNION ALL
                    SELECT DATE_ADD(date, INTERVAL 1 DAY)
                    FROM dates
                    WHERE date < ?
                )
                SELECT 
                    dates.date AS ngay,
                    COALESCE(SUM(ctpn.SoLuong * ctpn.DonGia), 0) AS chiphi,
                    COALESCE(SUM(hd.TongTienPhaiTra), 0) AS doanhthu
                FROM dates
                LEFT JOIN hoadon hd ON DATE(hd.NgayLapHoaDon) = dates.date
                LEFT JOIN chitiethoadon cthd ON hd.MaHoaDon = cthd.MaHoaDon
                LEFT JOIN phieunhap pn ON DATE(pn.NgayNhap) = dates.date
                LEFT JOIN chitietphieunhap ctpn ON pn.MaPhieuNhap = ctpn.MaPhieuNhap
                GROUP BY dates.date
                ORDER BY dates.date;
            """;
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, start);
            pst.setString(2, end);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Date ngay = rs.getDate("ngay");
                long chiphi = rs.getLong("chiphi");
                long doanhthu = rs.getLong("doanhthu");
                long loinhuan = doanhthu - chiphi;
                ThongKeTungNgayTrongThangDTO tn = new ThongKeTungNgayTrongThangDTO(ngay, chiphi, doanhthu, loinhuan);
                result.add(tn);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            Logger.getLogger(ThongKeDAO1.class.getName()).log(Level.SEVERE, "Error in getThongKeTuNgayDenNgay", e);
        }
        return result;
    }
}