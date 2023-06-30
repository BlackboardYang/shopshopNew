-- MySQL dump 10.13  Distrib 5.7.42, for osx10.18 (x86_64)
--
-- Host: localhost    Database: shopshop
-- ------------------------------------------------------
-- Server version	5.7.42

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,0,1,'相机'),(2,0,1,'生活家电'),(3,0,1,'厨房家电'),(4,0,1,'汽车用品'),(5,0,1,'手机数码'),(6,1,2,'数码相机'),(7,1,2,'胶片相机'),(8,1,2,'单反相机'),(9,1,2,'周边器材'),(10,2,2,'冰箱'),(11,2,2,'彩电'),(12,2,2,'洗衣机'),(13,3,2,'咖啡机'),(14,3,2,'料理机'),(15,3,2,'电饭煲'),(16,4,2,'内饰改造'),(17,4,2,'保养用品'),(18,5,2,'索尼'),(19,5,2,'苹果'),(20,5,2,'华为'),(21,5,2,'小米');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `header`
--

DROP TABLE IF EXISTS `header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `header` (
  `id` double NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `header`
--

LOCK TABLES `header` WRITE;
/*!40000 ALTER TABLE `header` DISABLE KEYS */;
INSERT INTO `header` VALUES (1,'冰箱'),(2,'彩电'),(3,'洗衣机'),(4,'电脑'),(5,'手机'),(6,'游戏机');
/*!40000 ALTER TABLE `header` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `product_count` int(11) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (101,6,3,67,'adsf',2,'a 双方都噶事'),(102,7,3,545,'商品名修改好了',34,'第一个被修改的商品'),(103,7,3,23,'asdva',51,'sd 发给火热同意'),(104,8,3,2,'asvd',145,'e 人格如果色一'),(105,8,3,245,'av',145,'s 特意我让他与'),(106,9,3,243,'avs',1344,'s 额头还是短发换个'),(107,9,3,245,'vasvd',14413,'s 大 V 爸爸儿童画个生日'),(108,10,3,24,'asv',13461,'s 地方说的话萨尔图'),(109,10,3,2,'asva',536,'s 好的环境我让他'),(110,11,3,235,'avv',124,'s 饿的和儿童格瑞特'),(111,11,3,235,'asdv',134,'s 让他侮辱'),(112,12,3,14,'avasd',145.5,'e 人特然后你的他已经'),(113,12,3,24,'asvasdv',15.24,'s 人特别多符合人体'),(114,13,3,12,'asdvasdv',1235134,' 虽然让他和我儿童家具'),(115,13,3,245,'ag4',1234,' 是如何人家截图'),(116,14,3,47,'cq34tv',1235,'sd 如何热热 4 '),(117,14,3,46,'57ybw45',456,'s 让他一颗太阳客厅'),(118,15,3,645,'2b56',2456,'d 附近的发个红包的'),(119,15,3,5,'gwvq3',347.245,'d 发原图哇 4 我也'),(120,16,3,354,'w345',24572,'e 尔耳也特意客厅'),(121,16,3,34,'6w',2452,'d 一天天可统一额头'),(122,17,3,345,'3476w43',245,'d 让他儿童也是让他姐姐'),(123,17,3,22,'7tes',345,'ds 乳液他也他已经'),(124,18,3,123,'se',3456,'l 李会计哦哦排偶有一个你'),(125,18,3,2,'ges5ges',734,'j 广播剧；空军；脚踝； '),(126,19,3,132,'se5h',3546,'kl 活泼好皮肤更'),(127,19,3,54,'aseh5',3,'d 符合他恶意'),(128,20,3,324,'45jj4',2456,'r 图二一说而已他开始'),(129,20,3,32,'e56kj',256,'y 他认识我日子太意外虽然一天'),(133,20,3,323,'rstywrt',4356,'丰田的啊但是发了视频的肌肤 v 地方'),(134,6,3,2,'而儿女',3,' 是对方是否'),(135,7,3,3,'爱的',3,' 打分儿个热我'),(136,8,4,235,'新商品3',4628.7,' 这是一个新商品嘿嘿'),(138,6,3,12,'新商品',123,' 危机发挥了如何够热'),(139,6,3,3,'new product!!!',23.12,'222283648712364597826395048');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_user`
--

DROP TABLE IF EXISTS `shop_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_user` (
  `id` double NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `roles` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_user`
--

LOCK TABLES `shop_user` WRITE;
/*!40000 ALTER TABLE `shop_user` DISABLE KEYS */;
INSERT INTO `shop_user` VALUES (1,'admin','$2a$10$KMS.fR8cE0FZJBZoEQrWE.jt3I/BHqpVl.1AkA385TnXwnDmgTeyK',NULL,NULL),(2,'alex','$2a$10$4LhZW8soy1egIVRaQ0n.ne7ozncNzmRh8lDaTq/Dk9x5SDTx6t7uq',NULL,NULL),(3,'aaaaaa','$2a$10$/OBihi63VlNsou7NFvZwe.Vdse9UbCNiDe9RE5/oWuMBPZ0tet0F6','bla5@gmail.com',NULL),(6,'administrator','$2a$10$4s.kfE/H4pWOoSHcZyOtt.aBrRfMcok7FeEAQWalW2Kv.T0rIMXfO','yangb@126.com',NULL),(8,'aaaaaaa','$2a$10$MKF3Bak.toLY3k3NU.bC2OOj8KaMnmFWLHatXUzpcI.dMVRM7nXje','black5th@gmail.com',NULL),(10,'black','$2a$10$zoOu8Rr5CVWSqrkssEZab.lfBGP9vK.nRfHayq.ijibpHhQ30e3a2','black5thedf@gmail.com',NULL),(11,'qwertt','$2a$10$SLsQdgcfFHPQKXSGJKCBXecYH8w0J6h3o/nf/ZsdKEgDHiNxqqXCW','1234@gmail.com',NULL),(12,'amyamy','$2a$10$ECrPL5MjIrGDKcNRY40KfOHeRZr8yOHmt2dWdk9HUzcIVAkQpGKyO','amyzipei108@gmail.com',NULL),(13,'test','$2a$10$WZC3o7ij0U2wQr7DU/hG2.sJuO0oka0k3D5z/mvv8vIPx1vCmVJiy','black5the@gmail.com',NULL);
/*!40000 ALTER TABLE `shop_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-30  9:40:44
