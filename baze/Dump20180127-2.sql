CREATE DATABASE  IF NOT EXISTS `prodavnice` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `prodavnice`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: prodavnice
-- ------------------------------------------------------
-- Server version	5.7.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artikal`
--

DROP TABLE IF EXISTS `artikal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artikal` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) NOT NULL,
  `Tip` varchar(255) NOT NULL DEFAULT '',
  `Cena` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikal`
--

LOCK TABLES `artikal` WRITE;
/*!40000 ALTER TABLE `artikal` DISABLE KEYS */;
INSERT INTO `artikal` VALUES (1,'LG255c','Televizor',23000),(2,'VOX611c','Televizor',27400),(3,'Vivax-gh55','Televizor',17999),(4,'Sony-Trinitron','Televizor',52000),(5,'Genelec1031a','Zvucnici',145000),(6,'YamahaNS10m','Zvucnici',9999),(7,'LG-Lite','Telefon',15000),(8,'Huawei55','Telefon',25000),(9,'Pentium4','Kompjuter',200000),(10,'Fujitsu-Siemens','Laptop',75599);
/*!40000 ALTER TABLE `artikal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodavnica`
--

DROP TABLE IF EXISTS `prodavnica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prodavnica` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(255) NOT NULL,
  `Adresa` varchar(255) NOT NULL DEFAULT '',
  `Tel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodavnica`
--

LOCK TABLES `prodavnica` WRITE;
/*!40000 ALTER TABLE `prodavnica` DISABLE KEYS */;
INSERT INTO `prodavnica` VALUES (1,'Tehnomanija','TC Zmaj',1234567),(2,'Gigatron','TC Usce',1234567),(3,'List Computers','Cara Dusana 75',1234567),(4,'Tehnomarket','Ivana Cankara bb',1346597);
/*!40000 ALTER TABLE `prodavnica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promet`
--

DROP TABLE IF EXISTS `promet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promet` (
  `Prodavnica_ID` int(11) NOT NULL DEFAULT '0',
  `Datum` date NOT NULL,
  `Kolicina` int(11) NOT NULL DEFAULT '0',
  `Iznos` int(11) NOT NULL DEFAULT '0',
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_promet_Prodavnica` (`Prodavnica_ID`),
  CONSTRAINT `fk_promet_Prodavnica` FOREIGN KEY (`Prodavnica_ID`) REFERENCES `prodavnica` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promet`
--

LOCK TABLES `promet` WRITE;
/*!40000 ALTER TABLE `promet` DISABLE KEYS */;
INSERT INTO `promet` VALUES (1,'2018-01-20',4,285100,1),(1,'2018-01-22',12,1140000,2),(2,'2018-01-23',11,242000,3),(1,'2018-01-25',15,197000,4);
/*!40000 ALTER TABLE `promet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rezervacija`
--

DROP TABLE IF EXISTS `rezervacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rezervacija` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Prodavnica_ID` int(11) NOT NULL DEFAULT '0',
  `Artikal_ID` int(11) NOT NULL DEFAULT '0',
  `Datum` date NOT NULL,
  `Kolicina` int(11) NOT NULL DEFAULT '0',
  `Ime` varchar(255) NOT NULL DEFAULT '',
  `Tel` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `fk_rezervacija_Prodavnica` (`Prodavnica_ID`),
  KEY `fk_rezervacija_Artikal` (`Artikal_ID`),
  CONSTRAINT `fk_rezervacija_Artikal` FOREIGN KEY (`Artikal_ID`) REFERENCES `artikal` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rezervacija_Prodavnica` FOREIGN KEY (`Prodavnica_ID`) REFERENCES `prodavnica` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezervacija`
--

LOCK TABLES `rezervacija` WRITE;
/*!40000 ALTER TABLE `rezervacija` DISABLE KEYS */;
INSERT INTO `rezervacija` VALUES (1,1,4,'2018-01-26',50,'Boban',1234567),(2,1,10,'2018-01-27',70,'Andrija',631234);
/*!40000 ALTER TABLE `rezervacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sadrzi`
--

DROP TABLE IF EXISTS `sadrzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sadrzi` (
  `Prodavnica_ID` int(11) NOT NULL DEFAULT '0',
  `Artikal_ID` int(11) NOT NULL DEFAULT '0',
  `Kolicina` int(11) NOT NULL DEFAULT '0',
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_sadrzi_Prodavnica` (`Prodavnica_ID`),
  KEY `fk_sadrzi_Artikal` (`Artikal_ID`),
  CONSTRAINT `fk_sadrzi_Artikal` FOREIGN KEY (`Artikal_ID`) REFERENCES `artikal` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sadrzi_Prodavnica` FOREIGN KEY (`Prodavnica_ID`) REFERENCES `prodavnica` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sadrzi`
--

LOCK TABLES `sadrzi` WRITE;
/*!40000 ALTER TABLE `sadrzi` DISABLE KEYS */;
INSERT INTO `sadrzi` VALUES (1,5,3,1),(1,6,10,2),(2,6,10,3),(1,4,11,4),(3,2,7,5),(2,1,10,6),(1,3,19,7),(1,1,16,8),(1,9,9,9),(1,8,5,10),(3,10,7,11);
/*!40000 ALTER TABLE `sadrzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'prodavnice'
--

--
-- Dumping routines for database 'prodavnice'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-27  7:09:56
