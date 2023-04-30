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
('P11', 'Product 11', 'Spécialité médicament', 'Injéctable', 10, '2023-02-15', '2023-11-15'),
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
