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
-- Table structure for table `division_difficulty`
--

DROP TABLE IF EXISTS `division_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `division_difficulty` (
  `id_division_difficulty` int(11) NOT NULL,
  `overall_difficulty` int(11) DEFAULT NULL,
  `id_numerical_skill_types_difficulty_division` int(11) DEFAULT NULL,
  `manual` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_division_difficulty`),
  KEY `id_numerical_skill_types_difficulty_division_idx` (`id_numerical_skill_types_difficulty_division`),
  CONSTRAINT `id_numerical_skill_types_difficulty_division` FOREIGN KEY (`id_numerical_skill_types_difficulty_division`) REFERENCES `numerical_skill_types_difficulty` (`id_numerical_skill_types_difficulty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division_difficulty`
--

LOCK TABLES `division_difficulty` WRITE;
/*!40000 ALTER TABLE `division_difficulty` DISABLE KEYS */;
INSERT INTO `division_difficulty` VALUES (496,34,3,0),(824,50,820,0),(853,20,849,0),(882,50,878,0),(911,50,907,0),(940,50,936,0),(969,50,965,0),(998,50,994,0),(1027,50,1023,0),(1056,50,1052,0),(1085,0,1081,0),(1114,8,1110,1),(1143,50,1139,0),(1172,100,1168,0),(1201,100,1197,1),(1230,14,1226,0),(1259,1,1255,0);
/*!40000 ALTER TABLE `division_difficulty` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13  4:31:50
