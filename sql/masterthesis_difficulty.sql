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
-- Table structure for table `difficulty`
--

DROP TABLE IF EXISTS `difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `difficulty` (
  `id_difficulty` int(11) NOT NULL,
  `id_conceptual_difficulty` int(11) DEFAULT NULL,
  `id_operational_difficulty` int(11) DEFAULT NULL,
  `id_concept_difficulty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_difficulty`),
  KEY `conceptual_difficulty` (`id_conceptual_difficulty`),
  KEY `id_operational_difficulty_idx` (`id_operational_difficulty`),
  KEY `id_concept_difficulty_idx` (`id_concept_difficulty`),
  CONSTRAINT `id_concept_difficulty` FOREIGN KEY (`id_concept_difficulty`) REFERENCES `concept_difficulty` (`id_concept_difficulty`),
  CONSTRAINT `id_conceptual_difficulty` FOREIGN KEY (`id_conceptual_difficulty`) REFERENCES `conceptual_difficulty` (`id_conceptual_difficulty`),
  CONSTRAINT `id_operational_difficulty` FOREIGN KEY (`id_operational_difficulty`) REFERENCES `operational_difficulty` (`id_operational_difficulty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `difficulty`
--

LOCK TABLES `difficulty` WRITE;
/*!40000 ALTER TABLE `difficulty` DISABLE KEYS */;
INSERT INTO `difficulty` VALUES (1118,1117,1115,1116),(1147,1146,1144,1145),(1176,1175,1173,1174),(1205,1204,1202,1203),(1234,1233,1231,1232),(1263,1262,1260,1261);
/*!40000 ALTER TABLE `difficulty` ENABLE KEYS */;
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
