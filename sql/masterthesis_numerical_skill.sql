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
-- Table structure for table `numerical_skill`
--

DROP TABLE IF EXISTS `numerical_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `numerical_skill` (
  `id_numerical_skill` int(11) NOT NULL,
  `id_addition_skill` int(11) NOT NULL,
  `id_subtraction_skill` int(11) NOT NULL,
  `id_multiplication_skill` int(11) NOT NULL,
  `id_division_skill` int(11) NOT NULL,
  PRIMARY KEY (`id_numerical_skill`),
  KEY `id_addition_skill_idx` (`id_addition_skill`),
  KEY `id_subtraction_skill_idx` (`id_subtraction_skill`),
  KEY `id_multiplication_skill_idx` (`id_multiplication_skill`),
  KEY `id_division_skill_idx` (`id_division_skill`),
  CONSTRAINT `id_addition_skill` FOREIGN KEY (`id_addition_skill`) REFERENCES `addition_skill` (`id_addition_skill`),
  CONSTRAINT `id_division_skill` FOREIGN KEY (`id_division_skill`) REFERENCES `division_skill` (`id_division_skill`),
  CONSTRAINT `id_multiplication_skill` FOREIGN KEY (`id_multiplication_skill`) REFERENCES `multiplication_skill` (`id_multiplication_skill`),
  CONSTRAINT `id_subtraction_skill` FOREIGN KEY (`id_subtraction_skill`) REFERENCES `subtraction_skill` (`id_subtraction_skill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `numerical_skill`
--

LOCK TABLES `numerical_skill` WRITE;
/*!40000 ALTER TABLE `numerical_skill` DISABLE KEYS */;
INSERT INTO `numerical_skill` VALUES (1128,1124,1125,1126,1127),(1157,1153,1154,1155,1156),(1186,1182,1183,1184,1185),(1215,1211,1212,1213,1214),(1244,1240,1241,1242,1243),(1273,1269,1270,1271,1272);
/*!40000 ALTER TABLE `numerical_skill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13  4:31:51
