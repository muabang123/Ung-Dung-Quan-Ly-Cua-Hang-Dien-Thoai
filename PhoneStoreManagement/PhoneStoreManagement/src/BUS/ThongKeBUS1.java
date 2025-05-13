package BUS;

import DAO.ThongKeDAO1;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeTheoThangDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import java.util.ArrayList;
import java.util.Date;

public class ThongKeBUS1 {
    private final ThongKeDAO1 dao;

    public ThongKeBUS1() {
        dao = ThongKeDAO1.getInstance(); // Dùng instance chung
    }

    // Lấy tất cả khách hàng từ ngày rất sớm tới hiện tại
    public ArrayList<ThongKeKhachHangDTO> getAllKhachHang() {
        ArrayList<ThongKeKhachHangDTO> result = dao.getThongKeKhachHang("", new Date(0), new Date(System.currentTimeMillis()));
        return result != null ? result : new ArrayList<>();
    }

    // Lọc khách hàng theo từ khóa và thời gian
    public ArrayList<ThongKeKhachHangDTO> filterKhachHang(String text, Date start, Date end) {
        ArrayList<ThongKeKhachHangDTO> result = dao.getThongKeKhachHang(text, start, end);
        return result != null ? result : new ArrayList<>();
    }

    // Thống kê doanh thu theo từng năm
    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int yearStart, int yearEnd) {
        return dao.getDoanhThuTheoTungNam(yearStart, yearEnd) != null ? dao.getDoanhThuTheoTungNam(yearStart, yearEnd) : new ArrayList<>();
    }

    // Thống kê doanh thu theo tháng trong một năm
    public ArrayList<ThongKeTheoThangDTO> getThongKeTheoThang(int nam) {
        return dao.getThongKeTheoThang(nam) != null ? dao.getThongKeTheoThang(nam) : new ArrayList<>();
    }

    // Thống kê từng ngày trong một tháng
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTungNgayTrongThang(int thang, int nam) {
        return dao.getThongKeTungNgayTrongThang(thang, nam) != null ? dao.getThongKeTungNgayTrongThang(thang, nam) : new ArrayList<>();
    }

    // Thống kê từ ngày đến ngày
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKeTuNgayDenNgay(String start, String end) {
        return dao.getThongKeTuNgayDenNgay(start, end) != null ? dao.getThongKeTuNgayDenNgay(start, end) : new ArrayList<>();
    }

    // Thống kê 7 ngày gần nhất
    public ArrayList<ThongKeTungNgayTrongThangDTO> getThongKe7NgayGanNhat() {
        return dao.getThongKe7NgayGanNhat() != null ? dao.getThongKe7NgayGanNhat() : new ArrayList<>();
    }

    // Thêm phương thức tính tổng doanh thu
    public long getTongDoanhThu(int yearStart, int yearEnd) {
        ArrayList<ThongKeDoanhThuDTO> data = getDoanhThuTheoTungNam(yearStart, yearEnd);
        return data.stream().mapToLong(ThongKeDoanhThuDTO::getDoanhthu).sum();
    }
}