-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 02, 2021 lúc 11:14 AM
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
(1, 324234, 'Nguyễn Văn Trí', '4 La Khê, Hà Đông, Hà Nội, Việt Nam', 'null', 'ChIJRRba-d9SNDEROxGId9BXvQQ'),
(2, 645564, 'Đặng Thai Mai', '7 La Khê, Hà Đông, Hà Nội, Việt Nam', 'null', 'EjBOZ8O1IDcsIExhIEtow6osIEjDoCDEkMO0bmcsIEjDoCBO4buZaSwgVmlldCBOYW0iLiosChQKEgkHmOA63FI0MRF3qR2Re4ZVUBIUChIJRRba-d9SNDEROxGId9BXvQQ'),
(3, 343423, 'Nguyễn Thành An', '67 La Khê, Hà Đông, Hà Nội, Việt Nam', 'null', 'ChIJRRba-d9SNDEROxGId9BXvQQ'),
(4, 245343, 'Thái Linh Hương', '3 La Khê, Hà Đông, Hà Nội, Việt Nam', 'null', 'ChIJRRba-d9SNDEROxGId9BXvQQ'),
(5, 435453, 'Nguyễn Kiều Anh', '34 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, ''),
(6, 132332, 'Lê Văn Mạnh', '23 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, ''),
(7, 234349, 'Đỗ Quốc Bảo', '45 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, ''),
(8, 345454, 'Đinh Hồng Lĩnh', '9 La Khê, Hà Đông, Hà Nội, Việt Nam', NULL, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichsh`
--

CREATE TABLE `lichsh` (
  `stt` int(10) UNSIGNED NOT NULL,
  `NgayThang` varchar(20) NOT NULL,
  `DiaDiem` varchar(50) NOT NULL,
  `NoiDung` varchar(200) NOT NULL,
  `ThongBao` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `lichsh`
--

INSERT INTO `lichsh` (`stt`, `NgayThang`, `DiaDiem`, `NoiDung`, `ThongBao`) VALUES
(9, '10/06/2021', 'Nhà văn hóa', 'Bàn luận, giải quyết phản ánh, kiến nghị', 'Cộng hòa xã hội chủ nghĩa Việt Nam\nĐộc lập - Tự do - Hạnh phúc\n--------------\nTHÔNG BÁO HỌP TỔ DÂN PHỐ\n\n\nÔng/bà:...............................................................\nNội dung buổi họp: Bàn luận, giải quyết phản ánh, kiến nghị\nVào ....h ngày 10/06/2021 tại: Nhà văn hóa\n\nKính mời!					');

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
(8, 345454, 'Chủ', 'Đinh Hồng Lĩnh', 'Nữ', '12/11/2000', '99 La Khê, Hà Đông, Hà Nội, Việt Nam', '9 La Khê, Hà Đông, Hà Nội, Việt Nam', 'Kinh', 'Giáo viên', 'Thanh Hóa', '34324343', '1/1/2020', 'La Khê, Hà Đông, Hà Nội, Việt Nam', 'null', 'null', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `noidungphananh`
--

CREATE TABLE `noidungphananh` (
  `id` int(11) NOT NULL,
  `hoten` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sdt` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diachi` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngay` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phanloai` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noidung` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `daxem` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `noidungphananh`
--

INSERT INTO `noidungphananh` (`id`, `hoten`, `sdt`, `diachi`, `ngay`, `phanloai`, `noidung`, `daxem`) VALUES
(1, 'Nguyễn Thị Ánh', '0349556546', 'Hà nội', '01/06/2021', 'Tố cáo', 'tố cáo hoài linh ăn chặn tiền ', 'Đã xem'),
(2, 'Lê Hoàng', '0345446565', 'Hà Nội', '02/06/2021', 'Khiếu nại', 'Hoài Linh vẫn chưa giải ngân tiền', 'Chưa xem');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `suckhoenguoidan`
--

CREATE TABLE `suckhoenguoidan` (
  `id` int(10) UNSIGNED NOT NULL,
  `idNhanKhau` int(10) UNSIGNED NOT NULL,
  `idHoKhau` int(20) UNSIGNED NOT NULL,
  `hoVaTen` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gioiTinh` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngaySinh` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cmnd_cccd` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tinhTrang` int(1) NOT NULL DEFAULT 4,
  `nguonLay` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noiCachLy` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayBatDauCachLy` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `noiDieuTri` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ngayBatDauDieuTri` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ketQuaTest1` int(1) NOT NULL DEFAULT -1,
  `ketQuaTest2` int(1) NOT NULL DEFAULT -1,
  `ketQuaTest3` int(1) NOT NULL DEFAULT -1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `suckhoenguoidan`
--

INSERT INTO `suckhoenguoidan` (`id`, `idNhanKhau`, `idHoKhau`, `hoVaTen`, `gioiTinh`, `ngaySinh`, `cmnd_cccd`, `tinhTrang`, `nguonLay`, `noiCachLy`, `ngayBatDauCachLy`, `noiDieuTri`, `ngayBatDauDieuTri`, `ketQuaTest1`, `ketQuaTest2`, `ketQuaTest3`) VALUES
(19, 1, 324234, 'Nguyễn Văn Trí', 'Nam', '01/01/1999', '64345523', 4, '', '', '', '', '', -1, -1, -1),
(20, 2, 645564, 'Đặng Thai Mai', 'Nam', '03/01/2000', '43545634', 4, NULL, NULL, NULL, NULL, NULL, -1, -1, -1),
(21, 3, 343423, 'Nguyễn Thành An', 'Nam', '10/01/1998', '45436565', 4, NULL, NULL, NULL, NULL, NULL, -1, -1, -1),
(22, 4, 245343, 'Thái Linh Hương', 'Nữ', '10/10/2000', '34342333', 4, NULL, NULL, NULL, NULL, NULL, -1, -1, -1),
(23, 5, 435453, 'Nguyễn Kiều Anh', 'Nữ', '02/01/1998', '24247878', 4, NULL, NULL, NULL, NULL, NULL, -1, -1, -1),
(24, 6, 132332, 'Lê Văn Mạnh', 'Nam', '22/12/1998', '24242345', 4, '', '', '', '', '', -1, -1, -1),
(25, 7, 234349, 'Đỗ Quốc Bảo', 'Nam', '11/12/2000', '56456767', 4, NULL, NULL, NULL, NULL, NULL, -1, -1, -1),
(26, 8, 345454, 'Đinh Hồng Lĩnh', 'Nữ', '12/11/2000', '34324343', 4, NULL, NULL, NULL, NULL, NULL, -1, -1, -1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tokhaiytehangngay`
--

CREATE TABLE `tokhaiytehangngay` (
  `id` int(10) UNSIGNED NOT NULL,
  `ngayNop` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `hoVaTen` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idNhanKhau` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `soDienThoai` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `denNoiCoDich` tinyint(4) DEFAULT NULL,
  `tiepXuc` tinyint(4) DEFAULT NULL,
  `trieuChung` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `daXem` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tokhaiytehangngay`
--

INSERT INTO `tokhaiytehangngay` (`id`, `ngayNop`, `hoVaTen`, `idNhanKhau`, `soDienThoai`, `denNoiCoDich`, `tiepXuc`, `trieuChung`, `daXem`) VALUES
(1, '2021-05-29', 'a', 'a', 'a', 1, 0, 'ho, đau họng', 1),
(2, '2021-05-29', 'Nguyễn Văn Trí', '1', '0987654321', 1, 0, 'ho, đau họng', 1),
(3, '2021-05-29', 'Đặng Thai Mai', '2', '1234567890', 0, 0, 'ho, đau họng, abc', 1),
(4, '2021-05-29', 'Nguyễn Thành An', '3', '123654', 0, 1, 'ho, sốt, đau họng, khó thở, abc', 1),
(5, '2021-05-29', 'Thái Linh Hương', '4', '9999999999', 1, 1, ', sốt, khó thở', 0),
(6, '2021-05-29', 'Nguyễn Kiều Anh', '5', '8888888888', 1, 1, 'sốt, khó thở, zzz', 1),
(7, '2021-05-30', 'huy', '123456', '0192837465', 1, 0, 'ho, đau họng, aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', 1),
(11, '2021-05-30', 'zzzzzzz', '123456', '55555555', 1, 0, 'sốt\nđau họng\nkhó thở\n', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hokhau`
--
ALTER TABLE `hokhau`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `lichsh`
--
ALTER TABLE `lichsh`
  ADD PRIMARY KEY (`stt`);

--
-- Chỉ mục cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `noidungphananh`
--
ALTER TABLE `noidungphananh`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `suckhoenguoidan`
--
ALTER TABLE `suckhoenguoidan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tokhaiytehangngay`
--
ALTER TABLE `tokhaiytehangngay`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hokhau`
--
ALTER TABLE `hokhau`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `lichsh`
--
ALTER TABLE `lichsh`
  MODIFY `stt` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `nhankhau`
--
ALTER TABLE `nhankhau`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `noidungphananh`
--
ALTER TABLE `noidungphananh`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `suckhoenguoidan`
--
ALTER TABLE `suckhoenguoidan`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT cho bảng `tokhaiytehangngay`
--
ALTER TABLE `tokhaiytehangngay`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
