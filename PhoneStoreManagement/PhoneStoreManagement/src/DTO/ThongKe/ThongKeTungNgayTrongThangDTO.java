/*
 * Modified version of ThongKeTungNgayTrongThangDTO.java to use long for monetary fields.
 */
package DTO.ThongKe;

import java.util.Date;
import java.util.Objects;

/**
 * @author robot
 */
public class ThongKeTungNgayTrongThangDTO {
    private Date ngay;
    private long chiphi;
    private long doanhthu;
    private long loinhuan;

    public ThongKeTungNgayTrongThangDTO() {
    }

    public ThongKeTungNgayTrongThangDTO(Date ngay, long chiphi, long doanhthu, long loinhuan) {
        this.ngay = ngay;
        this.chiphi = chiphi;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public long getChiphi() {
        return chiphi;
    }

    public void setChiphi(long chiphi) {
        this.chiphi = chiphi;
    }

    public long getDoanhthu() {
        return doanhthu;
    }

    public void setDoanhthu(long doanhthu) {
        this.doanhthu = doanhthu;
    }

    public long getLoinhuan() {
        return loinhuan;
    }

    public void setLoinhuan(long loinhuan) {
        this.loinhuan = loinhuan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.ngay);
        hash = 29 * hash + (int) (this.chiphi ^ (this.chiphi >>> 32));
        hash = 29 * hash + (int) (this.doanhthu ^ (this.doanhthu >>> 32));
        hash = 29 * hash + (int) (this.loinhuan ^ (this.loinhuan >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ThongKeTungNgayTrongThangDTO other = (ThongKeTungNgayTrongThangDTO) obj;
        return this.chiphi == other.chiphi &&
               this.doanhthu == other.doanhthu &&
               this.loinhuan == other.loinhuan &&
               Objects.equals(this.ngay, other.ngay);
    }

    @Override
    public String toString() {
        return "ThongKeTungNgayTrongThangDTO{" + "ngay=" + ngay + ", chiphi=" + chiphi + ", doanhthu=" + doanhthu + ", loinhuan=" + loinhuan + '}';
    }
}