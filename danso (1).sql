-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 28, 2021 lúc 05:10 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `danso`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hokhau`
--

CREATE TABLE `hokhau` (
  `id` int(10) UNSIGNED NOT NULL,
  `idho` int(20) DEFAULT NULL,
  `hotenchu` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diachi` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ghichu` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `placeid` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hokhau`
--

INSERT INTO `hokhau` (`id`, `idho`, `hotenchu`, `diachi`, `ghichu`, `placeid`) VALUES
(1, 324234, 'Nguyễn Văn Trí', '4 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(2, 645564, 'Đặng Thai Mai', '7 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(3, 343423, 'Nguyễn Thành An', '67 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(4, 245343, 'Thái Linh Hương', '3 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(5, 435453, 'Nguyễn Kiều Anh', '34 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(6, 132332, 'Lê Văn Mạnh', '23 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(7, 234349, 'Đỗ Quốc Bảo', '45 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(8, 345454, 'Đinh Hồng Lĩnh', '9 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL),
(9, 3242344, 'Lê Duy Thành', 'fdgf', NULL, NULL),
(10, 435344, 'Lê Duy Thái', 'Ngõ Tân Lạc, Trương Định, Hai Bà Trưng District, Hanoi, Vietnam', NULL, 'EkpOZ8O1IFTDom4gTOG6oWMsIFRyxrDGoW5nIMSQ4buLbmgsIEhhaSBCw6AgVHLGsG5nIERpc3RyaWN0LCBIYW5vaSwgVmlldG5hbSIuKiwKFAoSCScShwVyrDUxEYUonM4YUFufEhQKEglrcfiMbaw1MRF-bgMfkh7qiQ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhankhau`
--

CREATE TABLE `nhankhau` (
  `id` int(10) UNSIGNED NOT NULL,
  `idho` int(20) DEFAULT NULL,
  `quanhech` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hoten` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gioitinh` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngaysinh` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noisinh` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nguyenquan` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dantoc` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nghenghiep` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noilamviec` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmnd` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngaycap` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noicap` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ndkthuongtru` varchar(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dcthuongtrutrc` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ghichu` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhankhau`
--

INSERT INTO `nhankhau` (`id`, `idho`, `quanhech`, `hoten`, `gioitinh`, `ngaysinh`, `noisinh`, `nguyenquan`, `dantoc`, `nghenghiep`, `noilamviec`, `cmnd`, `ngaycap`, `noicap`, `ndkthuongtru`, `dcthuongtrutrc`, `ghichu`) VALUES
(1, 324234, 'Chủ', 'Nguyễn Văn Trí', 'Nam', '01/01/1999', '4 La Khê, Hà Đông, Hà Nội, Việt Nam', '4 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Công Nhân', 'Hà Nội', '64345523', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(2, 645564, 'Chủ', 'Đặng Thai Mai', 'Nam', '03/01/2000', '7 La Khê, Hà Đông, Hà Nội, Việt Nam', '7 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Làm thơ', 'Hà Nội', '43545634', '1/1/2019', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(3, 343423, 'Chủ', 'Nguyễn Thành An', 'Nam', '10/01/1998', '67 La Khê, Hà Đông, Hà Nội, Việt Nam', '67 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Làm văn', 'Hà Nội', '45436565', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(4, 245343, 'Chủ', 'Thái Linh Hương', 'Nữ', '10/10/2000', '3 La Khê, Hà Đông, Hà Nội, Việt Nam', '3 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Game thủ', 'Hà Nội', '34342333', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(5, 435453, 'Chủ', 'Nguyễn Kiều Anh', 'Nữ', '02/01/1998', '34 La Khê, Hà Đông, Hà Nội, Việt Nam', '34 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Ăn xin', 'Hà Nội', '24247878', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(6, 132332, 'Chủ', 'Lê Văn Mạnh', 'Nam', '22/12/1998', '23 La Khê, Hà Đông, Hà Nội, Việt Nam', '23 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Lái xe', 'Hà Nội', '24242345', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(7, 234349, 'Chủ', 'Đỗ Quốc Bảo', 'Nam', '11/12/2000', '45 La Khê, Hà Đông, Hà Nội, Việt Nam', '45 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Thất Nghiệp', NULL, '56456767', '1/1/2020', 'Hà Nội', NULL, NULL, NULL),
(8, 345454, 'Chủ', 'Đinh Hồng Lĩnh', 'Nữ', '12/11/2000', '9 La Khê, Hà Đông, Hà Nội, Việt Nam', '9 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Giáo viên', 'Thanh Hóa', '34324343', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, NULL, NULL),
(9, 3242344, 'Chủ', 'Lê Duy Thành', 'Nam', '19/05/2021', 'Thanh Hóa', 'Thanh Hóa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 435344, 'Chủ', 'Lê Duy Thái', 'Nam', '19/05/2021', 'Thanh Hóa', 'Thanh Hóa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 324234, 'Người thân', 'Hoài', 'Nam', '04/05/1994', 't', 't', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 3242344, 'Người thân', 'dsf', 'Nam', '24/05/2021', 'fa', 'fa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 435344, 'Người thân', 't', 'Nam', '24/05/2021', 't', 't', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, 324234, 'Người thân', 'fdg', 'Nam', '24/05/1900', 'hgj', 'hgj', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, 0, NULL, 'tùng', 'Nam', '29/05/2021', 'dfg', NULL, NULL, NULL, NULL, '4534', NULL, NULL, NULL, NULL, NULL);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hokhau`
--
ALTER TABLE `hokhau`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hokhau`
--
ALTER TABLE `hokhau`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
