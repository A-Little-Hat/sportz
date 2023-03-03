CREATE DATABASE  IF NOT EXISTS `sportz` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sportz`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: sportz
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `userorder`
--

DROP TABLE IF EXISTS `userorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userorder` (
  `orderId` bigint NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) DEFAULT NULL,
  `orderPhoneNumber` varchar(255) DEFAULT NULL,
  `orderAddress` varchar(255) DEFAULT NULL,
  `userEmail` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `product` json DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userorder`
--

LOCK TABLES `userorder` WRITE;
/*!40000 ALTER TABLE `userorder` DISABLE KEYS */;
INSERT INTO `userorder` VALUES (55,'Soumyaneel','8569874586','Saithia','sourav@ghosh.com','758465','[{\"quantity\": 1, \"product_id\": 27, \"product_name\": \"Leather Ball\", \"product_price\": 189}]'),(56,'Soumyaneel','8569874586','boubazar','a@a.com','854758','[{\"quantity\": 1, \"product_id\": 56, \"product_name\": \"NIVIA Shuttlecock\", \"product_price\": 59}]'),(57,'Soumyaneel','8547589654','GHANA JANA','soumyasarkar309@gmail.com','854758','[{\"quantity\": 1, \"product_id\": 3, \"product_name\": \"Adidas FS Gloves\", \"product_price\": 1099}]'),(58,'Soumyaneel','9547856214','AKSHAY','soumyasarkar309@gmail.com','700103','[{\"quantity\": 1, \"product_id\": 27, \"product_name\": \"Leather Ball\", \"product_price\": 189}, {\"quantity\": 1, \"product_id\": 28, \"product_name\": \"BS Heavy bat\", \"product_price\": 1899}]'),(59,'Soumyaneel','9512457856','narendrapur ramkrishna mission er pichoner bari','soumyasarkar309@gmail.com','700103','[{\"quantity\": 1, \"product_id\": 27, \"product_name\": \"Leather Ball\", \"product_price\": 189}, {\"quantity\": 1, \"product_id\": 26, \"product_name\": \"Tennis Ball\", \"product_price\": 99}, {\"quantity\": 1, \"product_id\": 28, \"product_name\": \"BS Heavy bat\", \"product_price\": 1899}]'),(60,'Soumyaneel','9851002427','Science City, Kolkata','soumyasarkar309@gmail.com','700102','[{\"quantity\": 1, \"product_id\": 51, \"product_name\": \"YONEX B-4000 Bdminton Racquet\", \"product_price\": 799}, {\"quantity\": 1, \"product_id\": 1, \"product_name\": \"Adidas Brazuca\", \"product_price\": 1500}]'),(61,'Soumyaneel','09851002427','Nasrapara kripamoyeetala Ranaghat Nadia pin 741201','soumyasarkar309@gmail.com','741201','[{\"quantity\": 1, \"product_id\": 8, \"product_name\": \"NIKE Mercurial\", \"product_price\": 4499}]');
/*!40000 ALTER TABLE `userorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-27 23:32:35
