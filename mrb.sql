-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: mrb
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.13.10.2

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
  `binfo` varchar(1024) DEFAULT NULL,
  `iid` int(10) DEFAULT NULL,
  `name` varchar(128) NOT NULL,
  `price` varchar(128) DEFAULT NULL,
  `function` varchar(1024) DEFAULT NULL,
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
INSERT INTO `tbbrand` VALUES (283222,116857,'1','1',1,'1','1','1','1',20140512150156,1),(283222,29781,'眼部护理系列','产品简介',0,'祛皱眼部精华','1280元/套','功效','其他说明',20140512144349,1),(283222,799618,'眼部护理系列111','产品简介33',333,'祛皱眼部精华','33222元/套','功效','其他说明33',20140512145616,0);
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
  `uid` int(10) DEFAULT NULL,
  `phone` varchar(20) NOT NULL,
  `code` varchar(10) NOT NULL,
  `status` int(1) DEFAULT NULL,
  `date` bigint(14) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcode`
--

LOCK TABLES `tbcode` WRITE;
/*!40000 ALTER TABLE `tbcode` DISABLE KEYS */;
INSERT INTO `tbcode` VALUES (382139,'15901411984','111111111',1,20140514171629),(159375,'15901411984','111111111',1,20140514171629);
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
  `title` varchar(128) DEFAULT NULL,
  `size` int(10) DEFAULT NULL,
  `suffix` char(10) DEFAULT NULL,
  `width` int(10) DEFAULT NULL,
  `height` int(10) DEFAULT NULL,
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
INSERT INTO `tbpcategory` VALUES (893062,'面部护理-修',20140510220911,1),(245002,'疗效护理',20140510003405,1),(30394,'疗效护理',20140510220921,1),(908877,'疗效护理',20140514001828,1),(912585,'疗效护理',20140514001832,1),(916045,'疗效护理',20140514001836,1),(148418,'疗效护理',20140514002228,1),(151420,'疗效护理',20140514002231,1),(154551,'疗效护理',20140514002234,1),(157753,'疗效护理',20140514002237,1),(161013,'疗效护理',20140514002241,1),(164952,'疗效护理',20140514002244,1),(168748,'疗效护理',20140514002248,1);
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
  `level` varchar(128) DEFAULT NULL,
  `area` varchar(128) DEFAULT NULL,
  `store` varchar(128) DEFAULT NULL,
  `build` varchar(128) DEFAULT NULL,
  `pack` varchar(128) DEFAULT NULL,
  `sale` varchar(128) DEFAULT NULL,
  `chain` int(1) DEFAULT NULL,
  `fee` int(1) DEFAULT NULL,
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
-- Table structure for table `tbucolletc`
--

DROP TABLE IF EXISTS `tbucolletc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbucolletc` (
  `vcid` int(10) NOT NULL,
  `vid` varchar(32) NOT NULL,
  `uid` int(10) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbucolletc`
--

