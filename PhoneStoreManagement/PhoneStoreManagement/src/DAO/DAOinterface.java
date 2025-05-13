package DAO;

import java.util.ArrayList;

public interface DAOinterface<T> {

    // Phương thức thêm một đối tượng vào cơ sở dữ liệu
    int insert(T t);

    // Phương thức cập nhật đối tượng trong cơ sở dữ liệu
    int update(T t);

    // Phương thức xóa đối tượng trong cơ sở dữ liệu
    int delete(String t);

    // Phương thức lấy tất cả đối tượng từ cơ sở dữ liệu
    ArrayList<T> selectAll();

    // Phương thức lấy đối tượng theo ID từ cơ sở dữ liệu
    T selectById(String t);

    // Phương thức lấy giá trị auto increment từ cơ sở dữ liệu
    int getAutoIncrement();
}
