-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2021 at 07:16 AM
-- Server version: 5.7.9
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `abcinstitute`
--

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

DROP TABLE IF EXISTS `lecturers`;
CREATE TABLE IF NOT EXISTS `lecturers` (
  `lecturerName` varchar(50) NOT NULL,
  `lecturerId` varchar(6) NOT NULL,
  `faculty` varchar(15) NOT NULL,
  `department` varchar(15) NOT NULL,
  `center` varchar(15) NOT NULL,
  `builduing` varchar(15) NOT NULL,
  `level` varchar(2) NOT NULL,
  `ranks` varchar(10) NOT NULL,
  PRIMARY KEY (`lecturerId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturers`
--

INSERT INTO `lecturers` (`lecturerName`, `lecturerId`, `faculty`, `department`, `center`, `builduing`, `level`, `ranks`) VALUES
('Kamal', '1405', 'Computing', 'SE', 'Malabe', 'Main', '1', '1.1405'),
('Vimal', '1406', 'Computing', 'SE', 'Malabe', 'Main', '2', '2.1406'),
('Anna', '1407', 'Computing', 'SE', 'Malabe', 'Main', '3', '3.1407'),
('John', '1308', 'Computing', 'IT', 'Malabe', 'D-Block', '4', '4.1308'),
('Emma', '1309', 'Computing', 'IT', 'Malabe', 'D-Block', '5', '5.1309'),
('Joseph', '1100', 'Computing', 'IT', 'Malabe', 'D-Block', '6', '6.1100');

-- --------------------------------------------------------

--
-- Table structure for table `lecturers1`
--

DROP TABLE IF EXISTS `lecturers1`;
CREATE TABLE IF NOT EXISTS `lecturers1` (
  `ID` varchar(5) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `NoWorkingDays` int(11) NOT NULL,
  `Mhrs` int(11) DEFAULT NULL,
  `Mmin` int(11) DEFAULT NULL,
  `Thrs` int(11) DEFAULT NULL,
  `Tmin` int(11) DEFAULT NULL,
  `Whrs` int(11) DEFAULT NULL,
  `Wmin` int(11) DEFAULT NULL,
  `Thhrs` int(11) DEFAULT NULL,
  `Thmin` int(11) DEFAULT NULL,
  `Fhrs` int(11) DEFAULT NULL,
  `Fmin` int(11) DEFAULT NULL,
  `Shrs` int(11) DEFAULT NULL,
  `Smin` int(11) DEFAULT NULL,
  `Suhrs` int(11) DEFAULT NULL,
  `Sumin` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecturers1`
--

INSERT INTO `lecturers1` (`ID`, `Name`, `NoWorkingDays`, `Mhrs`, `Mmin`, `Thrs`, `Tmin`, `Whrs`, `Wmin`, `Thhrs`, `Thmin`, `Fhrs`, `Fmin`, `Shrs`, `Smin`, `Suhrs`, `Sumin`) VALUES
('1405', 'Kamal', 5, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 0, 0, 0, 0),
('1406', 'Vimal', 5, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0),
('1407', 'Anna', 6, 2, 0, 2, 0, 2, 30, 2, 0, 2, 30, 2, 0, 0, 0),
('1308', 'John', 5, 3, 0, 4, 0, 5, 30, 5, 0, 2, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `lectures`
--

DROP TABLE IF EXISTS `lectures`;
CREATE TABLE IF NOT EXISTS `lectures` (
  `ID` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lectures`
--

INSERT INTO `lectures` (`ID`, `Name`) VALUES
(1, '1543'),
(2, '1543'),
(4, '1543');

-- --------------------------------------------------------

--
-- Table structure for table `lec_not_avilable_times`
--

DROP TABLE IF EXISTS `lec_not_avilable_times`;
CREATE TABLE IF NOT EXISTS `lec_not_avilable_times` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer_Name` varchar(45) NOT NULL,
  `Main_Group` varchar(45) NOT NULL,
  `Sub_Group` varchar(45) NOT NULL,
  `Session_ID` varchar(45) NOT NULL,
  `Time_From` time NOT NULL,
  `Time_To` time NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lec_not_avilable_times`
--

INSERT INTO `lec_not_avilable_times` (`ID`, `Lecturer_Name`, `Main_Group`, `Sub_Group`, `Session_ID`, `Time_From`, `Time_To`) VALUES
(1, 'Dr. Nuwan Kodagoda', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '7', '11:46:42', '11:46:42'),
(3, 'Dr Pradeepa', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '4', '11:46:42', '11:46:42'),
(5, 'Dr Pradeepa', 'Y1.S2.IT.03', 'Y1.S2.IT.03.1', '9', '15:46:42', '11:46:42'),
(6, 'Dr Pradeepa', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '4', '17:12:52', '17:12:52'),
(7, 'Dr. Nuwan Kodagoda', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '4', '10:39:43', '12:39:43'),
(8, 'Dr. Nuwan Kodagoda', 'Y4.S2.IT.02', 'Y4.S2.IT.02.1', '3', '21:57:12', '21:57:12'),
(11, 'Dr. Nuwan Kodagoda', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '7', '11:46:42', '11:46:42'),
(12, 'Dr Pradeepa', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '4', '11:46:42', '11:46:42'),
(13, 'Dr Pradeepa', 'Y1.S2.IT.03', 'Y1.S2.IT.03.1', '9', '15:46:42', '11:46:42');

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
CREATE TABLE IF NOT EXISTS `locations` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `buildingName` varchar(20) NOT NULL,
  `roomName` varchar(20) NOT NULL,
  `roomType` varchar(20) NOT NULL,
  `roomCapacity` varchar(20) NOT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`lid`, `buildingName`, `roomName`, `roomType`, `roomCapacity`) VALUES
(10, 'D - Block', 'D-301', 'Lecture Hall', '100'),
(11, 'D - Block', 'D-302', 'Lecture Hall', '100'),
(12, 'D - Block', 'D-303', 'Laboratary', '100'),
(13, 'Main', 'B-401', 'Lecture Hall', '100'),
(14, 'Main', 'B-201', 'Lecture Hall', '100'),
(15, 'Main', 'A-501', 'Lecture Hall', '100');

-- --------------------------------------------------------

--
-- Table structure for table `manageactivities`
--

DROP TABLE IF EXISTS `manageactivities`;
CREATE TABLE IF NOT EXISTS `manageactivities` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `sessionId1` varchar(20) NOT NULL,
  `sessionId2` varchar(12) NOT NULL,
  `sessionId3` varchar(12) DEFAULT NULL,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manageactivities`
--

INSERT INTO `manageactivities` (`mid`, `sessionId1`, `sessionId2`, `sessionId3`, `type`) VALUES
(4, '5', '3', NULL, 'Parallel'),
(5, '3', '4', NULL, 'Parallel'),
(7, '6', '8', NULL, 'Consecutive'),
(8, '11', '12', '13', 'Not OverLap');

-- --------------------------------------------------------

--
-- Table structure for table `not_available_locations`
--

DROP TABLE IF EXISTS `not_available_locations`;
CREATE TABLE IF NOT EXISTS `not_available_locations` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Room` varchar(45) NOT NULL,
  `Day` varchar(45) NOT NULL,
  `Start_Time` time NOT NULL,
  `End_Time` time NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `not_available_locations`
--

INSERT INTO `not_available_locations` (`ID`, `Room`, `Day`, `Start_Time`, `End_Time`) VALUES
(1, 'A502', 'Monday', '21:18:44', '22:18:44'),
(2, 'A502', 'Tuesday', '21:58:03', '22:58:03'),
(3, 'D202', 'Thursday', '16:48:32', '18:48:32'),
(4, 'B405', 'Monday', '17:40:57', '17:40:57'),
(5, 'B405', 'Monday', '17:40:57', '17:40:57'),
(6, 'D-303', 'Tuesday', '10:30:00', '11:30:00'),
(11, 'A502', 'Monday', '21:18:44', '22:18:44'),
(12, 'A502', 'Tuesday', '21:58:03', '22:58:03');

-- --------------------------------------------------------

--
-- Table structure for table `preffered_sessions`
--

DROP TABLE IF EXISTS `preffered_sessions`;
CREATE TABLE IF NOT EXISTS `preffered_sessions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Lecturer` varchar(45) NOT NULL,
  `Main_Group` varchar(45) NOT NULL,
  `subject` varchar(45) NOT NULL,
  `Date` varchar(45) NOT NULL,
  `Room` varchar(20) DEFAULT NULL,
  `Start_Time` time NOT NULL,
  `End_Time` time NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preffered_sessions`
--

INSERT INTO `preffered_sessions` (`ID`, `Lecturer`, `Main_Group`, `subject`, `Date`, `Room`, `Start_Time`, `End_Time`) VALUES
(13, 'Kamal', 'Y1.S2.IT.01', 'ITPM', 'Wednesday', 'D-301', '09:30:00', '11:30:00'),
(10, 'Anna', 'Y1.S1.IT.01', 'ITPM', 'Monday', 'D-301', '08:30:00', '10:30:00'),
(12, 'Kamal', 'Y1.S2.IT.01', 'ITPM', 'Tuesday', 'D-301', '09:30:00', '14:30:00'),
(14, 'Vimal', 'Y1.S2.IT.01', 'ITPM', 'Monday', 'D-301', '15:30:00', '17:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
CREATE TABLE IF NOT EXISTS `sessions` (
  `ssid` int(11) NOT NULL AUTO_INCREMENT,
  `lecturer` varchar(20) NOT NULL,
  `lecturer2` varchar(20) NOT NULL,
  `subjectName` varchar(20) NOT NULL,
  `subjectCode` varchar(20) NOT NULL,
  `grpId` varchar(20) NOT NULL,
  `tag` varchar(20) NOT NULL,
  `noOfStudents` varchar(20) NOT NULL,
  `duration` varchar(20) NOT NULL,
  `sessionName` varchar(100) NOT NULL,
  `rooms` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ssid`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`ssid`, `lecturer`, `lecturer2`, `subjectName`, `subjectCode`, `grpId`, `tag`, `noOfStudents`, `duration`, `sessionName`, `rooms`) VALUES
(18, 'Kamal', 'none', 'ITPM', 'IT3040', 'Y3.S1.IT.01', 'Lecture', '120', '2', 'Kamal-none-IT3040-ITPM-Y3.S1.IT.01-120-2', 'D-301'),
(21, 'Kamal', 'none', 'ITPM', 'IT3040', 'Y3.S1.IT.01', 'Tutorial', '120', '1', 'Kamal-none-IT3040-ITPM-Y3.S1.IT.01-120-1', 'D-301'),
(22, 'Vimal', 'none', 'SPM', 'SE3080', 'Y3.S1.IT.01', 'Lecture', '120', '2', 'Vimal-none-SE3080-SPM-Y3.S1.IT.01-120-2', 'D-301'),
(23, 'Vimal', 'none', 'SPM', 'SE3080', 'Y3.S1.IT.01', 'Tutorial', '120', '1', 'Vimal-none-SE3080-SPM-Y3.S1.IT.01-120-1', 'D-301'),
(24, 'Anna', 'none', 'DS', 'IT2020', 'Y3.S1.IT.01', 'Lecture', '120', '2', 'Anna-none-IT2020-DS-Y3.S1.IT.01-120-2', 'D-301'),
(25, 'Anna', 'none', 'DS', 'IT2020', 'Y3.S1.IT.01.1', 'Practical', '120', '2', 'Anna-none-IT2020-DS-Y3.S1.IT.01.1-120-2', 'D-303');

-- --------------------------------------------------------

--
-- Table structure for table `std_group`
--

DROP TABLE IF EXISTS `std_group`;
CREATE TABLE IF NOT EXISTS `std_group` (
  `ID` int(11) NOT NULL,
  `Group_ID` varchar(45) DEFAULT NULL,
  `Sub_Group_ID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `std_group`
--

INSERT INTO `std_group` (`ID`, `Group_ID`, `Sub_Group_ID`) VALUES
(1, 'Y1.S1.IT.01', 'Y1.S1.IT.01.1'),
(2, 'Y1.S1.IT.01', 'Y1.S1.IT.01.2'),
(3, 'Y1.S1.IT.02', 'Y1.S1.IT.01.1');

-- --------------------------------------------------------

--
-- Table structure for table `std_not_available_times`
--

DROP TABLE IF EXISTS `std_not_available_times`;
CREATE TABLE IF NOT EXISTS `std_not_available_times` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SGroup` varchar(45) NOT NULL,
  `Sub_Group` varchar(45) NOT NULL,
  `Session_ID` varchar(2) NOT NULL,
  `FromT` time NOT NULL,
  `ToT` time NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `std_not_available_times`
--

INSERT INTO `std_not_available_times` (`ID`, `SGroup`, `Sub_Group`, `Session_ID`, `FromT`, `ToT`) VALUES
(2, 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '1', '14:00:00', '15:00:00'),
(3, 'Y1.S1.IT.01', 'Y1.S2.IT.03.1', '13', '11:44:56', '11:44:56'),
(4, 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '13', '18:12:51', '20:12:51'),
(5, 'Y1.S1.IT.01', 'Y1.S1.IT.01.1', '3', '17:16:05', '17:16:05'),
(9, 'Y1.S1.IT.01', 'Y4.S2.IT.02.1', '6', '09:43:32', '10:43:32'),
(10, 'Y4.S2.IT.02', 'Y4.S2.IT.02.1', '3', '09:43:32', '10:43:32');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `stdid` int(11) NOT NULL AUTO_INCREMENT,
  `academicYearSem` varchar(7) NOT NULL,
  `programme` varchar(7) NOT NULL,
  `grpNo` varchar(7) NOT NULL,
  `subGrpNo` varchar(7) NOT NULL,
  `grpId` varchar(15) NOT NULL,
  `subGrpId` varchar(15) NOT NULL,
  PRIMARY KEY (`stdid`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`stdid`, `academicYearSem`, `programme`, `grpNo`, `subGrpNo`, `grpId`, `subGrpId`) VALUES
(20, 'Y1.S2', 'IT', '01', '2', 'Y1.S2.IT.01', 'Y1.S2.IT.01.2'),
(15, 'Y1.S1', 'IT', '01', '1', 'Y1.S1.IT.01', 'Y1.S1.IT.01.1'),
(17, 'Y1.S1', 'IT', '01', '2', 'Y1.S1.IT.01', 'Y1.S1.IT.01.2'),
(18, 'Y1.S1', 'IT', '01', '3', 'Y1.S1.IT.01', 'Y1.S1.IT.01.3'),
(19, 'Y1.S2', 'IT', '01', '1', 'Y1.S2.IT.01', 'Y1.S2.IT.01.1'),
(21, 'Y1.S2', 'CSSE', '01', '1', 'Y1.S2.CSSE.01', 'Y1.S2.CSSE.01.1'),
(22, 'Y1.S2', 'CSSE', '01', '2', 'Y1.S2.CSSE.01', 'Y1.S2.CSSE.01.2'),
(23, 'Y1.S2', 'CSSE', '01', '3', 'Y1.S2.CSSE.01', 'Y1.S2.CSSE.01.3'),
(24, 'Y1.S2', 'CSSE', '01', '4', 'Y1.S2.CSSE.01', 'Y1.S2.CSSE.01.4'),
(25, 'Y2.S1', 'CSSE', '01', '1', 'Y2.S1.CSSE.01', 'Y2.S1.CSSE.01.1'),
(26, 'Y2.S1', 'CSSE', '01', '2', 'Y2.S1.CSSE.01', 'Y2.S1.CSSE.01.2'),
(27, 'Y3.S1', 'IM', '01', '1', 'Y3.S1.IM.01', 'Y3.S1.IM.01.1'),
(28, 'Y3.S1', 'IM', '01', '2', 'Y3.S1.IM.01', 'Y3.S1.IM.01.2'),
(29, 'Y3.S2', 'IM', '01', '1', 'Y3.S2.IM.01', 'Y3.S2.IM.01.1'),
(30, 'Y3.S2', 'IM', '01', '2', 'Y3.S2.IM.01', 'Y3.S2.IM.01.2'),
(31, 'Y3.S2', 'IM', '01', '3', 'Y3.S2.IM.01', 'Y3.S2.IM.01.3'),
(32, 'Y4.S1', 'CSE', '01', '1', 'Y4.S1.CSE.01', 'Y4.S1.CSE.01.1'),
(33, 'Y4.S1', 'CSE', '01', '2', 'Y4.S1.CSE.01', 'Y4.S1.CSE.01.2'),
(34, 'Y4.S2', 'CSE', '01', '1', 'Y4.S2.CSE.01', 'Y4.S2.CSE.01.1'),
(35, 'Y4.S2', 'CSE', '01', '2', 'Y4.S2.CSE.01', 'Y4.S2.CSE.01.2'),
(36, 'Y4.S2', 'CSE', '01', '3', 'Y4.S2.CSE.01', 'Y4.S2.CSE.01.3'),
(37, 'Y3.S1', 'IT', '01', '1', 'Y3.S1.IT.01', 'Y3.S1.IT.01.1');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE IF NOT EXISTS `subjects` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(20) NOT NULL,
  `subjectCode` varchar(20) NOT NULL,
  `offerdYear` varchar(20) NOT NULL,
  `offerdSem` varchar(20) NOT NULL,
  `noOfLecHrs` varchar(3) NOT NULL,
  `noOfTutHrs` varchar(3) NOT NULL,
  `noOfLabHrs` varchar(3) NOT NULL,
  `noOfEvaHrs` varchar(3) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`sid`, `subjectName`, `subjectCode`, `offerdYear`, `offerdSem`, `noOfLecHrs`, `noOfTutHrs`, `noOfLabHrs`, `noOfEvaHrs`) VALUES
(7, 'ITPM', 'IT3040', '3rd Year', '1st Semester', '2', '1', '0', '2'),
(8, 'SPM', 'SE3080', '3rd Year', '1st Semester', '2', '1', '0', '2'),
(9, 'DS', 'IT2020', '3rd Year', '1st Semester', '2', '1', '0', '2'),
(10, 'SE', 'IE4050', '4th Year', '1st Semester', '2', '1', '0', '2'),
(11, 'DS', 'IT3030', '1st Year', '1st Semester', '2', '1', '0', '2'),
(12, 'ML', 'IT2020', '2nd Year', '1st Semester', '2', '1', '2', '0'),
(13, 'EAD', 'IT9090', '3rd Year', '1st Semester', '2', '1', '2', '0'),
(14, 'OOC', 'IT6789', '4th Year', '1st Semester', '2', '1', '2', '0');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(20) NOT NULL,
  `tagCode` varchar(10) NOT NULL,
  `tagRelated` varchar(10) NOT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`tid`, `tagName`, `tagCode`, `tagRelated`) VALUES
(9, 'Tute', 'tute01', 'Tutorial'),
(8, 'Lecturer', 'lec01', 'Lecture'),
(10, 'Lab', 'lab01', 'Practical');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
