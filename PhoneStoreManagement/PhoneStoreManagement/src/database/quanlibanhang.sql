-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 13, 2025 at 11:33 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlibanhang`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietctkm_hd`
--

CREATE TABLE `chitietctkm_hd` (
  `MaChuongTrinhKM` int(11) NOT NULL,
  `SoTienHoaDon` decimal(20,2) DEFAULT NULL,
  `PhanTramGiamGia` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietctkm_hd`
--

INSERT INTO `chitietctkm_hd` (`MaChuongTrinhKM`, `SoTienHoaDon`, `PhanTramGiamGia`) VALUES
(1, 20000000.00, 3.00),
(2, 35000000.00, 5.00),
(4, 2000000.00, 10.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitietctkm_sp`
--

CREATE TABLE `chitietctkm_sp` (
  `MaChuongTrinhKM` int(11) NOT NULL,
  `MaSanPham` int(11) NOT NULL,
  `PhanTramGiamGia` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietctkm_sp`
--

INSERT INTO `chitietctkm_sp` (`MaChuongTrinhKM`, `MaSanPham`, `PhanTramGiamGia`) VALUES
(1, 2, 5.00),
(2, 4, 10.00),
(4, 4, 15.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHoaDon` int(11) NOT NULL,
  `MaSanPham` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `Ram` varchar(50) NOT NULL,
  `Rom` varchar(50) NOT NULL,
  `MauSac` varchar(50) NOT NULL,
  `DonGia` decimal(20,2) NOT NULL,
  `ThanhTien` decimal(20,2) DEFAULT NULL,
  `SoTienConLai` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHoaDon`, `MaSanPham`, `SoLuong`, `Ram`, `Rom`, `MauSac`, `DonGia`, `ThanhTien`, `SoTienConLai`) VALUES
(1, 2, 1, '12GB', '256GB', 'Đen', 21990000.00, 21990000.00, 20990000.00),
(2, 4, 1, '32GB', '1TB', 'Đỏ', 39990000.00, 39990000.00, 37990000.00),
(3, 2, 1, '12GB', '256GB', 'Đen', 24189000.00, 24189000.00, 23705220.00),
(4, 3, 1, '16GB', '512GB', 'Đỏ', 65989000.00, 65989000.00, 65329110.00),
(5, 2, 1, '12GB', '256GB', 'Đen', 24189000.00, 24189000.00, 23705220.00),
(7, 4, 1, '32GB', '1TB', 'Đỏ', 43989000.00, 43989000.00, 43549110.00),
(8, 2, 1, '12GB', '256GB', 'Đen', 24189000.00, 24189000.00, 23705220.00),
(9, 4, 1, '32GB', '1TB', 'Đỏ', 43989000.00, 43989000.00, 39590100.00),
(10, 2, 2, '12GB', '256GB', 'Đen', 24189000.00, 48378000.00, 45959100.00),
(10, 4, 1, '32GB', '1TB', 'Đỏ', 43989000.00, 43989000.00, 39590100.00),
(11, 1, 2, '12GB', '120GB', 'Trắng', 32989000.00, 65978000.00, 65978000.00),
(12, 1, 1, '12GB', '120GB', 'Trắng', 32989000.00, 32989000.00, 32989000.00),
(13, 1, 1, '12GB', '120GB', 'Trắng', 32989000.00, 32989000.00, 32989000.00),
(14, 1, 1, '12GB', '120GB', 'Trắng', 32989000.00, 32989000.00, 32989000.00),
(15, 3, 1, '16GB', '512GB', 'Đỏ', 65989000.00, 65989000.00, 65989000.00),
(16, 2, 2, '12GB', '256GB', 'Đen', 24189000.00, 48378000.00, 45959100.00),
(16, 4, 1, '32GB', '1TB', 'Đỏ', 43989000.00, 43989000.00, 39590100.00),
(17, 4, 1, '32GB', '1TB', 'Đỏ', 43989000.00, 43989000.00, 39590100.00),
(18, 2, 1, '12GB', '256GB', 'Đen', 24189000.00, 24189000.00, 22979550.00),
(19, 4, 1, '32GB', '1TB', 'Đỏ', 43989000.00, 43989000.00, 37390650.00),
(20, 4, 3, '32GB', '1TB', 'Đỏ', 43989000.00, 131967000.00, 112171950.00),
(21, 4, 2, '32GB', '1TB', 'Đỏ', 43989000.00, 87978000.00, 74781300.00),
(22, 3, 1, '16GB', '512GB', 'Đỏ', 65989000.00, 65989000.00, 65989000.00),
(23, 2, 17, '12GB', '256GB', 'Đen', 24189000.00, 411213000.00, 411213000.00),
(24, 1, 30, '12GB', '120GB', 'Trắng', 32989000.00, 989670000.00, 989670000.00),
(25, 2, 400, '12GB', '256GB', 'Đen', 24189000.00, 9675600000.00, 9675600000.00),
(25, 3, 323, '16GB', '512GB', 'Đỏ', 65989000.00, 21314447000.00, 21314447000.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `MaPhieuNhap` int(11) NOT NULL,
  `MaSanPham` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `Ram` varchar(50) NOT NULL,
  `Rom` varchar(50) NOT NULL,
  `MauSac` varchar(50) NOT NULL,
  `DonGia` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`MaPhieuNhap`, `MaSanPham`, `SoLuong`, `Ram`, `Rom`, `MauSac`, `DonGia`) VALUES
(1, 1, 10, '8GB', '256GB', 'Xanh', 29990000.00),
(1, 2, 5, '12GB', '256GB', 'Đen', 21990000.00),
(2, 4, 15, '32GB', '1TB', 'Đỏ', 39990000.00),
(13, 2, 40, '12GB', '256GB', 'Đen', 21990000.00),
(13, 4, 15, '32GB', '1TB', 'Đỏ', 39990000.00),
(14, 1, 50, '8GB', '256GB', 'Xanh', 29990000.00),
(14, 4, 15, '32GB', '1TB', 'Đỏ', 39990000.00),
(15, 1, 50, '8GB', '256GB', 'Xanh', 29990000.00),
(15, 3, 20, '16GB', '512GB', 'Xanh', 59990000.00),
(16, 2, 40, '12GB', '256GB', 'Đen', 21990000.00),
(16, 4, 15, '32GB', '1TB', 'Đỏ', 39990000.00),
(17, 1, 100, '2GB', '32GB', 'Đỏ', 29990000.00),
(18, 1, 2, '4GB', '64GB', 'Đen', 29990000.00),
(19, 2, 2, '2GB', '32GB', 'Đỏ', 21990000.00),
(20, 4, 30, '32GB', '64GB', 'Vàng', 39990000.00),
(21, 2, 82, '12GB', '256GB', 'Đen', 21990000.00),
(22, 1, 14, '8GB', '120GB', 'Trắng', 23000000.00),
(22, 1, 202, '8GB', '256GB', 'Đen', 3546.00),
(22, 2, 11, '16GB', '256GB', 'Đen', 1600000.00),
(22, 3, 12, '8GB', '512GB', 'Đỏ', 20000000.00),
(22, 4, 13, '32GB', '1TB', 'Đỏ', 54000000.00),
(24, 1, 10, '12GB', '120GB', 'Trắng', 32000000.00),
(24, 1, 14, '8GB', '120GB', 'Trắng', 23000000.00),
(24, 2, 11, '16GB', '256GB', 'Đen', 1600000.00),
(24, 2, 15, '8GB', '250GB', 'Đen', 43000000.00),
(24, 3, 12, '8GB', '512GB', 'Đỏ', 20000000.00),
(24, 3, 75, '8GB', '64GB', 'Xanh', 59990000.00),
(24, 4, 13, '32GB', '1TB', 'Đỏ', 54000000.00),
(25, 4, 172, '32GB', '1TB', 'Đỏ', 39990000.00),
(26, 2, 2, '12GB', '256GB', 'Đen', 21990000.00),
(27, 2, 207, '12GB', '256GB', 'Đen', 21990000.00),
(28, 3, 160, '6GB', '120GB', 'Xanh', 59990000.00),
(29, 3, 2, '16GB', '512GB', 'Đỏ', 59990000.00),
(30, 4, 20, '32GB', '1TB', 'Đỏ', 39990000.00),
(31, 2, 4, '12GB', '256GB', 'Đen', 21990000.00),
(32, 1, 4, '8GB', '1TB', 'Đỏ', 29990000.00),
(33, 3, 2, '16GB', '512GB', 'Đỏ', 59990000.00);

-- --------------------------------------------------------

--
-- Table structure for table `chitietsanpham`
--

CREATE TABLE `chitietsanpham` (
  `MaSanPham` int(11) NOT NULL,
  `MaHang` int(11) DEFAULT NULL,
  `Chip` varchar(100) DEFAULT NULL,
  `Ram` varchar(50) NOT NULL,
  `Rom` varchar(50) NOT NULL,
  `INCH` varchar(10) DEFAULT NULL,
  `DungLuongPin` varchar(50) DEFAULT NULL,
  `MauSac` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chitietsanpham`
--

INSERT INTO `chitietsanpham` (`MaSanPham`, `MaHang`, `Chip`, `Ram`, `Rom`, `INCH`, `DungLuongPin`, `MauSac`) VALUES
(1, 1, 'A17 Bionic', '12GB', '120GB', '6.1\"', '3200mAh', 'Trắng'),
(1, 1, 'A17 Bionic', '8GB', '120GB', '6.1\"', '3200mAh', 'Trắng'),
(1, 1, 'A17 Bionic', '8GB', '1TB', '6.1\"', '3200mAh', 'Đỏ'),
(1, 1, 'A17 Bionic', '8GB', '256GB', '6.1\"', '3200mAh', 'Xanh'),
(1, 1, 'A17 Bionic', '8GB', '256GB', '6.1\"', '3200mAh', 'Đen'),
(2, 2, 'Snapdragon 8 Gen 2', '12GB', '256GB', '6.5\"', '4500mAh', 'Đen'),
(2, 2, 'Snapdragon 8 Gen 2', '16GB', '256GB', '6.5\"', '4500mAh', 'Đen'),
(2, 2, 'Snapdragon 8 Gen 2', '8GB', '250GB', '6.5\"', '4500mAh', 'Đen'),
(3, 1, 'M2 Pro', '16GB', '512GB', '16\"', '100Wh', 'Bạc'),
(3, 1, 'M2 Pro', '16GB', '512GB', '16\"', '100Wh', 'Đỏ'),
(3, 1, 'M2 Pro', '6GB', '120GB', '16\"', '100Wh', 'Xanh'),
(3, 1, 'M2 Pro', '8GB', '512GB', '16\"', '100Wh', 'Đỏ'),
(3, 1, 'M2 Pro', '8GB', '64GB', '16\"', '100Wh', 'Xanh'),
(4, 4, 'Ryzen 9', '32GB', '1TB', '15.6\"', '90Wh', 'Đỏ');

-- --------------------------------------------------------

--
-- Table structure for table `chuongtrinhkm`
--

CREATE TABLE `chuongtrinhkm` (
  `MaChuongTrinhKM` int(11) NOT NULL,
  `NgayBatDau` date NOT NULL,
  `NgayKetThuc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chuongtrinhkm`
--

INSERT INTO `chuongtrinhkm` (`MaChuongTrinhKM`, `NgayBatDau`, `NgayKetThuc`) VALUES
(1, '2024-03-01', '2024-03-31'),
(2, '2024-04-01', '2024-04-30'),
(4, '2025-04-05', '2025-06-07');

-- --------------------------------------------------------

--
-- Table structure for table `hang`
--

CREATE TABLE `hang` (
  `MaHang` int(11) NOT NULL,
  `TenHang` varchar(255) NOT NULL,
  `QuocGia` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hang`
--

INSERT INTO `hang` (`MaHang`, `TenHang`, `QuocGia`) VALUES
(1, 'Apple', 'Mỹ'),
(2, 'Samsung', 'Hàn Quốc'),
(3, 'Dell', 'Mỹ'),
(4, 'Asus', 'Đài Loan');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHoaDon` int(11) NOT NULL,
  `NgayLapHoaDon` date NOT NULL,
  `MaNhanVien` int(11) DEFAULT NULL,
  `MaKhachHang` int(11) DEFAULT NULL,
  `TongTien` decimal(20,2) DEFAULT NULL,
  `TongTienPhaiTra` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MaHoaDon`, `NgayLapHoaDon`, `MaNhanVien`, `MaKhachHang`, `TongTien`, `TongTienPhaiTra`) VALUES
(1, '2024-03-22', 2, 1, 21990000.00, 20990000.00),
(2, '2024-03-23', 3, 2, 39990000.00, 37990000.00),
(3, '2025-04-13', 1, 1, 24189000.00, 23705220.00),
(4, '2025-04-17', 1, 1, 65989000.00, 65329110.00),
(5, '2025-04-23', 1, 1, 24189000.00, 23705220.00),
(7, '2025-04-23', 1, 1, 43989000.00, 43549110.00),
(8, '2025-04-24', 1, 1, 24189000.00, 23705220.00),
(9, '2025-05-04', 1, 2, 125368650.00, 188052975.00),
(10, '2025-05-04', 1, 1, 87748650.00, 124932885.00),
(11, '2025-05-04', 1, 2, 65978000.00, 131956000.00),
(12, '2025-05-04', 1, 1, 32989000.00, 32989000.00),
(13, '2025-05-04', 1, 2, 32989000.00, 32989000.00),
(14, '2025-05-04', 1, 1, 32989000.00, 32989000.00),
(15, '2025-05-04', 1, 1, 65989000.00, 65989000.00),
(16, '2025-05-04', 1, 1, 87748650.00, 124932885.00),
(17, '2025-05-07', 1, 2, 43989000.00, 39590100.00),
(18, '2025-05-07', 1, 2, 24189000.00, 22979550.00),
(19, '2025-05-07', 1, 1, 43989000.00, 37390650.00),
(20, '2025-05-07', 1, 2, 118770300.00, 302864265.00),
(21, '2025-05-08', 3, 2, 79180200.00, 134606340.00),
(22, '2025-05-08', 2, 2, 65989000.00, 65989000.00),
(23, '2025-05-08', 1, 2, 411213000.00, 6990621000.00),
(24, '2025-05-08', 1, 1, 989670000.00, 29690100000.00),
(25, '2025-05-08', 1, 2, 27891042300.00, 9679325742900.00);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKhachHang` int(11) NOT NULL,
  `HoTenKhachHang` varchar(255) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `SoDienThoai` varchar(20) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MaKhachHang`, `HoTenKhachHang`, `DiaChi`, `SoDienThoai`, `Email`) VALUES
(1, 'Phạm Thị D', 'Hà Nội', '0912345678', 'phamthid@gmail.com'),
(2, 'Ngô Văn E', 'TPHCM', '0976543210', 'ngovane@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `MaLoai` int(11) NOT NULL,
  `TenLoai` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`MaLoai`, `TenLoai`) VALUES
(1, 'Điện thoại'),
(2, 'Laptop'),
(3, 'Máy tính bảng');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MaNhaCungCap` int(11) NOT NULL,
  `TenNhaCungCap` varchar(255) NOT NULL,
  `DiaChi` text DEFAULT NULL,
  `SoDienThoai` varchar(20) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MaNhaCungCap`, `TenNhaCungCap`, `DiaChi`, `SoDienThoai`, `Email`) VALUES
(1, 'Công ty Apple Việt Nam', 'Hà Nội', '0123456789', 'applevn@gmail.com'),
(2, 'Samsung Việt Nam', 'TPHCM', '0987654321', 'samsungvn@gmail.com'),
(3, 'Oppo Trung Quốc', '123 Tân Cương', '12345678', 'Oppo@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL,
  `HoTen` varchar(255) NOT NULL,
  `ChucVu` varchar(100) DEFAULT NULL,
  `NgayVaoLam` date DEFAULT NULL,
  `Luong` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `HoTen`, `ChucVu`, `NgayVaoLam`, `Luong`) VALUES
(1, 'Nguyễn Văn A', 'Quản lý', '2020-05-10', 30000000.00),
(2, 'Trần Thị B', 'Nhân viên bán hàng', '2021-07-15', 15000000.00),
(3, 'Lê Văn C', 'Nhân viên kho', '2019-12-01', 12000000.00);

-- --------------------------------------------------------

--
-- Table structure for table `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `MaNhomQuyen` int(11) NOT NULL,
  `TenQuyen` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhomquyen`
--

INSERT INTO `nhomquyen` (`MaNhomQuyen`, `TenQuyen`) VALUES
(1, 'Admin'),
(2, 'NhanVienQuanLy');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPhieuNhap` int(11) NOT NULL,
  `MaNhanVien` int(11) DEFAULT NULL,
  `MaNhaCungCap` int(11) DEFAULT NULL,
  `NgayNhap` date DEFAULT NULL,
  `TongTien` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`MaPhieuNhap`, `MaNhanVien`, `MaNhaCungCap`, `NgayNhap`, `TongTien`) VALUES
(1, 1, 1, '2024-03-20', 500000000.00),
(2, 2, 2, '2024-03-21', 300000000.00),
(12, 1, 1, '2025-04-07', 2379100000.00),
(13, 1, 1, '2025-04-07', 1479450000.00),
(14, 1, 1, '2025-04-07', 2099350000.00),
(15, 1, 1, '2025-04-07', 2699300000.00),
(16, 1, 1, '2025-04-07', 1479450000.00),
(17, 1, 1, '2025-04-07', 2999000000.00),
(18, 1, 1, '2025-04-07', 59980000.00),
(19, 2, 1, '2025-04-07', 43980000.00),
(20, 1, 1, '2025-04-07', 1199700000.00),
(21, 2, 1, '2025-04-07', 1803180000.00),
(22, 1, 1, '2025-04-14', 1282317100.00),
(24, 1, 1, '2025-04-23', 6745850000.00),
(25, 1, 2, '2025-04-23', 6878280000.00),
(26, 1, 3, '2025-04-23', 43980000.00),
(27, 1, 2, '2025-05-04', 4551930000.00),
(28, 1, 3, '2025-05-07', 9598400000.00),
(29, 3, 2, '2025-05-07', 119980000.00),
(30, 2, 2, '2025-05-07', 799800000.00),
(31, 3, 2, '2025-05-08', 87960000.00),
(32, 1, 3, '2025-05-08', 119960000.00),
(33, 3, 3, '2025-05-08', 119980000.00);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSanPham` int(11) NOT NULL,
  `TenSanPham` varchar(255) NOT NULL,
  `SoLuong` int(11) DEFAULT 0,
  `DonGia` decimal(20,2) NOT NULL,
  `DonViTinh` varchar(50) DEFAULT NULL,
  `HinhAnh` varchar(255) DEFAULT NULL,
  `MaLoai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MaSanPham`, `TenSanPham`, `SoLuong`, `DonGia`, `DonViTinh`, `HinhAnh`, `MaLoai`) VALUES
(1, 'iPhone 15 Pro', 0, 29990000.00, 'Chiếc', 'iPhone 15 Pro.jpg', 1),
(2, 'Samsung Galaxy S23', 0, 21990000.00, 'Cái', 'samsungs23.jpg', 1),
(3, 'MacBook Pro 16', 0, 59990000.00, 'Cái', 'macbookpro16.jpg', 2),
(4, 'Asus ROG Zephyrus', 0, 39990000.00, 'Chiếc', 'Asus ROG Zephyrus.jpg', 2),
(6, 'đ', 0, 3333.00, 'Chiếc', 'C:\\Users\\Admin\\Pictures\\Acer\\Acer_Wallpaper_01_3840x2400.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `MaUser` int(11) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `PasswordHash` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `HoTen` varchar(255) NOT NULL,
  `NgayTao` timestamp NOT NULL DEFAULT current_timestamp(),
  `MaNhomQuyen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`MaUser`, `Username`, `PasswordHash`, `Email`, `HoTen`, `NgayTao`, `MaNhomQuyen`) VALUES
(1, 'admin', 'hashed_password_here', 'admin@example.com', 'Administrator', '2025-04-13 09:51:44', 1),
(2, 'manager1', 'hashed_password_here', 'manager1@example.com', 'Nguyen Van A', '2025-04-13 09:51:44', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietctkm_hd`
--
ALTER TABLE `chitietctkm_hd`
  ADD PRIMARY KEY (`MaChuongTrinhKM`);

--
-- Indexes for table `chitietctkm_sp`
--
ALTER TABLE `chitietctkm_sp`
  ADD PRIMARY KEY (`MaChuongTrinhKM`,`MaSanPham`),
  ADD KEY `MaSanPham` (`MaSanPham`);

--
-- Indexes for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHoaDon`,`MaSanPham`,`Ram`,`Rom`,`MauSac`),
  ADD KEY `MaSanPham` (`MaSanPham`);

--
-- Indexes for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`MaPhieuNhap`,`MaSanPham`,`Ram`,`Rom`,`MauSac`),
  ADD KEY `MaSanPham` (`MaSanPham`);

--
-- Indexes for table `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD PRIMARY KEY (`MaSanPham`,`Ram`,`Rom`,`MauSac`),
  ADD KEY `MaHang` (`MaHang`);

--
-- Indexes for table `chuongtrinhkm`
--
ALTER TABLE `chuongtrinhkm`
  ADD PRIMARY KEY (`MaChuongTrinhKM`);

--
-- Indexes for table `hang`
--
ALTER TABLE `hang`
  ADD PRIMARY KEY (`MaHang`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHoaDon`),
  ADD KEY `MaNhanVien` (`MaNhanVien`),
  ADD KEY `MaKhachHang` (`MaKhachHang`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKhachHang`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MaNhaCungCap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Indexes for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`MaNhomQuyen`),
  ADD UNIQUE KEY `TenQuyen` (`TenQuyen`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPhieuNhap`),
  ADD KEY `MaNhanVien` (`MaNhanVien`),
  ADD KEY `MaNhaCungCap` (`MaNhaCungCap`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSanPham`),
  ADD KEY `MaLoai` (`MaLoai`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`MaUser`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `MaNhomQuyen` (`MaNhomQuyen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chuongtrinhkm`
--
ALTER TABLE `chuongtrinhkm`
  MODIFY `MaChuongTrinhKM` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hang`
--
ALTER TABLE `hang`
  MODIFY `MaHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHoaDon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKhachHang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `MaNhaCungCap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `MaNhomQuyen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `MaPhieuNhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `MaSanPham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `MaUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietctkm_hd`
--
ALTER TABLE `chitietctkm_hd`
  ADD CONSTRAINT `chitietctkm_hd_ibfk_1` FOREIGN KEY (`MaChuongTrinhKM`) REFERENCES `chuongtrinhkm` (`MaChuongTrinhKM`) ON DELETE CASCADE;

--
-- Constraints for table `chitietctkm_sp`
--
ALTER TABLE `chitietctkm_sp`
  ADD CONSTRAINT `chitietctkm_sp_ibfk_1` FOREIGN KEY (`MaChuongTrinhKM`) REFERENCES `chuongtrinhkm` (`MaChuongTrinhKM`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitietctkm_sp_ibfk_2` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE CASCADE;

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHoaDon`) REFERENCES `hoadon` (`MaHoaDon`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE CASCADE;

--
-- Constraints for table `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap` (`MaPhieuNhap`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE CASCADE;

--
-- Constraints for table `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD CONSTRAINT `chitietsanpham_ibfk_1` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitietsanpham_ibfk_2` FOREIGN KEY (`MaHang`) REFERENCES `hang` (`MaHang`) ON DELETE SET NULL;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE SET NULL,
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaKhachHang`) REFERENCES `khachhang` (`MaKhachHang`) ON DELETE SET NULL;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE SET NULL,
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNhaCungCap`) REFERENCES `nhacungcap` (`MaNhaCungCap`) ON DELETE SET NULL;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaisanpham` (`MaLoai`) ON DELETE SET NULL;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`MaNhomQuyen`) REFERENCES `nhomquyen` (`MaNhomQuyen`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
