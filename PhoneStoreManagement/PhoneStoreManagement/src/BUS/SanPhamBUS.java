package BUS;

import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import java.util.ArrayList;

public class SanPhamBUS {

    public final SanPhamDAO spDAO = new SanPhamDAO();
    private ArrayList<SanPhamDTO> listSP = new ArrayList<>();

    // Constructor: Khởi tạo dữ liệu khi tạo đối tượng
    public SanPhamBUS() {
       loadData();
    }
    
    public ArrayList<SanPhamDTO> search(String text) {
    text = text.toLowerCase();
    ArrayList<SanPhamDTO> result = new ArrayList<>();
    for (SanPhamDTO sp : this.getAll()) {
        if (Integer.toString(sp.getMaSanPham()).toLowerCase().contains(text)
            || sp.getTenSanPham().toLowerCase().contains(text)) {
            result.add(sp);
        }
    }
    return result;
}


    // Phương thức tải dữ liệu từ DAO
    public void loadData() {
        listSP = spDAO.selectAll();  // Cập nhật lại danh sách sản phẩm từ DAO
    }

    // Lấy toàn bộ danh sách sản phẩm
    public ArrayList<SanPhamDTO> getAll() {
        return this.listSP;
    }

    // Lấy sản phẩm theo index
    public SanPhamDTO getByIndex(int index) {
        if (index >= 0 && index < listSP.size()) {
            return this.listSP.get(index);
        }
        return null;  // Trả về null nếu index không hợp lệ
    }

    // Lấy sản phẩm theo mã sản phẩm
  public SanPhamDTO getByMaSP(int maSP) {
    for (SanPhamDTO sp : this.getAll()) {
        if (sp.getMaSanPham() == maSP) {
            return sp;
        }
    }
    return null;
}


    // Lấy vị trí của sản phẩm trong list theo mã sản phẩm
    public int getIndexByMaSP(int maSanPham) {
        for (int i = 0; i < this.listSP.size(); i++) {
            if (this.listSP.get(i).getMaSanPham() == maSanPham) {
                return i;
            }
        }
        return -1;  // Trả về -1 nếu không tìm thấy sản phẩm
    }

    // Thêm sản phẩm mới vào danh sách và vào cơ sở dữ liệu
    public Boolean add(SanPhamDTO sp) {
        boolean check = spDAO.insert(sp) != 0;
        if (check) {
            this.listSP.add(sp);  // Thêm vào danh sách sản phẩm nếu thành công
        }
        return check;
    }

    // Xóa sản phẩm khỏi danh sách và cơ sở dữ liệu
    public Boolean delete(SanPhamDTO sp) {
        boolean check = spDAO.delete(Integer.toString(sp.getMaSanPham())) != 0;
        if (check) {
            this.listSP.remove(sp);  // Xóa khỏi danh sách nếu thành công
        }
        return check;
    }

    // Cập nhật thông tin sản phẩm
    public Boolean update(SanPhamDTO sp) {
        boolean check = spDAO.update(sp) != 0;
        if (check) {
            this.listSP.set(getIndexByMaSP(sp.getMaSanPham()), sp);  // Cập nhật trong danh sách
        }
        return check;
    }

  // Kiểm tra mã sản phẩm có tồn tại trong cơ sở dữ liệu hay không
    public boolean isMaSanPhamExist(int maSanPham) {
        return spDAO.isMaSanPhamExist(maSanPham);
    }


    


    

    // Tính tổng số lượng sản phẩm
    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (SanPhamDTO sp : this.listSP) {
            totalQuantity += sp.getSoLuong();
        }
        return totalQuantity;
    }

    // Lấy sản phẩm theo mã sản phẩm từ DAO
    public SanPhamDTO getSanPhamByMaSP(int maSanPham) {
        return spDAO.selectById(Integer.toString(maSanPham));
    }
}
