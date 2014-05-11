-- MySQL dump 10.11
--
-- Host: localhost    Database: mrb
-- ------------------------------------------------------
-- Server version	5.0.83-community-nt

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
-- Table structure for table `tbbrand`
--

DROP TABLE IF EXISTS `tbbrand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbbrand` (
  `pid` int(10) NOT NULL,
  `bid` int(10) NOT NULL,
  `btitle` varchar(128) NOT NULL,
  `binfo` varchar(1024) default NULL,
  `iid` int(10) default NULL,
  `name` varchar(128) NOT NULL,
  `price` varchar(128) default NULL,
  `function` varchar(1024) default NULL,
  `summary` varchar(1024) NOT NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbbrand`
--

LOCK TABLES `tbbrand` WRITE;
/*!40000 ALTER TABLE `tbbrand` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbbrand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcity`
--

DROP TABLE IF EXISTS `tbcity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbcity` (
  `cid` int(10) NOT NULL,
  `level` int(1) NOT NULL,
  `name` varchar(64) NOT NULL,
  `pid` int(10) NOT NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcity`
--

LOCK TABLES `tbcity` WRITE;
/*!40000 ALTER TABLE `tbcity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbcity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcode`
--

DROP TABLE IF EXISTS `tbcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbcode` (
  `uid` int(10) default NULL,
  `phone` varchar(20) NOT NULL,
  `code` varchar(10) NOT NULL,
  `status` int(1) default NULL,
  `date` bigint(14) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcode`
--

LOCK TABLES `tbcode` WRITE;
/*!40000 ALTER TABLE `tbcode` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbimage`
--

DROP TABLE IF EXISTS `tbimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbimage` (
  `iid` int(10) NOT NULL,
  `uri` varchar(512) NOT NULL,
  `title` varchar(128) default NULL,
  `size` int(10) default NULL,
  `suffix` char(10) default NULL,
  `width` int(10) default NULL,
  `height` int(10) default NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbimage`
--

LOCK TABLES `tbimage` WRITE;
/*!40000 ALTER TABLE `tbimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpcategory`
--

DROP TABLE IF EXISTS `tbpcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbpcategory` (
  `cid` int(10) NOT NULL,
  `name` varchar(128) NOT NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpcategory`
--

LOCK TABLES `tbpcategory` WRITE;
/*!40000 ALTER TABLE `tbpcategory` DISABLE KEYS */;
INSERT INTO `tbpcategory` VALUES (893062,'面部护理-修',20140510220911,1),(245002,'疗效护理',20140510003405,1),(30394,'疗效护理',20140510220921,1);
/*!40000 ALTER TABLE `tbpcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpimg`
--

DROP TABLE IF EXISTS `tbpimg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbpimg` (
  `bid` int(10) NOT NULL,
  `iid` int(10) NOT NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpimg`
--

LOCK TABLES `tbpimg` WRITE;
/*!40000 ALTER TABLE `tbpimg` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbpimg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpreview`
--

DROP TABLE IF EXISTS `tbpreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbpreview` (
  `rid` int(10) NOT NULL,
  `bid` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `text` varchar(512) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpreview`
--

LOCK TABLES `tbpreview` WRITE;
/*!40000 ALTER TABLE `tbpreview` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbpreview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbproject`
--

DROP TABLE IF EXISTS `tbproject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbproject` (
  `pid` int(10) NOT NULL,
  `name` varchar(128) NOT NULL,
  `iid` int(10) NOT NULL,
  `level` varchar(128) default NULL,
  `area` varchar(128) default NULL,
  `store` varchar(128) default NULL,
  `build` varchar(128) default NULL,
  `pack` varchar(128) default NULL,
  `sale` varchar(128) default NULL,
  `chain` int(1) default NULL,
  `fee` int(1) default NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbproject`
--

LOCK TABLES `tbproject` WRITE;
/*!40000 ALTER TABLE `tbproject` DISABLE KEYS */;
INSERT INTO `tbproject` VALUES (283222,'欧莱雅',10000,'高端客户','北京','大中型店','1988年','普通装','直销',1,0,20140511002222,0),(476979,'欧莱雅',10000,'高端客户','北','大中型店','1988年',NULL,NULL,1,1,20140511001502,0);
/*!40000 ALTER TABLE `tbproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbstore`
--

DROP TABLE IF EXISTS `tbstore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbstore` (
  `sid` int(10) NOT NULL,
  `name` varchar(128) NOT NULL,
  `cid` int(10) NOT NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbstore`
--

LOCK TABLES `tbstore` WRITE;
/*!40000 ALTER TABLE `tbstore` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbstore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuser`
--

DROP TABLE IF EXISTS `tbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuser` (
  `uid` int(10) NOT NULL auto_increment,
  `uname` varchar(32) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `type` int(1) NOT NULL,
  `status` char(1) NOT NULL,
  `date` bigint(14) NOT NULL,
  `opdate` bigint(14) NOT NULL,
  `operid` int(10) NOT NULL,
  PRIMARY KEY  (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=950134 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (482773,'yankaili2006','15901411984','dddd',0,'C',20140510180802,20140510203950,1),(562869,'abc','111112233333','1111',0,'Z',20140510203922,20140510203922,1),(950133,'coola58','15901411984','000000',0,'Z',20140505220910,20140505221910,0),(454957,'yankaili2006','159333333','abcccc',0,'Z',20140505221735,20140510215219,1);
/*!40000 ALTER TABLE `tbuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-11 11:46:52
