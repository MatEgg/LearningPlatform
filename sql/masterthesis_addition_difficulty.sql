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
-- Table structure for table `addition_difficulty`
--

DROP TABLE IF EXISTS `addition_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `addition_difficulty` (
  `id_addition_difficulty` int(11) NOT NULL,
  `overall_difficulty` int(11) DEFAULT NULL,
  `id_numerical_skill_types_difficulty_addition` int(11) DEFAULT NULL,
  `manual` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_addition_difficulty`),
  KEY `id_numerical_skill_types_difficulty_idx` (`id_numerical_skill_types_difficulty_addition`),
  CONSTRAINT `id_numerical_skill_types_difficulty_addition` FOREIGN KEY (`id_numerical_skill_types_difficulty_addition`) REFERENCES `numerical_skill_types_difficulty` (`id_numerical_skill_types_difficulty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addition_difficulty`
--

LOCK TABLES `addition_difficulty` WRITE;
/*!40000 ALTER TABLE `addition_difficulty` DISABLE KEYS */;
INSERT INTO `addition_difficulty` VALUES (850,20,846,0),(879,50,875,0),(908,54,904,0),(937,50,933,0),(966,1,962,0),(995,1,991,0),(1024,1,1020,0),(1053,1,1049,0),(1082,0,1078,0),(1111,16,1107,1),(1140,49,1136,0),(1169,74,1165,0),(1198,100,1194,0),(1227,16,1223,0),(1256,1,1252,0);
/*!40000 ALTER TABLE `addition_difficulty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13  4:31:52