LOCK TABLES `tbucolletc` WRITE;
/*!40000 ALTER TABLE `tbucolletc` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbucolletc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuplay`
--

DROP TABLE IF EXISTS `tbuplay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuplay` (
  `vpid` int(10) NOT NULL,
  `vid` varchar(32) NOT NULL,
  `uid` int(10) NOT NULL,
  `finish` char(1) NOT NULL,
  `date` bigint(20) NOT NULL,
  `laststop` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuplay`
--

LOCK TABLES `tbuplay` WRITE;
/*!40000 ALTER TABLE `tbuplay` DISABLE KEYS */;
INSERT INTO `tbuplay` VALUES (1111,'ZZZZZZZ111111112222222222',995811,'0',20140321042322,'20140321042322');
/*!40000 ALTER TABLE `tbuplay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbureview`
--

DROP TABLE IF EXISTS `tbureview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbureview` (
  `vrid` int(10) NOT NULL,
  `vid` varchar(32) NOT NULL,
  `uid` int(10) NOT NULL,
  `text` varchar(512) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbureview`
--

LOCK TABLES `tbureview` WRITE;
/*!40000 ALTER TABLE `tbureview` DISABLE KEYS */;
INSERT INTO `tbureview` VALUES (1,'ZZZZZZZ111111112222222222',1,'1111122222',20140323042321),(1,'ZZZZZZZ111111112222222222',1,'1111122222',20140323042321);
/*!40000 ALTER TABLE `tbureview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuser`
--

DROP TABLE IF EXISTS `tbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuser` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `uname` varchar(32) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `type` int(1) NOT NULL,
  `status` char(1) NOT NULL,
  `date` bigint(14) NOT NULL,
  `opdate` bigint(14) NOT NULL,
  `operid` int(10) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=995812 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (482773,'yankaili2006','15901411984','dddd',0,'C',20140510180802,20140510203950,1),(562869,'abc','111112233333','2222',0,'Z',20140510203922,20140514224901,1),(950133,'coola58','15901411984','000000',0,'C',20140505220910,20140512003523,0),(454957,'yankaili2006','159333333','abcccc',0,'Z',20140505221735,20140510215219,1),(597783,'1221','1212','1212',0,'Z',20140514113637,20140514113637,1),(608197,'1212','root','root',0,'Z',20140514113648,20140514113648,1),(922356,'aqqq','12','1',0,'Z',20140514132202,20140514132202,1),(995811,'1','1','1',0,'Z',20140514141315,20140514141315,1),(78392,'2','2','2',0,'Z',20140514141438,20140514141438,1),(276263,'3','3','3',0,'Z',20140514141756,20140514141756,1),(346306,'4','4','4',0,'Z',20140514141906,20140514141906,1),(155878,'5','5','5',0,'Z',20140514144915,20140514144915,1),(501161,'6','6','6',0,'Z',20140514145501,20140514145501,1),(559350,'7','7','7',0,'Z',20140514145559,20140514145559,1),(593289,'8','8','8',0,'Z',20140514145633,20140514145633,1);
/*!40000 ALTER TABLE `tbuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvcate`
--

DROP TABLE IF EXISTS `tbvcate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbvcate` (
  `vcid` int(10) NOT NULL,
  `name` varchar(128) NOT NULL,
  `date` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvcate`
--

LOCK TABLES `tbvcate` WRITE;
/*!40000 ALTER TABLE `tbvcate` DISABLE KEYS */;
INSERT INTO `tbvcate` VALUES (648194,'专家课堂',20140513185728,1),(836808,'2222',20140513190036,1);
/*!40000 ALTER TABLE `tbvcate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvideo`
--

DROP TABLE IF EXISTS `tbvideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbvideo` (
  `vid` varchar(32) NOT NULL,
  `vcid` int(10) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `description` varchar(2048) DEFAULT NULL,
  `tags` varchar(512) DEFAULT NULL,
  `snapshot_url` varchar(512) DEFAULT NULL,
  `thumbnail_url` varchar(512) DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `activated` int(1) DEFAULT NULL,
  `modified_time` varchar(20) DEFAULT NULL,
  `created_time` varchar(20) DEFAULT NULL,
  `height` int(10) DEFAULT NULL,
  `width` int(10) DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `extension` varchar(10) DEFAULT NULL,
  `title` varchar(512) DEFAULT NULL,
  `mp4_url` varchar(512) DEFAULT NULL,
  `mp4_expires` int(10) DEFAULT NULL,
  `permanent_url` varchar(512) DEFAULT NULL,
  `permanent_expires` int(10) DEFAULT NULL,
  `m3u8_url` varchar(512) DEFAULT NULL,
  `m3u8_expires` int(10) DEFAULT NULL,
  `zm_file_size` bigint(20) DEFAULT NULL,
  `zm_type` varchar(10) DEFAULT NULL,
  `zm_id` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `teacher` varchar(32) DEFAULT NULL,
  `date` bigint(20) NOT NULL,
  `opdate` bigint(20) NOT NULL,
  `operid` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvideo`
--

LOCK TABLES `tbvideo` WRITE;
/*!40000 ALTER TABLE `tbvideo` DISABLE KEYS */;
INSERT INTO `tbvideo` VALUES ('593727852328384587',0,'Z','','','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-593727852328384587/593727865242728667.jpg','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-593727852328384587/593727865242728669.jpg',25030656,1,'2014-05-19 23:28:51','2014-05-19 23:27:18',360,640,159310,'.mp4','周杰伦-天台的月光-国语[www.mtvxz.cn]','',0,'',11,'',0,0,'','',NULL,NULL,20140520004748,20140520004748,1),('592063311985780151',0,'Z','','','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-592063311985780151/592063324897358963.jpg','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-592063311985780151/592063324897358965.jpg',194048,1,'2014-05-15 11:48:06','2014-05-15 11:48:02',97,165,10290,'.avi','split','',0,'',11,'',0,0,'','',NULL,NULL,20140520010154,20140520010154,1);
/*!40000 ALTER TABLE `tbvideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vvlaststop`
--

DROP TABLE IF EXISTS `vvlaststop`;
/*!50001 DROP VIEW IF EXISTS `vvlaststop`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vvlaststop` (
  `vid` tinyint NOT NULL,
  `uid` tinyint NOT NULL,
  `laststop` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vvplay`
--

DROP TABLE IF EXISTS `vvplay`;
/*!50001 DROP VIEW IF EXISTS `vvplay`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vvplay` (
  `vid` tinyint NOT NULL,
  `cnt` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `vvreview`
--

DROP TABLE IF EXISTS `vvreview`;
/*!50001 DROP VIEW IF EXISTS `vvreview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vvreview` (
  `vid` tinyint NOT NULL,
  `cnt` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vvlaststop`
--

/*!50001 DROP TABLE IF EXISTS `vvlaststop`*/;
/*!50001 DROP VIEW IF EXISTS `vvlaststop`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vvlaststop` AS (select `tbuplay`.`vid` AS `vid`,`tbuplay`.`uid` AS `uid`,max(`tbuplay`.`laststop`) AS `laststop` from `tbuplay` group by `tbuplay`.`vid`,`tbuplay`.`uid`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vvplay`
--

/*!50001 DROP TABLE IF EXISTS `vvplay`*/;
/*!50001 DROP VIEW IF EXISTS `vvplay`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vvplay` AS (select `tbuplay`.`vid` AS `vid`,count(1) AS `cnt` from `tbuplay` group by `tbuplay`.`vid`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vvreview`
--

/*!50001 DROP TABLE IF EXISTS `vvreview`*/;
/*!50001 DROP VIEW IF EXISTS `vvreview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vvreview` AS (select `tbureview`.`vid` AS `vid`,count(1) AS `cnt` from `tbureview`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-20  1:03:03
