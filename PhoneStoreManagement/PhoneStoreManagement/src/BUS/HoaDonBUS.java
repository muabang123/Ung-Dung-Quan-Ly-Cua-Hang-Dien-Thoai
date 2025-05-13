/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;


import DAO.HoaDonDAO;
import DTO.HoaDonDTO;

import java.util.ArrayList;

/**
 *
 * @author minhminh
 */
public class HoaDonBUS {
    
    private final HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private ArrayList<HoaDonDTO> listHoaDon;

    public HoaDonBUS() {
        listHoaDon = new ArrayList<>();
    }

    // Lấy tất cả hóa đơn
    public ArrayList<HoaDonDTO> getAll() {
        this.listHoaDon = hoaDonDAO.selectAll();
        return this.listHoaDon;
    }

    // Trả về danh sách hóa đơn đã tải về từ cơ sở dữ liệu
    public ArrayList<HoaDonDTO> getAllList() {
        return this.listHoaDon;
    }

    // Thêm hóa đơn mới
    public boolean add(HoaDonDTO hoaDon) {
        return hoaDonDAO.insert(hoaDon) != 0;
    }

    // Cập nhật hóa đơn
    public boolean update(HoaDonDTO hoaDon) {
        return hoaDonDAO.update(hoaDon) != 0;
    }

    // Xóa hóa đơn
    public boolean delete(String maHoaDon) {
        return hoaDonDAO.delete(maHoaDon) != 0;
    }

    // Lấy hóa đơn theo mã hóa đơn
    public HoaDonDTO getById(String maHoaDon) {
        return hoaDonDAO.selectById(maHoaDon);
    }

    // Lấy giá trị tự động tăng của mã hóa đơn
    public int getNextId() {
        return hoaDonDAO.getAutoIncrement();
    }


}
