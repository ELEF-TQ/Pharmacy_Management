-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2023 at 07:29 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`Id`, `Name`, `Phone`, `Email`, `Payment`, `RIB`) VALUES
(6, 'PharmaBio', '06626063164', 'pharmabio@example.com', 'Paiement par cash', 'Paiement par cash'),
(7, 'MedicoPharm', '06298830017', 'medicopharm@example.com', 'Paiement par banque', '1234567890'),
(8, 'HealthCare Solutions', '06615960624', 'healthcaresolutions@example.com', 'Paiement par cash', 'Paiement par cash'),
(9, 'VitaPharm', '06183313100', 'vitapharm@example.com', 'Paiement par banque', '9876543210'),
(10, 'BioGenix Pharmaceuticals', '06068683660', 'biogenixpharma@example.com', 'Paiement par cash', 'Paiement par cash'),
(11, 'MediCure', '06793479031', 'medicure@example.com', 'Paiement par banque', '2468135790'),
(12, 'Pharmalife', '06761344207', 'pharmalife@example.com', 'Paiement par cash', 'Paiement par cash'),
(13, 'HealWell Pharmaceuticals', '06426283971', 'healwellpharma@example.com', 'Paiement par banque', '1357924680'),
(14, 'BioMedica', '06847387268', 'biomedica@example.com', 'Paiement par cash', 'Paiement par cash'),
(15, 'Medix Pharmaceuticals', '06958084455', 'medixpharma@example.com', 'Paiement par banque', '0123456789');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
