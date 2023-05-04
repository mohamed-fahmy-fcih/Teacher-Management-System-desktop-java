-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: teacher_student_mangment
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `Student_Id` int NOT NULL,
  `Lec_Id` int NOT NULL,
  `Paied` double NOT NULL,
  `Attencdance_Date` date NOT NULL,
  KEY `lec_id_fk_attendance` (`Lec_Id`),
  KEY `student_id_fk_attendance` (`Student_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (100000,1,50,'2021-04-26'),(200000,5,2000,'2021-07-28'),(100000,7,100,'2021-07-28');
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `Book_Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Book_Price` double NOT NULL,
  `Grade` int DEFAULT NULL,
  PRIMARY KEY (`Book_Id`),
  KEY `Grade` (`Grade`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Physics 1',50,1),(3,'Electronics',200,1),(4,'Electronics 2',200,2),(5,'Electronics 3',228,2);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books_sales`
--

DROP TABLE IF EXISTS `books_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books_sales` (
  `Student_Id` int NOT NULL,
  `Book_Id` int NOT NULL,
  `Paid` double NOT NULL,
  `Sell_Date` date NOT NULL,
  KEY `book_id_fk_book_sales` (`Book_Id`),
  KEY `student_id_fk_book_sales` (`Student_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books_sales`
--

LOCK TABLES `books_sales` WRITE;
/*!40000 ALTER TABLE `books_sales` DISABLE KEYS */;
INSERT INTO `books_sales` VALUES (100000,1,50,'2021-04-26'),(200000,1,20,'2021-07-27');
/*!40000 ALTER TABLE `books_sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `days`
--

DROP TABLE IF EXISTS `days`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `days` (
  `Day_Id` int NOT NULL,
  `Day_Name` varchar(45) NOT NULL,
  PRIMARY KEY (`Day_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `days`
--

LOCK TABLES `days` WRITE;
/*!40000 ALTER TABLE `days` DISABLE KEYS */;
INSERT INTO `days` VALUES (1,'السبت'),(2,'الاحد'),(3,'الاثنين'),(4,'الثلاثاء'),(5,'الاربعاء'),(6,'الخميس'),(7,'الجمعة');
/*!40000 ALTER TABLE `days` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exams`
--

DROP TABLE IF EXISTS `exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams` (
  `Exam_ID` int NOT NULL AUTO_INCREMENT,
  `Exam_Name` varchar(45) NOT NULL,
  `Exam_Degree` double NOT NULL,
  `IsFinal` tinyint NOT NULL DEFAULT '0',
  `Grade_Id` int NOT NULL,
  PRIMARY KEY (`Exam_ID`),
  KEY `Grade_Id` (`Grade_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams`
--

LOCK TABLES `exams` WRITE;
/*!40000 ALTER TABLE `exams` DISABLE KEYS */;
INSERT INTO `exams` VALUES (1,'شهر يناير',50,0,1),(4,'kkkkk',50,0,2),(5,'ttttt',50,0,1),(6,'bbbb',50,0,1);
/*!40000 ALTER TABLE `exams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `Grade_Id` int NOT NULL AUTO_INCREMENT,
  `Garade_Name` varchar(45) DEFAULT NULL,
  `Garade_Prefix` int DEFAULT NULL,
  PRIMARY KEY (`Grade_Id`),
  UNIQUE KEY `Garade_Prefix_UNIQUE` (`Garade_Prefix`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
INSERT INTO `grades` VALUES (1,'اولى ثناوى',1),(2,'تانية ثناوى',2),(3,'تالتة ثناوى',3),(5,'الخامس الثناوى',5),(6,'عاشر الثناوى',10);
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_quiz`
--

DROP TABLE IF EXISTS `lecture_quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_quiz` (
  `Student_Id` int NOT NULL,
  `Lec_Id` int NOT NULL,
  `Degree` double NOT NULL,
  KEY `student_id_fk_lec_quiz` (`Student_Id`),
  KEY `lec_id_fk_lec_quiz` (`Lec_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_quiz`
--

LOCK TABLES `lecture_quiz` WRITE;
/*!40000 ALTER TABLE `lecture_quiz` DISABLE KEYS */;
INSERT INTO `lecture_quiz` VALUES (100000,1,10),(100000,2,12),(100000,3,8),(100000,1,23),(200000,1,22),(100000,1,18),(100000,5,18),(100000,7,15);
/*!40000 ALTER TABLE `lecture_quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lectures`
--

DROP TABLE IF EXISTS `lectures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lectures` (
  `Lec_Id` int NOT NULL AUTO_INCREMENT,
  `Grade_Id` int NOT NULL,
  `Lec_Name` varchar(200) NOT NULL,
  `Date` date NOT NULL,
  `Max_Degree` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`Lec_Id`),
  KEY `grade_id_fk_lectures` (`Grade_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lectures`
--

LOCK TABLES `lectures` WRITE;
/*!40000 ALTER TABLE `lectures` DISABLE KEYS */;
INSERT INTO `lectures` VALUES (1,1,'الاولى','2022-03-08',20),(5,1,'الثانى','2021-07-22',20),(6,3,'الثانى','2021-07-22',20),(7,1,'الثالث','2021-07-27',15);
/*!40000 ALTER TABLE `lectures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `money`
--

DROP TABLE IF EXISTS `money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `money` (
  `descriptionMoney` varchar(400) NOT NULL,
  `money` int NOT NULL,
  `dateMoney` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `money`
--

LOCK TABLES `money` WRITE;
/*!40000 ALTER TABLE `money` DISABLE KEYS */;
INSERT INTO `money` VALUES ('100000 Payed Book. ',50,'2021-04-26'),('100000 Payed Lecture. ',50,'2021-04-26'),('200000 Payed Book. ',20,'2021-07-27'),('200000 Payed Lecture. ',2000,'2021-07-28'),('100000 Payed Lecture. ',100,'2021-07-28');
/*!40000 ALTER TABLE `money` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones_number`
--

DROP TABLE IF EXISTS `phones_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones_number` (
  `Student_Id` int NOT NULL,
  `Student_Phone` varchar(45) DEFAULT NULL,
  `Parent_Phone` varchar(45) DEFAULT NULL,
  KEY `student_id_fk_phones_numbers` (`Student_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones_number`
--

LOCK TABLES `phones_number` WRITE;
/*!40000 ALTER TABLE `phones_number` DISABLE KEYS */;
INSERT INTO `phones_number` VALUES (100000,'01005353','01124532'),(200000,'0100852235','0135358563'),(200001,'4362623','67345734'),(200002,'4362623','67345734'),(1000001,'41251352','62362346'),(300001,'12341234','2351235'),(500004,'456334566345','456345634'),(500004,'35415256','325235'),(500004,'3124141','2344231234'),(500004,'1234124124','233241234'),(500002,'555555','444444'),(500003,'2222','22222');
/*!40000 ALTER TABLE `phones_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretary_attendance`
--

DROP TABLE IF EXISTS `secretary_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretary_attendance` (
  `Secretary_Id` int NOT NULL,
  `Day_Id` int NOT NULL,
  `Attendance_Date` date NOT NULL,
  `Report` varchar(350) DEFAULT NULL,
  KEY `secretary_Id_fk_secretary_attendance` (`Secretary_Id`),
  KEY `day_Id_fk_secretary_attendance` (`Day_Id`),
  CONSTRAINT `day_Id_fk_secretary_attendance` FOREIGN KEY (`Day_Id`) REFERENCES `days` (`Day_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretary_attendance`
--

LOCK TABLES `secretary_attendance` WRITE;
/*!40000 ALTER TABLE `secretary_attendance` DISABLE KEYS */;
INSERT INTO `secretary_attendance` VALUES (1122,1,'2021-07-27',''),(1122,3,'2021-07-27',''),(111,3,'2021-07-27',''),(1122,4,'2021-07-28',''),(1122,5,'2021-07-29',''),(1122,7,'2021-07-31','');
/*!40000 ALTER TABLE `secretary_attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretary_data`
--

DROP TABLE IF EXISTS `secretary_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretary_data` (
  `Secretary_Id` int NOT NULL,
  `Secretary_Name` varchar(100) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `SSN` varchar(45) NOT NULL,
  `BirthDay` date NOT NULL,
  `Joined_Date` date NOT NULL,
  `salary` int NOT NULL,
  PRIMARY KEY (`Secretary_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretary_data`
--

LOCK TABLES `secretary_data` WRITE;
/*!40000 ALTER TABLE `secretary_data` DISABLE KEYS */;
INSERT INTO `secretary_data` VALUES (111,'Ahmed','uuuuuu','47856352','2021-07-13','2021-07-27',2500),(1122,'hany','rrrrrr','783278678952','2021-07-21','2021-07-27',2000),(123123123,'zaki','asasadsas','34734763','2021-07-15','2021-07-28',2000);
/*!40000 ALTER TABLE `secretary_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretary_phones`
--

DROP TABLE IF EXISTS `secretary_phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretary_phones` (
  `Secretary_Id` int NOT NULL,
  `Secretary_Phone` varchar(45) DEFAULT NULL,
  KEY `secretary_Id_fk_secretary_phones` (`Secretary_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretary_phones`
--

LOCK TABLES `secretary_phones` WRITE;
/*!40000 ALTER TABLE `secretary_phones` DISABLE KEYS */;
INSERT INTO `secretary_phones` VALUES (123123,'01112512356'),(1122,'01007235235'),(1122,'01007235235'),(1122,'010092351'),(111,'951252623'),(123123123,'62763835');
/*!40000 ALTER TABLE `secretary_phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secretary_work_days`
--

DROP TABLE IF EXISTS `secretary_work_days`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `secretary_work_days` (
  `Secretary_Id` int NOT NULL,
  `DayName` varchar(45) NOT NULL,
  KEY `secretary_Id_fk_secretary_work_days` (`Secretary_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secretary_work_days`
--

LOCK TABLES `secretary_work_days` WRITE;
/*!40000 ALTER TABLE `secretary_work_days` DISABLE KEYS */;
/*!40000 ALTER TABLE `secretary_work_days` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_general_info`
--

DROP TABLE IF EXISTS `student_general_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_general_info` (
  `Student_Id` int NOT NULL,
  `Name` varchar(200) NOT NULL,
  `School` varchar(45) DEFAULT NULL,
  `Joined_Date` date NOT NULL,
  `Grade_Id` int NOT NULL,
  `Group_Student` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`Student_Id`),
  KEY `grade_id_fk` (`Grade_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_general_info`
--

LOCK TABLES `student_general_info` WRITE;
/*!40000 ALTER TABLE `student_general_info` DISABLE KEYS */;
INSERT INTO `student_general_info` VALUES (100000,'محمد محمود','عزيز اباظة','2025-04-22',1,1),(200000,'Ahmed','ggggggg','2021-04-08',1,1),(200001,'vdfgsdf','asdfasdg','2021-04-08',2,3),(200002,'fgbnvc fghfhg','asdfasdg','2021-04-08',2,3),(300001,'sdfgsdfgsdfgsdfg','asdfggasdf','2021-07-09',3,3),(500002,'xxxxxxx','sdsssdsds','2021-07-15',5,2),(500003,'aaaaaaa','fffff','2021-07-08',5,2),(500004,'ghfghsfghsfd','sdffgsdfghsfgh','2021-07-09',5,2),(1000001,'Mohamed','asdfasdfasd','2021-07-09',6,1);
/*!40000 ALTER TABLE `student_general_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_groups`
--

DROP TABLE IF EXISTS `student_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_groups` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `size` int NOT NULL,
  `Grade_Id` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_groups`
--

LOCK TABLES `student_groups` WRITE;
/*!40000 ALTER TABLE `student_groups` DISABLE KEYS */;
INSERT INTO `student_groups` VALUES (1,'مجموعة أ',30,1),(2,'مجموعة ب',40,1),(3,'مجموعة ج',3,2);
/*!40000 ALTER TABLE `student_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_exams`
--

DROP TABLE IF EXISTS `students_exams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students_exams` (
  `Student_Id` int NOT NULL,
  `Exam_ID` int NOT NULL,
  `Exam_Date` date NOT NULL,
  `Student_Degree` double NOT NULL,
  KEY `student_Id_fk_exames` (`Student_Id`),
  KEY `Exam_ID` (`Exam_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_exams`
--

LOCK TABLES `students_exams` WRITE;
/*!40000 ALTER TABLE `students_exams` DISABLE KEYS */;
INSERT INTO `students_exams` VALUES (100000,1,'2021-04-26',40),(200000,6,'2021-07-27',60);
/*!40000 ALTER TABLE `students_exams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-31 16:37:25
