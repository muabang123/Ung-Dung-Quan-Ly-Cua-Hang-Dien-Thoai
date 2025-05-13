/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Modified version of ThongKeTheoThangDTO.java to use long for monetary fields.
 */
package DTO.ThongKe;

/**
 * @author robot
 */
public class ThongKeTheoThangDTO {
    private int thang;
    private long chiphi;
    private long doanhthu;
    private long loinhuan;
    
    public ThongKeTheoThangDTO() {
    }
    
    public ThongKeTheoThangDTO(int thang, long chiphi, long doanhthu, long loinhuan) {
        this.thang = thang;
        this.chiphi = chiphi;
        this.doanhthu = doanhthu;
        this.loinhuan = loinhuan;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
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
        int hash = 7;
        hash = 59 * hash + this.thang;
        hash = 59 * hash + (int) (this.chiphi ^ (this.chiphi >>> 32));
        hash = 59 * hash + (int) (this.doanhthu ^ (this.doanhthu >>> 32));
        hash = 59 * hash + (int) (this.loinhuan ^ (this.loinhuan >>> 32));
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
        ThongKeTheoThangDTO other = (ThongKeTheoThangDTO) obj;
        return this.thang == other.thang &&
               this.chiphi == other.chiphi &&
               this.doanhthu == other.doanhthu &&
               this.loinhuan == other.loinhuan;
    }

    @Override
    public String toString() {
        return "ThongKeTheoThangDTO{" + "thang=" + thang + ", chiphi=" + chiphi + ", doanhthu=" + doanhthu + ", loinhuan=" + loinhuan + '}';
    }
}