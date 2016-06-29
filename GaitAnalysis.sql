-- phpMyAdmin SQL Dump
-- version 4.6.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2016-06-28 11:59:40
-- 服务器版本： 5.5.50
-- PHP Version: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `GaitAnalysis`
--

-- --------------------------------------------------------

--
-- 表的结构 `patient`
--

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL COMMENT '登陆ID',
  `patient_pwd` varchar(16) COLLATE utf8_unicode_ci DEFAULT '111111' COMMENT '登陆密码',
  `patient_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT '匿名' COMMENT '姓名',
  `height` float DEFAULT NULL COMMENT '身高(cm)',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别',
  `weight` float DEFAULT NULL COMMENT '体重(kg)',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `leg_length` float DEFAULT NULL COMMENT '腿长',
  `doctor_id` char(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '医生ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `relative`
--

CREATE TABLE `relative` (
  `raletive_id` int(11) NOT NULL COMMENT '亲属ID',
  `patient_id` int(11) NOT NULL COMMENT '病人ID',
  `raletive_pwd` varchar(16) COLLATE utf8_unicode_ci NOT NULL DEFAULT '111111' COMMENT '亲属密码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `settings`
--

CREATE TABLE `settings` (
  `patient_id` int(11) NOT NULL COMMENT '病人ID',
  `alarm_or_not` tinyint(1) DEFAULT '0' COMMENT '1为alarm，0为取消',
  `start_hour` int(11) DEFAULT '9' COMMENT '开始时间',
  `end_hour` int(11) DEFAULT '10' COMMENT '结束时间',
  `start_minute` int(11) DEFAULT '0',
  `end_minute` int(11) DEFAULT '0',
  `doctor_advice` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '医生建议',
  `summary_frequency` tinyint(1) DEFAULT '0' COMMENT '0-1周1次，1-1月1次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `test_result`
--

CREATE TABLE `test_result` (
  `patient_id` int(11) NOT NULL COMMENT '病人ID',
  `avg_length` double NOT NULL DEFAULT '0' COMMENT '平均步长',
  `avg_cadence` double NOT NULL DEFAULT '0' COMMENT '平均步时',
  `avg_degree` double NOT NULL DEFAULT '0' COMMENT '平均倾角',
  `dcd_length` float NOT NULL DEFAULT '0' COMMENT '步长对称度',
  `dcd_cadence` float DEFAULT '0' COMMENT '步时对称度',
  `dcd_degree` float NOT NULL DEFAULT '0' COMMENT '倾角对称度'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `relative`
--
ALTER TABLE `relative`
  ADD PRIMARY KEY (`raletive_id`),
  ADD KEY `Relative-Patient` (`patient_id`);

--
-- Indexes for table `settings`
--
ALTER TABLE `settings`
  ADD PRIMARY KEY (`patient_id`);

--
-- Indexes for table `test_result`
--
ALTER TABLE `test_result`
  ADD PRIMARY KEY (`patient_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `patient`
--
ALTER TABLE `patient`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登陆ID', AUTO_INCREMENT=127;
--
-- 使用表AUTO_INCREMENT `relative`
--
ALTER TABLE `relative`
  MODIFY `raletive_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '亲属ID';
--
-- 限制导出的表
--

--
-- 限制表 `relative`
--
ALTER TABLE `relative`
  ADD CONSTRAINT `relative_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  ADD CONSTRAINT `relative_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  ADD CONSTRAINT `relative_ibfk_3` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);

--
-- 限制表 `settings`
--
ALTER TABLE `settings`
  ADD CONSTRAINT `settings_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);

--
-- 限制表 `test_result`
--
ALTER TABLE `test_result`
  ADD CONSTRAINT `test_result_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
