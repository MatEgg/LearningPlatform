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
-- Table structure for table `conceptual_skill`
--

DROP TABLE IF EXISTS `conceptual_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `conceptual_skill` (
  `id_conceptual_skill` int(11) NOT NULL,
  `id_number_skill` int(11) DEFAULT NULL,
  `id_measurement_skill` int(11) DEFAULT NULL,
  `id_randomness_skill` int(11) DEFAULT NULL,
  `id_solving_task_skill` int(11) DEFAULT NULL,
  `id_general_performance_skill` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_conceptual_skill`),
  KEY `id_number_skill_idx` (`id_number_skill`),
  KEY `id_measurement_skill_idx` (`id_measurement_skill`),
  KEY `id_randomness_skill_idx` (`id_randomness_skill`),
  KEY `id_solving_task_skill_idx` (`id_solving_task_skill`),
  KEY `id_general_performance_skill_idx` (`id_general_performance_skill`),
  CONSTRAINT `id_general_performance_skill` FOREIGN KEY (`id_general_performance_skill`) REFERENCES `general_performance_skill` (`id_general_performance_skill`),
  CONSTRAINT `id_measurement_skill` FOREIGN KEY (`id_measurement_skill`) REFERENCES `measurement_skill` (`id_measurement_skill`),
  CONSTRAINT `id_number_skill` FOREIGN KEY (`id_number_skill`) REFERENCES `number_skill` (`id_number_skill`),
  CONSTRAINT `id_randomness_skill` FOREIGN KEY (`id_randomness_skill`) REFERENCES `randomness_skill` (`id_randomness_skill`),
  CONSTRAINT `id_solving_task_skill` FOREIGN KEY (`id_solving_task_skill`) REFERENCES `solving_task_skill` (`id_solving_task_skill`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceptual_skill`
--

LOCK TABLES `conceptual_skill` WRITE;
/*!40000 ALTER TABLE `conceptual_skill` DISABLE KEYS */;
INSERT INTO `conceptual_skill` VALUES (873,870,869,871,872,868),(902,899,898,900,901,897),(931,928,927,929,930,926),(960,957,956,958,959,955),(1134,1131,1130,1132,1133,1129),(1163,1160,1159,1161,1162,1158),(1192,1189,1188,1190,1191,1187),(1221,1218,1217,1219,1220,1216),(1250,1247,1246,1248,1249,1245),(1279,1276,1275,1277,1278,1274);
/*!40000 ALTER TABLE `conceptual_skill` ENABLE KEYS */;
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
