
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 07, 2016 at 06:59 AM
-- Server version: 10.0.20-MariaDB
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u839687865_cwork`
--

-- --------------------------------------------------------

--
-- Table structure for table `GPS`
--

CREATE TABLE IF NOT EXISTS `GPS` (
  `UserName` varchar(15) NOT NULL,
  `Address` varchar(15) NOT NULL,
  `DateTime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `Latitude` varchar(5) NOT NULL,
  `Longitude` varchar(5) NOT NULL,
  `Data` varchar(10000) NOT NULL,
  PRIMARY KEY (`DateTime`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `GPS`
--

INSERT INTO `GPS` (`UserName`, `Address`, `DateTime`, `Latitude`, `Longitude`, `Data`) VALUES
('TranVanA', '', '2016-11-21 11:22:54', '', '', ''),
('2', '', '2016-12-06 13:54:04', '21.03', '105.7', '[{"Address":"","Longitude":105.7829387,"Latitude":21.0326537,"DateTime":"2016:12:06  13:53:45 "},{"Address":"","Longitude":105.7829387,"Latitude":21.0326537,"DateTime":"2016:12:06  13:54:04 "}]'),
('2', '', '2016-12-06 13:53:45', '21.03', '105.7', '[{"Address":"","Longitude":105.7829387,"Latitude":21.0326537,"DateTime":"2016:12:06  13:53:45 "},{"Address":"","Longitude":105.7829387,"Latitude":21.0326537,"DateTime":"2016:12:06  13:54:04 "}]'),
('2', 'Ngo 7 Pham Hung', '2016-12-06 11:39:44', '21.03', '105.7', '[{"Address":"Ngo 7 Pham Hung Dich Vong Hau Cau Giay Ha Noi ","Longitude":105.7829387,"Latitude":21.0326537,"DateTime":"2016:12:06  11:39:44 "}]'),
('2', 'Ngo 7 Pham Hung', '2016-12-06 13:55:40', '21.03', '105.7', '[{"Address":"Ngo 7 Pham Hung Dich Vong Hau Cau Giay Ha Noi ","Longitude":105.7829387,"Latitude":21.0326537,"DateTime":"2016:12:06  13:55:40 "}]');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
