-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2023 at 05:45 PM
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
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `Code` varchar(20) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `Forme` varchar(30) NOT NULL,
  `Price` int(30) NOT NULL,
  `DateFab` date NOT NULL,
  `DateExp` date NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`Code`, `Name`, `Category`, `Forme`, `Price`, `DateFab`, `DateExp`) VALUES
('P1', 'Product 1', 'Spécialité médicament', 'Injéctable', 11, '2023-01-01', '2023-12-31'),
('P10', 'Product 10', 'Spécialité médicament', 'Dérmique', 11, '2023-10-01', '2023-03-31'),
('P11', 'Product 11', 'Spécialité médicament', 'Injéctable', 25, '2023-02-15', '2023-11-15'),
('P12', 'Product 12', 'Parapharmacerie', 'Dérmique', 13, '2023-03-20', '2023-10-20'),
('P13', 'Product 13', 'diététique', 'Inhalées', 12, '2023-05-01', '2023-08-01'),
('P14', 'Product 14', 'Spécialité médicament', 'Réctales', 15, '2023-06-15', '2023-09-15'),
('P15', 'Product 15', 'Parapharmacerie', 'Injéctable', 18, '2023-08-01', '2023-05-01'),
('P16', 'Product 16', 'diététique', 'Dérmique', 20, '2023-09-15', '2023-04-15'),
('P17', 'Product 17', 'Spécialité médicament', 'Inhalées', 9, '2023-10-30', '2023-03-30'),
('P18', 'Product 18', 'Parapharmacerie', 'Réctales', 12, '2023-11-15', '2023-02-15'),
('P19', 'Product 19', 'diététique', 'Injéctable', 14, '2023-01-15', '2023-12-15'),
('P2', 'Product 2', 'Parapharmacerie', 'Dérmique', 16, '2023-02-01', '2023-11-30'),
('P20', 'Product 20', 'Spécialité médicament', 'Dérmique', 17, '2023-04-01', '2023-09-01'),
('P3', 'Product 3', 'diététique', 'Inhalées', 9, '2023-03-01', '2023-10-31'),
('P4', 'Product 4', 'Spécialité médicament', 'Réctales', 13, '2023-04-01', '2023-09-30'),
('P5', 'Product 5', 'Parapharmacerie', 'Injéctable', 10, '2023-05-01', '2023-08-31'),
('P6', 'Product 6', 'diététique', 'Dérmique', 15, '2023-06-01', '2023-07-31'),
('P7', 'Product 7', 'Spécialité médicament', 'Inhalées', 12, '2023-07-01', '2023-06-30'),
('P8', 'Product 8', 'Parapharmacerie', 'Réctales', 17, '2023-08-01', '2023-05-31'),
('P9', 'Product 9', 'diététique', 'Injéctable', 14, '2023-09-01', '2023-04-30');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE IF NOT EXISTS `sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Client_Name` varchar(255) NOT NULL,
  `Client_CNI` varchar(255) NOT NULL,
  `Sale_Date` datetime NOT NULL,
  `Total_Price` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`id`, `Client_Name`, `Client_CNI`, `Sale_Date`, `Total_Price`) VALUES
(10, 'TARIQ', 'JC617648', '2023-05-10 14:47:36', 680),
(11, 'Test2', 'JC2', '2023-05-10 15:53:28', 300);

-- --------------------------------------------------------

--
-- Table structure for table `sales_products`
--

CREATE TABLE IF NOT EXISTS `sales_products` (
  `Sale_ID` int(11) NOT NULL,
  `Product_Code` varchar(20) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`Sale_ID`,`Product_Code`),
  KEY `product_code` (`Product_Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales_products`
--

INSERT INTO `sales_products` (`Sale_ID`, `Product_Code`, `Quantity`) VALUES
(10, 'P11', 20),
(10, 'P14', 12),
(11, 'P11', 12);

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
(1, 'Supplier 1', '1234567890', 'supplier1@example.com', 'Payment Par Bank', '9544640212'),
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

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales_products`
--
ALTER TABLE `sales_products`
  ADD CONSTRAINT `sales_products_ibfk_1` FOREIGN KEY (`sale_id`) REFERENCES `sales` (`id`),
  ADD CONSTRAINT `sales_products_ibfk_2` FOREIGN KEY (`product_code`) REFERENCES `products` (`Code`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
