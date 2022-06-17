-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 02, 2022 at 03:12 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Library`
--

-- --------------------------------------------------------

--
-- Table structure for table `tChiTienHoaDon`
--

CREATE TABLE `tChiTienHoaDon` (
  `SoHoaDon` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `GhiChu` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tChiTienHoaDon`
--

INSERT INTO `tChiTienHoaDon` (`SoHoaDon`, `MaSach`, `SoLuong`, `GhiChu`, `id`) VALUES
(1, 1, 2, 'về HN', 1),
(1, 2, 1, 'Về CHùa', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tDaiLy`
--

CREATE TABLE `tDaiLy` (
  `MaDaiLy` int(11) NOT NULL,
  `TenDaiLy` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `TenChuDaiLy` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `DiaChi` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tDaiLy`
--

INSERT INTO `tDaiLy` (`MaDaiLy`, `TenDaiLy`, `TenChuDaiLy`, `DiaChi`, `SoDienThoai`) VALUES
(1, 'FPT', 'Thanh 1', 'HN', '0834577'),
(3, 'EMP', 'manh', 'QN', '0238476732');

-- --------------------------------------------------------

--
-- Table structure for table `tHoaDon`
--

CREATE TABLE `tHoaDon` (
  `SoHoaDon` int(11) NOT NULL,
  `NgayLapHoaDon` date DEFAULT NULL,
  `MaDaiLy` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tHoaDon`
--

INSERT INTO `tHoaDon` (`SoHoaDon`, `NgayLapHoaDon`, `MaDaiLy`) VALUES
(1, '2022-04-30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tNhaXuatBan`
--

CREATE TABLE `tNhaXuatBan` (
  `MaNhaXuatBan` int(11) NOT NULL,
  `TenNhaXuatBan` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tNhaXuatBan`
--

INSERT INTO `tNhaXuatBan` (`MaNhaXuatBan`, `TenNhaXuatBan`, `DiaChi`, `SoDienThoai`) VALUES
(1, 'Kim Dong', 'Nghệ An', '02384767'),
(2, 'Kim Dong 2', 'Quảng Ninh', '0385613085');

-- --------------------------------------------------------

--
-- Table structure for table `tSach`
--

CREATE TABLE `tSach` (
  `MaSach` int(11) NOT NULL,
  `TenSach` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `TenTacGia` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `GiaBan` double DEFAULT NULL,
  `GiaBanChoDaiLy` double DEFAULT NULL,
  `MaNhaXuatBan` int(11) DEFAULT NULL,
  `SoTrang` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tSach`
--

INSERT INTO `tSach` (`MaSach`, `TenSach`, `TenTacGia`, `GiaBan`, `GiaBanChoDaiLy`, `MaNhaXuatBan`, `SoTrang`) VALUES
(1, 'Song gio', 'kim', 1200, 100, 1, 30),
(2, 'toan', 'huy', 1300, 200, 2, 50);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tChiTienHoaDon`
--
ALTER TABLE `tChiTienHoaDon`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tChiTienHoaDon_tHoaDon_SoHoaDon_fk` (`SoHoaDon`),
  ADD KEY `tChiTienHoaDon_tSach_MaSach_fk` (`MaSach`);

--
-- Indexes for table `tDaiLy`
--
ALTER TABLE `tDaiLy`
  ADD PRIMARY KEY (`MaDaiLy`);

--
-- Indexes for table `tHoaDon`
--
ALTER TABLE `tHoaDon`
  ADD PRIMARY KEY (`SoHoaDon`),
  ADD KEY `tHoaDon_tDaiLy_MaDaiLy_fk` (`MaDaiLy`);

--
-- Indexes for table `tNhaXuatBan`
--
ALTER TABLE `tNhaXuatBan`
  ADD PRIMARY KEY (`MaNhaXuatBan`);

--
-- Indexes for table `tSach`
--
ALTER TABLE `tSach`
  ADD PRIMARY KEY (`MaSach`),
  ADD KEY `tSach_tNhaXuatBan_MaNhaXuatBan_fk` (`MaNhaXuatBan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tChiTienHoaDon`
--
ALTER TABLE `tChiTienHoaDon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tDaiLy`
--
ALTER TABLE `tDaiLy`
  MODIFY `MaDaiLy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tHoaDon`
--
ALTER TABLE `tHoaDon`
  MODIFY `SoHoaDon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tNhaXuatBan`
--
ALTER TABLE `tNhaXuatBan`
  MODIFY `MaNhaXuatBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tSach`
--
ALTER TABLE `tSach`
  MODIFY `MaSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tChiTienHoaDon`
--
ALTER TABLE `tChiTienHoaDon`
  ADD CONSTRAINT `tChiTienHoaDon_tHoaDon_SoHoaDon_fk` FOREIGN KEY (`SoHoaDon`) REFERENCES `tHoaDon` (`SoHoaDon`),
  ADD CONSTRAINT `tChiTienHoaDon_tSach_MaSach_fk` FOREIGN KEY (`MaSach`) REFERENCES `tSach` (`MaSach`);

--
-- Constraints for table `tHoaDon`
--
ALTER TABLE `tHoaDon`
  ADD CONSTRAINT `tHoaDon_tDaiLy_MaDaiLy_fk` FOREIGN KEY (`MaDaiLy`) REFERENCES `tDaiLy` (`MaDaiLy`);

--
-- Constraints for table `tSach`
--
ALTER TABLE `tSach`
  ADD CONSTRAINT `tSach_tNhaXuatBan_MaNhaXuatBan_fk` FOREIGN KEY (`MaNhaXuatBan`) REFERENCES `tNhaXuatBan` (`MaNhaXuatBan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
