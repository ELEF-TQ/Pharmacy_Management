-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2023 at 07:38 PM
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
  `Price` int(40) NOT NULL,
  `DateFab` date NOT NULL,
  `DateExp` date NOT NULL,
  `Quantity` int(255) NOT NULL,
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`Code`, `Name`, `Category`, `Forme`, `Price`, `DateFab`, `DateExp`, `Quantity`) VALUES
('10001', 'Paracétamol', 'orale', 'Spécialité médicament', 599, '2022-01-01', '2023-01-01', 100),
('10002', 'Ibuprofène', 'orale', 'Spécialité médicament', 799, '2022-02-01', '2023-02-01', 150),
('10003', 'Aspirine', 'orale', 'Spécialité médicament', 399, '2022-03-01', '2023-03-01', 200),
('10004', 'Morphine', 'injectable', 'Spécialité médicament', 1299, '2022-04-01', '2023-04-01', 50),
('10005', 'Insuline', 'injectable', 'Spécialité médicament', 2599, '2022-05-01', '2023-05-01', 80),
('10006', 'Crème hydratante', 'dermique', 'Parapharmaceutique', 999, '2022-06-01', '2023-06-01', 120),
('10007', 'Sirop pour la toux', 'orale', 'Spécialité médicament', 699, '2022-07-01', '2023-07-01', 90),
('10008', 'Patch contraceptif', 'dermique', 'Parapharmaceutique', 1599, '2022-08-01', '2023-08-01', 70),
('10009', 'Lotion anti-moustique', 'dermique', 'Parapharmaceutique', 499, '2022-09-01', '2023-09-01', 110),
('10010', 'Suppositoire', 'rectale', 'Spécialité médicament', 899, '2022-10-01', '2023-10-01', 60);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
