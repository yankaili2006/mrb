-- MySQL dump 10.13  Distrib 5.5.37, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: mrb
-- ------------------------------------------------------
-- Server version	5.5.37-0ubuntu0.13.10.1

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
-- Table structure for table `tbbimg`
--

DROP TABLE IF EXISTS `tbbimg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbbimg` (
  `biid` bigint(20) NOT NULL,
  `bid` int(10) NOT NULL,
  `iuri` varchar(20480) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbbimg`
--

LOCK TABLES `tbbimg` WRITE;
/*!40000 ALTER TABLE `tbbimg` DISABLE KEYS */;
INSERT INTO `tbbimg` VALUES (503473,799618,'美人邦学堂.png'),(483510,925927,'相关视频.png');
/*!40000 ALTER TABLE `tbbimg` ENABLE KEYS */;
UNLOCK TABLES;

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
  `iuri` varchar(512) DEFAULT NULL,
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
INSERT INTO `tbbrand` VALUES (283222,29781,'眼部护理系列','产品简介','管店首页.png','祛皱眼部精华','1280元/套','功效','其他说明',20140526215046,0),(283222,799618,'眼部护理系列111','产品简介33','切换店铺.png','祛皱眼部精华','33222元/套','功效','其他说明33',20140526185743,0),(476979,925927,'1','1','有奖反馈.png','1','1','1','1',20140526184532,0);
/*!40000 ALTER TABLE `tbbrand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcity`
--

DROP TABLE IF EXISTS `tbcity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbcity` (
  `cid` bigint(20) NOT NULL,
  `level` int(1) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcity`
--

LOCK TABLES `tbcity` WRITE;
/*!40000 ALTER TABLE `tbcity` DISABLE KEYS */;
INSERT INTO `tbcity` VALUES (1401078764927,1,'上海'),(1401078312450,1,'北京'),(1401078772521,1,'广州');
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
INSERT INTO `tbcode` VALUES (382139,'15901411984','111111111',1,20140514171629),(159375,'15901411984','111111111',1,20140514171629),(29159,'1233','111111111',1,20140520220744),(711457,'111','255200',1,20140520225234),(742466,'111','310226',0,20140520225542),(46366,'11','077531',0,20140520230046),(49204,'11','700504',0,20140520230049),(119238,'```','328517',0,20140520230159),(122437,'111','225013',0,20140520230202),(603735,'55','068772',0,20140520231003),(637857,'er','171868',0,20140520231037),(671591,'1212','721834',0,20140520231111),(688897,'1212','681222',1,20140520230900),(837930,'3','424417',0,20140520231357),(887716,'2','435083',0,20140520231447);
/*!40000 ALTER TABLE `tbcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbfeed`
--

DROP TABLE IF EXISTS `tbfeed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbfeed` (
  `fid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `info` varchar(4096) NOT NULL,
  `date` bigint(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbfeed`
--

LOCK TABLES `tbfeed` WRITE;
/*!40000 ALTER TABLE `tbfeed` DISABLE KEYS */;
INSERT INTO `tbfeed` VALUES (59834,562869,'555',20140529003739);
/*!40000 ALTER TABLE `tbfeed` ENABLE KEYS */;
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
INSERT INTO `tbpcategory` VALUES (893062,'面部护理',20140526131344,1),(168748,'疗效护理',20140514002248,1);
/*!40000 ALTER TABLE `tbpcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbpreview`
--

DROP TABLE IF EXISTS `tbpreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbpreview` (
  `rid` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `text` varchar(512) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbpreview`
--

LOCK TABLES `tbpreview` WRITE;
/*!40000 ALTER TABLE `tbpreview` DISABLE KEYS */;
INSERT INTO `tbpreview` VALUES (309337,476979,562869,'undefined',20140528235149);
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
  `pcid` bigint(20) NOT NULL,
  `cid` bigint(20) NOT NULL,
  `iuri` varchar(512) NOT NULL,
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
INSERT INTO `tbproject` VALUES (283222,'欧莱雅',168748,1401078312450,'项目首页-品牌故事.png','高端客户','北京','大中型店','1988年','普通装','直销',1,0,20140526215501,0),(476979,'欧莱雅',168748,1401078312450,'左侧抽屉导航.png','高端客户','北','大中型店','1988年',NULL,NULL,1,1,20140526215555,0),(507360,'1',893062,1401078764927,'版本检测.png','1','1','1','1','2','2',1,1,20140526215542,0);
/*!40000 ALTER TABLE `tbproject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbucollect`
--

DROP TABLE IF EXISTS `tbucollect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbucollect` (
  `ucid` bigint(20) NOT NULL,
  `uid` int(10) NOT NULL,
  `vid` varchar(32) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbucollect`
--

LOCK TABLES `tbucollect` WRITE;
/*!40000 ALTER TABLE `tbucollect` DISABLE KEYS */;
INSERT INTO `tbucollect` VALUES (1,562869,'593727852328384587',11111),(20140522214159,562869,'593727852328384587',20140522214159),(20140522215812,562869,'593727852328384587',20140522215812),(20140522222003,562869,'593727852328384587',20140522222003),(20140522222104,562869,'593727852328384587',20140522222104);
/*!40000 ALTER TABLE `tbucollect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuoperate`
--

DROP TABLE IF EXISTS `tbuoperate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuoperate` (
  `opid` bigint(20) NOT NULL,
  `uid` bigint(20) NOT NULL,
  `oper` varchar(512) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuoperate`
--

LOCK TABLES `tbuoperate` WRITE;
/*!40000 ALTER TABLE `tbuoperate` DISABLE KEYS */;
INSERT INTO `tbuoperate` VALUES (16916,608197,'增加视频分类',20140531095016);
/*!40000 ALTER TABLE `tbuoperate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuplay`
--

DROP TABLE IF EXISTS `tbuplay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbuplay` (
  `vpid` bigint(20) NOT NULL,
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
INSERT INTO `tbuplay` VALUES (1111,'593727852328384587',562869,'0',20140321042322,'20140321042322'),(20140522220905,'593727852328384587',562869,'1',20140522220905,'1'),(20140522221625,'593727852328384587',562869,'1',20140522221625,'1');
/*!40000 ALTER TABLE `tbuplay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbureview`
--

DROP TABLE IF EXISTS `tbureview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbureview` (
  `vrid` bigint(20) NOT NULL,
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
INSERT INTO `tbureview` VALUES (1,'593727852328384587',1,'1111122222',20140323042321),(1,'593727852328384587',1,'1111122222',20140323042321),(20140522223729,'593727852328384587',562869,'2',20140522223729),(20140528205548,'593727852328384587',562869,'11',20140528205548);
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
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE=MyISAM AUTO_INCREMENT=995813 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (482773,'yankaili2006','15901411984','dddd',0,'C',20140510180802,20140510203950,1),(562869,'abc','111112233333','1111',0,'Z',20140510203922,20140526203414,1),(950133,'coola58','15901411984','000000',0,'C',20140505220910,20140512003523,0),(597783,'1221','1212','1212',0,'Z',20140514113637,20140514113637,1),(608197,'1212','root','root',0,'Z',20140514113648,20140514113648,1),(995811,'1','1','1',0,'Z',20140514141315,20140514141315,1),(78392,'2','2','2',0,'Z',20140514141438,20140514141438,1),(276263,'3','3','3',0,'Z',20140514141756,20140514141756,1),(995812,'w8888','15641190209','654321',0,'Z',20140526203110,20140526203110,1),(501161,'6','6','6',0,'Z',20140514145501,20140526140159,1),(559350,'7','7','7',0,'Z',20140514145559,20140514145559,1),(593289,'8','8','8',0,'Z',20140514145633,20140514145633,1);
/*!40000 ALTER TABLE `tbuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbushare`
--

DROP TABLE IF EXISTS `tbushare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbushare` (
  `usid` bigint(20) NOT NULL,
  `uid` int(10) NOT NULL,
  `vid` varchar(32) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbushare`
--

LOCK TABLES `tbushare` WRITE;
/*!40000 ALTER TABLE `tbushare` DISABLE KEYS */;
INSERT INTO `tbushare` VALUES (20140522222236,562869,'593727852328384587',20140522222236),(20140528205543,562869,'593727852328384587',20140528205543);
/*!40000 ALTER TABLE `tbushare` ENABLE KEYS */;
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
INSERT INTO `tbvcate` VALUES (648194,'专家课堂',20140513185728,1),(836808,'2222',20140526140331,0),(308646,'2323',20140526140508,1),(16870,'测试分类',20140531095016,1);
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
INSERT INTO `tbvideo` VALUES ('593727852328384587',648194,'Z','','','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-593727852328384587/593727865242728667.jpg','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-593727852328384587/593727865242728669.jpg',25030656,1,'2014-05-19 23:28:51','2014-05-19 23:27:18',360,640,159310,'.mp4','周杰伦-天台的月光-国语[www.mtvxz.cn]','',0,'',11,'',0,0,'','',NULL,NULL,20140520004748,20140520004748,1),('592063311985780151',648194,'Z','','','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-592063311985780151/592063324897358963.jpg','http://misc.smvp.cn/snapshot/pub-591771258504619901/ent-592063311985780151/592063324897358965.jpg',194048,1,'2014-05-15 11:48:06','2014-05-15 11:48:02',97,165,10290,'.avi','split','',0,'',11,'',0,0,'','',NULL,NULL,20140520010154,20140520010154,1);
/*!40000 ALTER TABLE `tbvideo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvrelate`
--

DROP TABLE IF EXISTS `tbvrelate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbvrelate` (
  `vrid` int(10) NOT NULL,
  `vida` varchar(32) NOT NULL,
  `vidb` varchar(32) NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvrelate`
--

LOCK TABLES `tbvrelate` WRITE;
/*!40000 ALTER TABLE `tbvrelate` DISABLE KEYS */;
INSERT INTO `tbvrelate` VALUES (1,'593727852328384587','593727852328384587',20140323);
/*!40000 ALTER TABLE `tbvrelate` ENABLE KEYS */;
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

-- Dump completed on 2014-05-31 11:28:34
