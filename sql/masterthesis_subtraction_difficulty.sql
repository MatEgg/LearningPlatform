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
-- Table structure for table `subtraction_difficulty`
--

DROP TABLE IF EXISTS `subtraction_difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subtraction_difficulty` (
  `id_subtraction_difficulty` int(11) NOT NULL,
  `overall_difficulty` int(11) DEFAULT NULL,
  `id_numerical_skill_types_difficulty_subtraction` int(11) DEFAULT NULL,
  `manual` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_subtraction_difficulty`),
  KEY `id_numerical_skill_types_difficulty_idx` (`id_numerical_skill_types_difficulty_subtraction`),
  CONSTRAINT `id_numerical_skill_types_difficulty_subtraction` FOREIGN KEY (`id_numerical_skill_types_difficulty_subtraction`) REFERENCES `numerical_skill_types_difficulty` (`id_numerical_skill_types_difficulty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtraction_difficulty`
--

LOCK TABLES `subtraction_difficulty` WRITE;
/*!40000 ALTER TABLE `subtraction_difficulty` DISABLE KEYS */;
INSERT INTO `subtraction_difficulty` VALUES (494,41,2,0),(658,1,654,0),(686,1,682,0),(699,1,695,0),(712,1,708,0),(725,1,721,0),(738,1,734,0),(751,1,747,0),(779,1,775,0),(792,1,788,0),(822,1,818,0),(851,20,847,0),(880,52,876,0),(909,50,905,0),(938,0,934,0),(967,1,963,0),(996,1,992,0),(1025,1,1021,0),(1054,1,1050,0),(1083,0,1079,0),(1112,12,1108,1),(1141,50,1137,0),(1170,76,1166,0),(1199,100,1195,0),(1228,18,1224,0),(1257,1,1253,0);
/*!40000 ALTER TABLE `subtraction_difficulty` ENABLE KEYS */;
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
