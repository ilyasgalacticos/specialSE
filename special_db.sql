-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 12, 2020 at 10:16 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.3.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `special_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `club` varchar(255) NOT NULL,
  `transfer_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `name`, `surname`, `club`, `transfer_price`) VALUES
(1, 'Cristiano', 'Ronaldo', 'Juventus', 90000000),
(2, 'Leo', 'Messi', 'Barcelona', 120000000),
(3, 'Sergio', 'Ramos', 'Real', 30000000),
(4, 'Neymar', 'Jr.', 'PSG', 222000000),
(5, 'Kilyan', 'Mbappe', 'PSG', 170000000),
(6, 'Ertem', 'Dzyuba', 'Zenit', 10000000.45);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
