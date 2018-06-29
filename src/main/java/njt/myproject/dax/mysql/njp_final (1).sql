-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2018 at 04:03 PM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

CREATE TABLE `task` (
  `id` int(11) NOT NULL,
  `todo_list_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `due_date` date NOT NULL,
  `priority` tinyint(1) NOT NULL COMMENT '1-low, 2-medium , 3 - high',
  `done` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0- not done, 1- done'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `todo_list_id`, `name`, `description`, `due_date`, `priority`, `done`) VALUES
(1, 1, 'Backend job', 'Phalcon update task', '2018-09-15', 2, 0),
(2, 1, 'Front End ', 'Angualr 5 job task', '2018-10-27', 1, 0),
(3, 1, 'test', 'desc', '2018-06-30', 3, 1),
(8, 8, 'dasda', 'x1231231313213', '2018-08-17', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `todo_list`
--

CREATE TABLE `todo_list` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `todo_list`
--

INSERT INTO `todo_list` (`id`, `name`) VALUES
(1, 'Job Tasks'),
(7, 'Job Tasks'),
(8, 'Test1'),
(9, 'Home Tasks');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL COMMENT 'identifier',
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(350) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0-not active, 1 -active',
  `role` tinyint(1) NOT NULL COMMENT '0-regular user, 1-admin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `email`, `password`, `active`, `role`) VALUES
(1, 'Nikola', 'Trajkovic', 'trajko@gmail.com', '$2a$10$y8qhkJsFCt.D1dmmnl/WWuc1/9lpG3MAL.0gw2etLEarhiLswjPaa', 1, 0),
(2, 'User', 'SurName', 'user@user', '$2a$10$iIrazVSDVN61DSGiFXqVeepYjBYzaCqgyfA1tm5QNrM6srubmP7PG', 1, 1),
(3, 'USER ', 'SURNAME', 't@tas', '', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_has_todo_list`
--

CREATE TABLE `user_has_todo_list` (
  `user_id` int(11) NOT NULL,
  `todo_list_id` int(11) NOT NULL,
  `permissions` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1-full permission, 2- RU , 3 - read only'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_has_todo_list`
--

INSERT INTO `user_has_todo_list` (`user_id`, `todo_list_id`, `permissions`) VALUES
(1, 1, 1),
(1, 8, 1),
(1, 9, 1),
(2, 7, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id`,`todo_list_id`),
  ADD KEY `task_todo_list_fk` (`todo_list_id`);

--
-- Indexes for table `todo_list`
--
ALTER TABLE `todo_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_has_todo_list`
--
ALTER TABLE `user_has_todo_list`
  ADD PRIMARY KEY (`user_id`,`todo_list_id`),
  ADD KEY `todo_list_fk` (`todo_list_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `todo_list`
--
ALTER TABLE `todo_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identifier', AUTO_INCREMENT=4;
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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
