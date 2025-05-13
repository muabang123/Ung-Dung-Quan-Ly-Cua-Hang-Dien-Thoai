# Ung-Dung-Quan-Ly-Cua-Hang-Dien-Thoai
# Hướng dẫn cài đặt
1. Phần mềm yêu cầu
- Java JDK 8 trở lên
- NetBeans IDE (đã tích hợp sẵn thư viện Swing)
- XAMPP (chạy Apache và MySQL)
- Trình duyệt web để truy cập phpMyAdmin
2. Cấu hình cơ sở dữ liệu
- clone về máy bằng cách:
      + Mở cmd và gõ git clone https://github.com/muabang123/Ung-Dung-Quan-Ly-Cua-Hang-Dien-Thoai.git
- Mở XAMPP và khởi động Apache và MySQL
- Truy cập http://localhost/phpmyadmin
- Tạo cơ sở dữ liệu mới với tên: quanlibanhang
- Import file SQL được cung cấp trong thư mục database của project
3. Cấu hình kết nối trong Java
Trong file JDBCUtil.java, cấu hình chuỗi kết nối như sau:
String url = "jdbc:mysql://localhost:3306/quanlibanhang";
String user = "root";
String password = "";
4. Chạy chương trình
- Mở project bằng NetBeans
- Build và Run chương trình
- Đăng nhập bằng tài khoản mặc định:
  + Username: admin
  + Password: 123456
