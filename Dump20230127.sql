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
INSERT INTO `chapter` VALUES (1,'Vertigo '),(2,'Head outer '),(3,'Head inner '),(4,'Eye outer '),(5,'Eye inner '),(6,'Vision '),(7,'Ear outer '),(8,'Ear inner '),(9,'Hearing '),(10,'Nose outer '),(11,'Nose inner '),(12,'Rhinitis '),(13,'Face '),(14,'Mouth outer '),(15,'Mouth inner '),(16,'Tongue '),(17,'Taste '),(18,'Jaws teeth '),(19,'Appetite '),(20,'Thirst '),(21,'Food drink '),(22,'Throat internal '),(23,'Belching '),(24,'Nausea vomiting '),(25,'Indigestion dyspepsia '),(26,'Stomach '),(27,'Abdomen internal '),(28,'Intestine '),(29,'Anus perineum '),(30,'Stool '),(31,'Abdomen external '),(32,'Groin pubic region '),(33,'New Chapter Test');
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

--
-- Table structure for table `subtitles`
--

DROP TABLE IF EXISTS `subtitles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subtitles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chapterId` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chapterId_idx` (`chapterId`),
  CONSTRAINT `chapterId` FOREIGN KEY (`chapterId`) REFERENCES `chapter` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtitles`
--

LOCK TABLES `subtitles` WRITE;
/*!40000 ALTER TABLE `subtitles` DISABLE KEYS */;
INSERT INTO `subtitles` VALUES (1,1,'dizziness'),(2,1,'giddiness'),(3,1,'waving'),(4,1,'whirling'),(5,1,'tottering'),(6,1,'reeling'),(7,1,'stagger'),(8,1,'sway'),(9,1,'grogginess'),(10,1,'tendency to fall'),(11,2,'cranial bones'),(12,2,'lymph nodes head'),(13,2,'fontanelles'),(14,2,'mastoid'),(15,2,'head lice'),(16,2,'movement involuntary'),(17,2,'rolling'),(18,2,'lifting the head'),(19,2,'holding'),(20,3,'head noise'),(21,3,'migraine'),(22,3,'meningitis'),(23,3,'encephalitis'),(24,3,'apoplexy'),(25,3,'brain tumor'),(26,4,'blepharitis'),(27,5,'cataract'),(28,5,'glaucoma'),(29,6,'squint'),(30,6,'photophobia'),(31,7,'behind the ears'),(32,7,'drilling with fingers'),(33,8,'eustachian tube'),(34,9,'noise'),(35,9,'tinnitus'),(36,9,'hypersensitivity'),(37,9,'deafness'),(38,11,'sinuses'),(39,11,'nasal bones'),(40,11,'sniffing'),(41,11,'snorting'),(42,11,'drilling with fingers'),(43,13,'zygomatic bone'),(44,13,'frontal bone'),(45,15,'soor'),(46,15,'stomatitis'),(47,15,'aphthae'),(48,15,'teething'),(49,22,'pharynx'),(50,22,'tonsils'),(51,22,'pharyngeal tonsils'),(52,24,'gagging'),(53,24,'retching'),(54,24,'nausea'),(55,24,'disgust'),(56,24,'nausea wit h mouth watering'),(57,24,'nausea with'),(58,24,'belching'),(59,24,'seasickness'),(60,24,'motion sickness'),(61,26,'cardia'),(62,26,'esophagus'),(63,26,'pylorus'),(64,26,'stomach inlet/ cardiac opening'),(65,26,'gastritis'),(66,26,'gastroenteritis'),(67,26,'ulcer'),(68,26,'hyperacidity'),(69,26,'singultus'),(70,26,'dyspepsia'),(71,27,'upper abdomen: epigastrium'),(72,27,'pit of stomach'),(73,27,'pit of heart'),(74,27,'hypochondrium: lower rib region'),(75,27,'lower abdomen: hypogastrium'),(76,27,'mesenteric glands'),(77,27,'omentum'),(78,27,'bile ducts'),(79,27,'appendicitis typhlitis'),(80,27,'abdominal grumbling'),(81,27,'abdominal cutting'),(82,27,'ascites'),(83,27,'abdominal ailment ?'),(84,27,'gripes'),(85,27,'stomachache'),(86,28,'bowel'),(87,28,'entrails'),(88,28,'intestine'),(89,28,'Small intestine: duodenum'),(90,28,'jejunum'),(91,28,'ileum'),(92,28,'Ileocecal valve'),(93,28,'Large intestine: appendix cecum'),(94,28,'caecum'),(95,28,'caecum with vermiform appendix'),(96,28,'colon'),(97,28,'rectum  and anal canal'),(98,28,'Ileus intestinal paralysis'),(99,28,'intussusception'),(100,28,'diverticulosis'),(101,28,'proctitis'),(102,28,'distension'),(103,28,'flatulence'),(104,28,'meteorism'),(105,28,'rumbling'),(106,28,'sloshing'),(107,28,'splashing'),(108,28,'irritable'),(109,28,'bowel syndrome'),(110,29,'internal sphincter'),(111,29,'midgut'),(112,29,'anal sphincter'),(113,29,'anal glands'),(114,29,'perineum'),(115,29,'prolapsed bowel'),(116,29,'prolapsed anus'),(117,29,'perineal hernia'),(118,29,'worms'),(119,29,'parasites'),(120,29,'encopresis'),(121,29,'defecation'),(122,29,'tenesmus ani painful urge to defecate'),(123,30,'cholera'),(124,30,'dysentery'),(125,31,'turnout of ribs'),(126,31,'navel'),(127,31,'abdominal muscles'),(128,31,'ribs'),(129,31,'turnout'),(130,31,'flank'),(131,31,'umbilicus'),(132,32,'thigh'),(133,32,'groin'),(134,32,'inguinal glands'),(135,32,'groin'),(136,32,'inner inguinal'),(137,32,'ring abdominal ring'),(138,32,'outer inguinal ring'),(139,32,'inguinal hernia'),(140,32,'pubic bone'),(141,32,'pubic'),(142,32,'mound'),(143,32,'womb lap');
/*!40000 ALTER TABLE `subtitles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-27 16:38:34
