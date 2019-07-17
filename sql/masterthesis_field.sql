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
-- Table structure for table `field`
--

DROP TABLE IF EXISTS `field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `field` (
  `id_Field` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `area` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `width` float DEFAULT NULL,
  `height` float DEFAULT NULL,
  `used` tinyint(4) DEFAULT NULL,
  `name_plural` varchar(45) DEFAULT NULL,
  `name_start` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_Field`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` VALUES (1,'Tennisplatz',1000,40,25,1,0,'Tennisplätzen','Der Tennisplatz'),(2,'Fußballfeld',4050,90,45,2,0,'Fußballfeldern','Das Fußballfeld'),(3,'Volleyballfeld',162,18,9,2,0,'Volleyballfeldern','Das Volleyballfeld'),(4,'Billiardtisch',3,2,1.5,1,0,'Billiardtischen','Der Billiardtisch'),(5,'Schwimmbad',2500,50,50,3,0,'Schwimmbäder','Das Schwimmbad'),(8,'Schreibtisch',1.5,1.5,1,1,0,'Schreibtischen','Der Schreibtisch'),(9,'Garage',18,6,3,3,0,'Garagen','Die Garage'),(10,'Tafel',2,1,2,2,0,'Tafeln','Die Tafel'),(13,'Raum (klein)',60,20,3,4,0,'Räumen','Der Raum'),(14,'Bus',150,30,5,2,0,'Bussen','Der Bus'),(15,'Squashfeld',300,15,30,5,0,'Squashfeldern','Das Squashfeld'),(16,'Kinoleinwand',400,20,20,10,0,'Kinoleinwänden','Die Kinoleinwand'),(17,'Skateboard',0.12,0.6,0.2,0.1,0,'Skateboards','Das Skateboard'),(18,'Longboard',0.28,0.8,0.35,0.2,0,'Longboards','Das Longboard');
/*!40000 ALTER TABLE `field` ENABLE KEYS */;
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
