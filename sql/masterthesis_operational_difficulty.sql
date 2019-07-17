-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: masterthesis
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `operational_difficulty`
--

DROP TABLE IF EXISTS `operational_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operational_difficulty` (
  `id_operational_difficulty` int(11) NOT NULL,
  `id_addition_difficulty` int(11) DEFAULT NULL,
  `id_subtraction_difficulty` int(11) DEFAULT NULL,
  `id_multiplication_difficulty` int(11) DEFAULT NULL,
  `id_division_difficulty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_operational_difficulty`),
  KEY `id_addition_difficulty_idx` (`id_addition_difficulty`),
  KEY `id_subtraction_difficulty_idx` (`id_subtraction_difficulty`),
  KEY `id_multiplication_difficulty_idx` (`id_multiplication_difficulty`),
  KEY `id_division_difficulty_idx` (`id_division_difficulty`),
  CONSTRAINT `id_addition_difficulty` FOREIGN KEY (`id_addition_difficulty`) REFERENCES `addition_difficulty` (`id_addition_difficulty`),
  CONSTRAINT `id_division_difficulty` FOREIGN KEY (`id_division_difficulty`) REFERENCES `division_difficulty` (`id_division_difficulty`),
  CONSTRAINT `id_multiplication_difficulty` FOREIGN KEY (`id_multiplication_difficulty`) REFERENCES `multiplication_difficulty` (`id_multiplication_difficulty`),
  CONSTRAINT `id_subtraction_difficulty` FOREIGN KEY (`id_subtraction_difficulty`) REFERENCES `subtraction_difficulty` (`id_subtraction_difficulty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operational_difficulty`
--

LOCK TABLES `operational_difficulty` WRITE;
/*!40000 ALTER TABLE `operational_difficulty` DISABLE KEYS */;
INSERT INTO `operational_difficulty` VALUES (854,850,851,852,853),(883,879,880,881,882),(912,908,909,910,911),(941,937,938,939,940),(970,966,967,968,969),(999,995,996,997,998),(1028,1024,1025,1026,1027),(1057,1053,1054,1055,1056),(1086,1082,1083,1084,1085),(1115,1111,1112,1113,1114),(1144,1140,1141,1142,1143),(1173,1169,1170,1171,1172),(1202,1198,1199,1200,1201),(1231,1227,1228,1229,1230),(1260,1256,1257,1258,1259);
/*!40000 ALTER TABLE `operational_difficulty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13  4:31:49
