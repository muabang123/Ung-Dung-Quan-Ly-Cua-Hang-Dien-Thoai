package BUS;

import DAO.PhieuNhapDAO;
import DTO.PhieuNhapDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PhieuNhapBUS {

    private final PhieuNhapDAO phieuNhapDAO = new PhieuNhapDAO();
    private ArrayList<PhieuNhapDTO> listPhieuNhap;

    public PhieuNhapBUS() {
        listPhieuNhap = new ArrayList<>();
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        this.listPhieuNhap = phieuNhapDAO.selectAll();
        return this.listPhieuNhap;
    }

    public ArrayList<PhieuNhapDTO> getAllList() {
        return this.listPhieuNhap;
    }

    public boolean add(PhieuNhapDTO phieu) {
        return phieuNhapDAO.insert(phieu) != 0;
    }

    public boolean update(PhieuNhapDTO phieu) {
        return phieuNhapDAO.update(phieu) != 0;
    }

    public boolean delete(String maPhieuNhap) {
        return phieuNhapDAO.delete(maPhieuNhap) != 0;
    }

    public PhieuNhapDTO getById(String maPhieuNhap) {
        return phieuNhapDAO.selectById(maPhieuNhap);
    }

    public int getNextId() {
        return phieuNhapDAO.getAutoIncrement();
    }

}
