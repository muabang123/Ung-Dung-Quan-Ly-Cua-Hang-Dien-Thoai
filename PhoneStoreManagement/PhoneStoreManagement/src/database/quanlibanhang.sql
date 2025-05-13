-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: May 13, 2025 at 10:22 AM
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

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD PRIMARY KEY (`MaSanPham`,`Ram`,`Rom`,`MauSac`),
  ADD KEY `MaHang` (`MaHang`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD CONSTRAINT `chitietsanpham_ibfk_1` FOREIGN KEY (`MaSanPham`) REFERENCES `sanpham` (`MaSanPham`) ON DELETE CASCADE,
  ADD CONSTRAINT `chitietsanpham_ibfk_2` FOREIGN KEY (`MaHang`) REFERENCES `hang` (`MaHang`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
