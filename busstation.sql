-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 04, 2022 lúc 03:39 PM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `busstation`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bookings`
--

CREATE TABLE `bookings` (
  `id` int(11) NOT NULL,
  `BusName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fileName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `filterDate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fromDestination` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `noOfPersons` int(11) DEFAULT NULL,
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `toDestination` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `totalCalculated` double DEFAULT NULL,
  `tripStatus` bit(1) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `bookings`
--

INSERT INTO `bookings` (`id`, `BusName`, `fileName`, `filterDate`, `fromDestination`, `noOfPersons`, `time`, `toDestination`, `totalCalculated`, `tripStatus`, `userId`) VALUES
(1, 'SaiGonTrip', 'nodata', '2022-09-01', 'Sai Gon', 4, '12:00', 'Ha Noi', 4000, b'0', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `busdata`
--

CREATE TABLE `busdata` (
  `id` int(11) NOT NULL,
  `BusName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `filterDate` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fromDestination` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `toDestination` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `busdata`
--

INSERT INTO `busdata` (`id`, `BusName`, `filterDate`, `fromDestination`, `price`, `time`, `toDestination`) VALUES
(1, 'SaiGonTrip', '2022-09-01', 'Sai Gon', 1000, '12:00', 'Ha Noi'),
(2, 'PhanThietAdora', '2022-08-29', 'Dong Xoai', 500, '5:00', 'Phan Thiet'),
(3, 'VungTauDragon', '2022-09-12', 'Sai Gon', 400, '3:00', 'Vung Tau'),
(4, 'HueCityRiver', '2022-09-15', 'Sai Gon', 1000, '22:00', 'Hue');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`) VALUES
(1, '1851050073kiet@ou.edu.vn', 'HoangKiet NguyenHuu', '$2a$10$ZXelnPW.2pXA8bOf5ASqR.g/BAP6r4Sx7fj1G.wwlZgq/ELO83BBS'),
(2, 'uk@uk.com', 'Qliphort Shell', '$2a$10$.jtIf53t1miv58X22JNnbezdJYtDsWiqv4YTZUCRA17pukdCsB9iW');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `usersrole`
--

CREATE TABLE `usersrole` (
  `custId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `usersrole`
--

INSERT INTO `usersrole` (`custId`, `roleId`) VALUES
(2, 1),
(3, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `busdata`
--
ALTER TABLE `busdata`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `usersrole`
--
ALTER TABLE `usersrole`
  ADD PRIMARY KEY (`custId`,`roleId`),
  ADD KEY `FK8u0sj6dksvb8jyyqs2uw0udbd` (`roleId`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bookings`
--
ALTER TABLE `bookings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `busdata`
--
ALTER TABLE `busdata`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
