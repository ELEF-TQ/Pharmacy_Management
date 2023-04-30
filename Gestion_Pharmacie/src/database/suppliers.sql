-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 30, 2023 at 12:33 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pharmacymanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE IF NOT EXISTS `suppliers` (
  `Id` int(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) NOT NULL,
  `Phone` varchar(30) NOT NULL,
  `Email` varchar(60) NOT NULL,
  `Payment` varchar(20) NOT NULL DEFAULT 'Bank',
  `RIB` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`Id`, `Name`, `Phone`, `Email`, `Payment`, `RIB`) VALUES
(1, 'Supplier 1', '1234567890', 'supplier1@example.com', 'Payment Par Bank', '9544640210'),
(2, 'Supplier 2', '2345678901', 'supplier2@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(3, 'Supplier 3', '3456789012', 'supplier3@example.com', 'Payment Par Bank', '5700229245'),
(4, 'Supplier 4', '4567890123', 'supplier4@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(5, 'Supplier 5', '5678901234', 'supplier5@example.com', 'Payment Par Bank', '7867225875'),
(6, 'Supplier 6', '6789012345', 'supplier6@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(7, 'Supplier 7', '7890123456', 'supplier7@example.com', 'Payment Par Bank', '3235441918'),
(8, 'Supplier 8', '8901234567', 'supplier8@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(9, 'Supplier 9', '9012345678', 'supplier9@example.com', 'Payment Par Bank', '9575532242'),
(10, 'Supplier 10', '0123456789', 'supplier10@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(11, 'Supplier 11', '1111111111', 'supplier11@example.com', 'Payment Par Bank', '8385830327'),
(12, 'Supplier 12', '2222222222', 'supplier12@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(13, 'Supplier 13', '3333333333', 'supplier13@example.com', 'Payment Par Bank', '2450843813'),
(14, 'Supplier 14', '4444444444', 'supplier14@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(15, 'Supplier 15', '5555555555', 'supplier15@example.com', 'Payment Par Bank', '4096728361'),
(16, 'Supplier 16', '6666666666', 'supplier16@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(17, 'Supplier 17', '7777777777', 'supplier17@example.com', 'Payment Par Bank', '3131110643'),
(18, 'Supplier 18', '8888888888', 'supplier18@example.com', 'Payment Par Cash', 'Payment Par Cash'),
(19, 'Supplier 19', '9999999999', 'supplier19@example.com', 'Payment Par Bank', '2365368408'),
(20, 'Supplier 20', '0000000000', 'supplier20@example.com', 'Payment Par Cash', 'Payment Par Cash');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
