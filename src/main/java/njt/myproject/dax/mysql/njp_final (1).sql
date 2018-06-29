-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 29, 2018 at 04:19 AM
-- Server version: 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `njp_final`
--

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `todo_list_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `due_date` date NOT NULL,
  `priority` tinyint(1) NOT NULL COMMENT '1-low, 2-medium , 3 - high',
  `done` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0- not done, 1- done',
  PRIMARY KEY (`id`,`todo_list_id`),
  KEY `task_todo_list_fk` (`todo_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `todo_list_id`, `name`, `description`, `due_date`, `priority`, `done`) VALUES
(1, 1, 'Phalcon update', 'Phalcon 3.3 version update on Linux systems', '2018-09-21', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `todo_list`
--

DROP TABLE IF EXISTS `todo_list`;
CREATE TABLE IF NOT EXISTS `todo_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `todo_list`
--

INSERT INTO `todo_list` (`id`, `name`) VALUES
(1, 'Job Tasks'),
(2, 'Home Tasks');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifier',
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(350) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0-not active, 1 -active',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `email`, `password`, `active`) VALUES
(1, 'Nikola', 'Trajkovic', 't@t.com', '$2a$10$71daK2vz7RUeqvqI4f4t9eg7/Ub5HfEJj2Qb7h/hIZD1EmGOUhakW', 1),
(2, 'Dzony', 'Boy', 'trajko@gmail.com', '$2a$10$o85ATQ6Skt2ANwpekXcU1.GmlREc4l3KO23BlKU6btGa0wLbiy9fS', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_has_todo_list`
--

DROP TABLE IF EXISTS `user_has_todo_list`;
CREATE TABLE IF NOT EXISTS `user_has_todo_list` (
  `user_id` int(11) NOT NULL,
  `todo_list_id` int(11) NOT NULL,
  `permissions` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-full permission, 2- RU , 3 - read only',
  PRIMARY KEY (`user_id`,`todo_list_id`),
  KEY `todo_list_fk` (`todo_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_has_todo_list`
--

INSERT INTO `user_has_todo_list` (`user_id`, `todo_list_id`, `permissions`) VALUES
(1, 1, 1),
(2, 2, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_todo_list_fk` FOREIGN KEY (`todo_list_id`) REFERENCES `todo_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_has_todo_list`
--
ALTER TABLE `user_has_todo_list`
  ADD CONSTRAINT `todo_list_fk` FOREIGN KEY (`todo_list_id`) REFERENCES `todo_list` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
