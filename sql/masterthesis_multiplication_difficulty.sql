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
-- Table structure for table `multiplication_difficulty`
--

DROP TABLE IF EXISTS `multiplication_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `multiplication_difficulty` (
  `id_multiplication_difficulty` int(11) NOT NULL,
  `overall_difficulty` int(11) DEFAULT NULL,
  `id_numerical_skill_types_difficulty_multiplication` int(11) DEFAULT NULL,
  `manual` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_multiplication_difficulty`),
  KEY `id_numerical_skill_types_difficulty_multiplication_idx` (`id_numerical_skill_types_difficulty_multiplication`),
  CONSTRAINT `id_numerical_skill_types_difficulty_multiplication` FOREIGN KEY (`id_numerical_skill_types_difficulty_multiplication`) REFERENCES `numerical_skill_types_difficulty` (`id_numerical_skill_types_difficulty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multiplication_difficulty`
--

LOCK TABLES `multiplication_difficulty` WRITE;
/*!40000 ALTER TABLE `multiplication_difficulty` DISABLE KEYS */;
INSERT INTO `multiplication_difficulty` VALUES (495,33,4,0),(659,50,655,0),(687,50,683,0),(700,50,696,0),(713,50,709,0),(726,50,722,0),(739,50,735,0),(752,50,748,0),(780,50,776,0),(793,50,789,0),(823,50,819,0),(852,20,848,0),(881,50,877,0),(910,73,906,0),(939,50,935,0),(968,50,964,0),(997,50,993,0),(1026,50,1022,0),(1055,50,1051,0),(1084,0,1080,0),(1113,8,1109,1),(1142,29,1138,1),(1171,12,1167,1),(1200,100,1196,1),(1229,20,1225,0),(1258,1,1254,0);
/*!40000 ALTER TABLE `multiplication_difficulty` ENABLE KEYS */;
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
