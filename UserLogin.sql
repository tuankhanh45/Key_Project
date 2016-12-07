
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 07, 2016 at 07:00 AM
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
-- Table structure for table `UserLogin`
--

CREATE TABLE IF NOT EXISTS `UserLogin` (
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Company` varchar(50) NOT NULL,
  `Id` varchar(10) NOT NULL,
  `WorkDays` int(11) NOT NULL,
  PRIMARY KEY (`UserName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `UserLogin`
--

INSERT INTO `UserLogin` (`UserName`, `Password`, `Email`, `Company`, `Id`, `WorkDays`) VALUES
('TranVanA', '1111', 'jknono1995@gmail.com', 'FLC', 'TVA123', 1),
('NguyeVanB', '123', '111', 'VCF', 'NVB123', 1),
('PhamVanC', '123', '111', 'VinHomes', 'PVC123', 1),
('2', '2', 'jknono1995@gmail.com', 'snacke', '123', 16),
('tuankhanh', '1111', 'jknono1995@gmail.com', 'abcxyz', '', 5),
('nguyenvana', '1111112', 'nguỵenvana@gmail.com', 'vinhome', '', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
