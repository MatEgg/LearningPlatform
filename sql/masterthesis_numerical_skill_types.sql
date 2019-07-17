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
-- Table structure for table `numerical_skill_types`
--

DROP TABLE IF EXISTS `numerical_skill_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `numerical_skill_types` (
  `id_numerical_skill_types` int(11) NOT NULL,
  `decimal_handling` int(11) NOT NULL DEFAULT '1',
  `big_number_handling` int(11) NOT NULL DEFAULT '1',
  `numerical_type_handling` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_numerical_skill_types`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `numerical_skill_types`
--

LOCK TABLES `numerical_skill_types` WRITE;
/*!40000 ALTER TABLE `numerical_skill_types` DISABLE KEYS */;
INSERT INTO `numerical_skill_types` VALUES (1120,110,250,110),(1121,110,250,110),(1122,110,250,110),(1123,110,250,110),(1149,220,470,170),(1150,220,550,220),(1151,220,730,330),(1152,220,550,220),(1178,350,750,330),(1179,350,750,330),(1180,350,750,330),(1181,350,750,330),(1207,499,1099,499),(1208,499,1099,499),(1209,499,1099,499),(1210,499,1099,499),(1236,499,1099,499),(1237,1,1,1),(1238,1,1,1),(1239,1,1,1),(1265,1,1,1),(1266,1,1,1),(1267,1,1,1),(1268,1,1,1);
/*!40000 ALTER TABLE `numerical_skill_types` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13  4:31:53
