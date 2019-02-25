-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 19, 2017 at 07:08 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jackemoney`
--

-- --------------------------------------------------------

--
-- Table structure for table `kiosk`
--

CREATE TABLE IF NOT EXISTS `kiosk` (
  `kioskname` varchar(15) NOT NULL,
  `userName` varchar(15) NOT NULL,
  `userId` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `waridline` varchar(15) NOT NULL,
  `mtnline` varchar(15) NOT NULL,
  `waridfloat` int(20) NOT NULL,
  `waridcash` int(20) DEFAULT NULL,
  `mtnfloat` int(20) NOT NULL,
  `mtncash` int(20) DEFAULT NULL,
  `waridcommission` int(20) DEFAULT NULL,
  `mtncommission` int(20) DEFAULT NULL,
  PRIMARY KEY (`kioskname`),
  UNIQUE KEY `userId` (`userId`,`waridline`,`mtnline`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `kiosk`
--

INSERT INTO `kiosk` (`kioskname`, `userName`, `userId`, `waridline`, `mtnline`, `waridfloat`, `waridcash`, `mtnfloat`, `mtncash`, `waridcommission`, `mtncommission`) VALUES
('bugolobi', 'phiona', 4, '0702292820', '0772292820', 876000, 124000, 998000, 2000, 1295, 1145),
('makerere', 'robert', 2, '0753929390', '0783929390', 992200, 7800, 1000000, NULL, 285, NULL),
('makindye', 'mukiibi', 1, '0704291450', '0774291450', 1000000, NULL, 1000000, NULL, NULL, NULL),
('munyonyo', 'lubwama', 5, '0754191416', '0774191416', 830000, 170000, 922000, 78000, 520, 440),
('muswangali', 'sewante', 7, '0704066878', '0784066878', 1154001, 46000, 1102002, 98000, 569, 1008),
('nakasero', 'jude', 6, '0703241920', '0789426490', 1000000, NULL, 1000000, NULL, NULL, NULL),
('wandegeya', 'joyce', 3, '0754112233', '0784112233', 1000000, NULL, 1000000, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE IF NOT EXISTS `request` (
  `requestId` int(5) NOT NULL AUTO_INCREMENT,
  `customerNumber` varchar(15) DEFAULT NULL,
  `amount` int(6) DEFAULT NULL,
  `receiverUserId` int(5) DEFAULT NULL,
  `senderUserId` int(5) DEFAULT NULL,
  `status` varchar(8) DEFAULT NULL,
  `serviceProvider` varchar(10) DEFAULT NULL,
  `dateAndTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`requestId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`requestId`, `customerNumber`, `amount`, `receiverUserId`, `senderUserId`, `status`, `serviceProvider`, `dateAndTime`) VALUES
(1, '0784221010', 60000, 4, 2, 'pending', 'mtn', '2017-05-31 10:41:39'),
(2, '0782142011', 56500, 6, 4, 'pending', 'mtn', '2017-05-31 11:10:00'),
(3, '0782101010', 75000, 2, 4, 'pending', 'mtn', '2017-05-31 13:36:24'),
(4, '0781202003', 46000, 5, 4, 'pending', 'mtn', '2017-05-31 13:40:22');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `transactionId` int(10) NOT NULL AUTO_INCREMENT,
  `kioskPhone` varchar(15) NOT NULL,
  `userName` varchar(15) NOT NULL,
  `customerNumber` varchar(15) NOT NULL,
  `dateAndTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(10) NOT NULL,
  `serviceProvider` varchar(10) NOT NULL,
  `commission` int(6) NOT NULL,
  `amount` int(6) NOT NULL,
  PRIMARY KEY (`transactionId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transactionId`, `kioskPhone`, `userName`, `customerNumber`, `dateAndTime`, `type`, `serviceProvider`, `commission`, `amount`) VALUES
(1, '0784066878', 'sewante', '0782212214', '2017-05-31 10:11:33', 'deposit', 'mtn', 285, 10000),
(2, '0704066878', 'sewante', '0702161719', '2017-05-31 10:11:33', 'deposit', 'warid', 285, 23000),
(3, '0784066878', 'sewante', '07899121617', '2017-05-31 10:21:01', 'deposit', 'mtn', 440, 78000),
(4, '0784066878', 'sewante', '0782212214', '2017-05-31 10:21:02', 'deposit', 'mtn', 285, 10000),
(5, '0704066878', 'sewante', '0702161719', '2017-05-31 10:21:02', 'deposit', 'warid', 285, 23000),
(6, '0753929390', 'robert', '0705172212', '2017-05-31 10:37:27', 'deposit', 'warid', 285, 7800),
(7, '0772292820', 'phiona', '0789912167', '2017-05-31 13:35:11', 'deposit', 'mtn', 285, 17000),
(8, '0772292820', 'phiona', '0778921547', '2017-05-31 13:35:12', 'deposit', 'mtn', 285, 45000),
(9, '0702292820', 'phiona', '0752365810', '2017-05-31 13:35:12', 'deposit', 'warid', 285, 20000),
(10, '0702292820', 'phiona', '0705789541', '2017-05-31 13:38:40', 'deposit', 'warid', 440, 78000),
(11, '0702292820', 'phiona', '0751020406', '2017-05-31 13:38:40', 'deposit', 'warid', 285, 12000),
(12, '0772292820', 'phiona', '07815212112', '2017-05-31 13:42:41', 'withdraw', 'mtn', 575, 60000),
(13, '0702292820', 'phiona', '0751454514', '2017-05-31 13:42:41', 'deposit', 'warid', 285, 14000),
(14, '0774191416', 'lubwama', '0785121312', '2017-05-31 14:10:19', 'deposit', 'mtn', 440, 78000),
(15, '0754191416', 'lubwama', '0705121312', '2017-05-31 14:10:20', 'deposit', 'warid', 520, 170000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(5) NOT NULL AUTO_INCREMENT,
  `userName` varchar(15) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `category` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userId`, `userName`, `password`, `category`) VALUES
(1, 'jack', '123Jadmin', 'Administrator'),
(2, 'mukiibi', 'muk74', 'Attendant'),
(3, 'robert', 'rob3', 'Attendant'),
(4, 'joyce', 'jojo10', 'Attendant'),
(5, 'phiona', 'phio24', 'Attendant'),
(6, 'lubwama', 'lub11', 'Attendant'),
(7, 'jude', 'bwayo17', 'Attendant'),
(8, 'sewante', 'jt25wavez', 'Attendant');
