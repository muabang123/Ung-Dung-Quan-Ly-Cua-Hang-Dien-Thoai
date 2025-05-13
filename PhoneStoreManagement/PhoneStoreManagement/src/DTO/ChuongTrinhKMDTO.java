/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

public class ChuongTrinhKMDTO {
    private int maChuongTrinhKM;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public ChuongTrinhKMDTO() {}

    public ChuongTrinhKMDTO(int maChuongTrinhKM, Date ngayBatDau, Date ngayKetThuc) {
        this.maChuongTrinhKM = maChuongTrinhKM;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public int getMaChuongTrinhKM() {
        return maChuongTrinhKM;
    }

    public void setMaChuongTrinhKM(int maChuongTrinhKM) {
        this.maChuongTrinhKM = maChuongTrinhKM;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
}

