-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2023 at 06:35 PM
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
('C23', 'doliprane', 'Spécialité médicament', 'Inhalées', 32, '2023-04-12', '2023-04-06'),
('D34', 'Vitamine C', 'Parapharmacerie', 'Dérmique', 34, '2023-04-17', '2022-04-09'),
('R12', 'Rinoumissin', 'Parapharmacerie', 'Inhalées', 10, '2023-04-18', '2021-05-15');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`Id`, `Name`, `Phone`, `Email`, `Payment`, `RIB`) VALUES
(11, 'TARIQ', '18309', 'gmail', 'Payment Par Cash', 'Payment Par Cash'),
(12, 'TARIQSO', 'DOQD', 'DPQED', 'Payment Par Bank', '09706');

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
