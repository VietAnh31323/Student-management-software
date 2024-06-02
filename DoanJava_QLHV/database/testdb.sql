-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 16, 2024 lúc 02:30 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `testdb`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bangdiem`
--

CREATE TABLE `bangdiem` (
  `maHocVien` int(11) NOT NULL,
  `Lop` varchar(255) NOT NULL,
  `diemKT1` double NOT NULL,
  `diemKT2` double NOT NULL,
  `diemKT3` double NOT NULL,
  `diemTB` double NOT NULL,
  `Danhgia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `bangdiem`
--

INSERT INTO `bangdiem` (`maHocVien`, `Lop`, `diemKT1`, `diemKT2`, `diemKT3`, `diemTB`, `Danhgia`) VALUES
(1001, 'A1', 3, 5, 5, 4.33, 'Không Đạt'),
(1002, 'A1', 4, 3, 5, 4, 'Không Đạt'),
(1003, 'A2', 7, 10, 4, 7, 'Đạt'),
(1004, 'A2', 8, 6, 10, 8, 'Đạt'),
(1005, 'A2', 4, 10, 9, 7.67, 'Đạt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichhoc`
--

CREATE TABLE `lichhoc` (
  `id` varchar(255) NOT NULL,
  `tenmonhoc` varchar(255) NOT NULL,
  `tengiangvien` varchar(255) NOT NULL,
  `thoigian` varchar(255) NOT NULL,
  `diadiem` varchar(255) NOT NULL,
  `Lop` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lichhoc`
--

INSERT INTO `lichhoc` (`id`, `tenmonhoc`, `tengiangvien`, `thoigian`, `diadiem`, `Lop`) VALUES
('1', 'Reading 1', 'Lương Thị Bích Phượng', '8h30-12/1/2021', 'H-H.501', 'A1'),
('2', 'Listening 2', 'Nguyễn Việt Anh', '14h30-15/1/2024', 'H-H.102', 'A3'),
('3', 'Speaking 2', 'Đỗ Hồng An', '14h30-23/1/2024', 'H-H102', 'B1'),
('4', 'Practice questions', 'Nguyễn Chu Kiều Trang', '8h30-20/1/2024', 'H-H.303', 'A1'),
('5', 'Writing 1', 'Nguyễn Thị Tâm Nhi', '8h30-19/1/2024', 'H-H.101', 'A4');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `qlhv`
--

CREATE TABLE `qlhv` (
  `maKhoaHoc` varchar(255) NOT NULL,
  `maHocVien` int(255) NOT NULL,
  `tenHocVien` varchar(255) NOT NULL,
  `trinhDo` varchar(255) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `ngaySinh` date NOT NULL,
  `gioiTinh` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `lop` varchar(50) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `diemKT1` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `qlhv`
--

INSERT INTO `qlhv` (`maKhoaHoc`, `maHocVien`, `tenHocVien`, `trinhDo`, `diaChi`, `ngaySinh`, `gioiTinh`, `email`, `lop`, `phone`, `diemKT1`) VALUES
('EA1', 1001, 'Nguyễn Việt Anh', '1', 'An Tường, Tuyên Quang', '2003-03-31', '1', 'nvietanh170@gmail.com', 'A1', '0982848203', 56),
('EA1', 1002, 'Nguyễn Chu Kiều Trang', '4', 'Nguyên Hồng, Hà Nội', '2003-12-30', '0', 'nckieutrang@gmail.com', 'A2', '0985482450', 90),
('EA1', 1003, 'Nguyễn Thị Tâm Nhi', '4', 'Hoài Đức, Hà Nội', '2003-05-09', '0', 'nttamnhi0905@gmail.com', 'B1', '0987546123', 80);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('NguyenVanh', '123456789');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bangdiem`
--
ALTER TABLE `bangdiem`
  ADD PRIMARY KEY (`maHocVien`);

--
-- Chỉ mục cho bảng `lichhoc`
--
ALTER TABLE `lichhoc`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `qlhv`
--
ALTER TABLE `qlhv`
  ADD PRIMARY KEY (`maHocVien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
