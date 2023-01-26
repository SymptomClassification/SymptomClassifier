-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (x86_64)
--
-- Host: dagere.comiles.eu    Database: classification
-- ------------------------------------------------------
-- Server version	5.7.41

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
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (1,'Vertigo '),(2,'Head outer '),(3,'Head inner '),(4,'Eye outer '),(5,'Eye inner '),(6,'Vision '),(7,'Ear outer '),(8,'Ear inner '),(9,'Hearing '),(10,'Nose outer '),(11,'Nose inner '),(12,'Rhinitis '),(13,'Face '),(14,'Mouth outer '),(15,'Mouth inner '),(16,'Tongue '),(17,'Taste '),(18,'Jaws teeth '),(19,'Appetite '),(20,'Thirst '),(21,'Food drink '),(22,'Throat internal '),(23,'Belching '),(24,'Nausea vomiting '),(25,'Indigestion dyspepsia '),(26,'Stomach '),(27,'Abdomen internal '),(28,'Intestine '),(29,'Anus perineum '),(30,'Stool '),(31,'Abdomen external '),(32,'Groin pubic region ');
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subchapters`
--

DROP TABLE IF EXISTS `subchapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subchapters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `chapterId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chapterId` (`chapterId`),
  CONSTRAINT `subchapters_ibfk_1` FOREIGN KEY (`chapterId`) REFERENCES `chapter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subchapters`
--

LOCK TABLES `subchapters` WRITE;
/*!40000 ALTER TABLE `subchapters` DISABLE KEYS */;
INSERT INTO `subchapters` VALUES (1,'General',2),(2,'Temples',2),(3,'Sides',2),(4,'Vertex, parietal region',2),(5,'Occiput',2),(6,'Scalp',2),(7,'Hair',2),(8,'Head, movements of',2),(9,'General',3),(10,'Forehead',3),(11,'Temples',3),(12,'Sides, one -sided',3),(13,'Occiput',3),(14,'Vertex, Parietal bone',3),(15,'Brain',3),(16,'General',4),(17,'Pupils',4),(18,'Conjunctiva',4),(19,'Cornea',4),(20,'Eyelids',4),(21,'- upper',4),(22,'Eyelid margins',4),(23,'Corner of the eyes',4),(24,'- outer',4),(25,'Eyebrows',4),(26,'Eye surrounding',4),(27,'Tears',4),(28,'General',5),(29,'Orbit',5),(30,'Eyeball',5),(31,'Retina',5),(32,'General',7),(33,'Auricles  & Earlobe s',7),(34,'Auditory meatus',7),(35,'Ear, surrounding region',7),(36,'General',8),(37,'Otitis',8),(38,'Discharge',8),(39,'General',10),(40,'Nasal wings',10),(41,'Nostrils',10),(42,'Tip of nose',10),(43,'Nose root',10),(44,'Nose, surrounding region',10),(45,'General',11),(46,'Nasal septum',11),(47,'Nosebleeds',11),(48,'Secretion',11),(49,'Crusts',11),(50,'Smell from the nose',11),(51,'Sneezing',11),(52,'Smelling',11),(53,'General',12),(54,'Runny nose',12),(55,'Sticky rhinitis',12),(56,'Secretion',12),(57,'General',13),(58,'Forehead',13),(59,'Temples',13),(60,'Parotid gland',13),(61,'Cheeks',13),(62,'Beard',13),(63,'Chin',13),(64,'General',14),(65,'Corner of the mouth',14),(66,'Lips',14),(67,'- upper',14),(68,'Mouth, surrounding region',14),(69,'General',15),(70,'inner cheeks',15),(71,'Oral cavity',15),(72,'Palate',15),(73,'Soft palate, uvula',15),(74,'Saliva',15),(75,'- increase',15),(76,'Bad breath',15),(77,'General',16),(78,'Tongue coating',16),(79,'General',17),(80,'Subtle',17),(81,'Weak/ lost',17),(82,'Changed',17),(83,'General',18),(84,'Upper  jaw',18),(85,'Lower  jaw',18),(86,'Temporomandibular joint',18),(87,'Gums',18),(88,'Teeth',18),(89,'- molar teeth',18),(90,'molar teeth',18),(91,'General',19),(92,'Loss of appetite',19),(93,'Hunger',19),(94,'Cravings',19),(95,'General (e.g. desire to drink without thirst)',20),(96,'Thirstlessness',20),(97,'General',21),(98,'Aversion to',21),(99,'Craving for',21),(100,'Aggravation by or intolerable',21),(101,'Improvement through',21),(102,'General',24),(103,'Type of vomit',24),(104,'General',26),(105,'Heartburn',26),(106,'Hiccups',26),(107,'General',27),(108,'Peritoneum, abdominal mesh',27),(109,'Mesentery',27),(110,'Upper abdomen',27),(111,'- Liver',27),(112,'- Spleen',27),(113,'Hypochondria',27),(114,'navel',27),(115,'Umbilicus',27),(116,'Sides of abdomen',27),(117,'Lower abdomen, small pelvis',27),(118,'General',28),(119,'Duodenum',28),(120,'Small intestine',28),(121,'Large intestine',28),(122,'- Colon',28),(123,'Flatulence',28),(124,'- Flatulenc y',28),(125,'anus',29),(126,'General',29),(127,'Perineum',29),(128,'Hemorrhoids',29),(129,'General',30),(130,'u rge to defecate',30),(131,'Rectal tenesmus',30),(132,'trigger',30),(133,'Diarrhea',30),(134,'Constipation',30),(135,'Stool consistency',30),(136,'- Constipation',30),(137,'General',31),(138,'Upper abdomen',31),(139,'Sides',31),(140,'Ribs',31),(141,'Umbilicus',31),(142,'Abdominal wall',31),(143,'Groin',32),(144,'Pubic bone',32),(145,'Lap',32);
/*!40000 ALTER TABLE `subchapters` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-26  2:53:29
