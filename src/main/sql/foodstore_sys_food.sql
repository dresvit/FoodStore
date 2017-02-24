-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: foodstore
-- ------------------------------------------------------
-- Server version	5.5.52

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
-- Table structure for table `sys_food`
--

DROP TABLE IF EXISTS `sys_food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_food` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `foodname` varchar(100) DEFAULT NULL,
  `price` varchar(100) DEFAULT NULL,
  `quantity` int(100) DEFAULT NULL,
  `isvip` tinyint(1) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_food_foodname` (`foodname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_food`
--

LOCK TABLES `sys_food` WRITE;
/*!40000 ALTER TABLE `sys_food` DISABLE KEYS */;
INSERT INTO `sys_food` VALUES
  (1, "五香土豆", "12.00", 30, false, "优质土豆，含有大量淀粉以及蛋白质、B族维生素、维生素C等，能促进脾胃的消化功能，和中养胃、健脾利湿。同时，土豆能供给人体大量有特殊保护作用的黏液蛋白，能保持消化道、呼吸道以及关节腔、浆膜腔的润滑，降糖降脂，美容养颜。加之花椒、丁香、陈皮、八角、肉桂等五香调料的香气，可谓香飘十里，五味俱全。"),
  (2, "椒盐鸡翅", "18.00", 20, true, "精选优质鸡翅，肉质鲜嫩爽滑，蛋白质含量高，易被人体吸收利用，有增强体力，强壮身体的功效。配以椒盐的花椒香气溢出，香喷的气味马上会进入你的五脏六腑，令你“口水直流三千尺”。"),
  (3, "西兰花炒虾仁", "30.00", 5, true, "选用活虾为原料，去掉虾头虾尾，剥壳后的纯虾肉制作而成，新鲜、无毒、无污染，色泽纯正，形态美观，口感鲜嫩，清淡爽口，易于消化。虾仁中含有20%的蛋白质，是鱼、蛋、奶的几倍甚至十几倍，营养丰富。西兰花更是具有防癌抗癌的功效，维生素C丰富，质嫩爽脆，美味可口。"),
  (4, "秘制羊肉串", "22.00", 15, true, "羊肉较牛肉的肉质要细嫩，容易消化，高蛋白、低脂肪、含磷脂多，较猪肉和牛肉的脂肪含量都要少，胆固醇含量少，是冬季防寒温补的美味之一，可收到进补和防寒的双重效果。羊后腿肉，肉中夹筋，筋肉相连，酱制而成。配以秘制烧烤汁，以多种天然香辛料的浸提液为基料，加多种辅料调配而成，风格独特，深受广大食客欢迎。"),
  (5, "铁板烧肉", "28.00", 10, true, "铁板烧是在十五、六世纪时由西班牙人所发明，并于二十世纪初由日裔美国人引进日本加以改良，成为今日名躁一时的日式铁板烧。选用优质扒肉，配以适量油盐酱油，通过高热的铁板快速烹调成熟以保留其本身的营养和味道，肥腴鲜美，多汁且耐嚼。"),
  (6, "脆皮鸡翅", "15.00", 20, false, "选用新鲜翅中，胶原蛋白含量丰富，对于保持皮肤光泽、增强皮肤弹性均有好处。并用少许盐五香粉花椒粉拌均匀腌制入味，沾上淀粉入锅油炸而成，香脆可口，色味俱佳，妙不可言。"),
  (7, "麻辣鸡翅", "16.00", 20, true, "优质鸡翅，鸡肉色泽鲜嫩，并采用新鲜麻椒、辣椒腌制而成，看起来娇嫩欲滴，闻起来浓香四溢，尝起来辣气却是愈演愈烈，直至意犹未尽，不得不望辣兴叹矣，鲜香麻辣，酣畅淋漓。"),
  (8, "宫保鸡丁", "25.00", 10, false, "选用鸡肉为主料，佐以花生米、黄瓜、辣椒等辅料烹制而成，麻辣鲜香，回味无穷。鸡丁富含蛋白质、钙、磷、铁、维生素及碳水化合物等营养成分，具有温中益气、滋补五脏、健脾胃、壮筋骨的功效。食之可养身滋补、增进食欲、促进人体健康、增强机体抵抗能力。"),
  (9, "酸甜排骨", "32.00", 5, true, "选用新鲜猪子排作料，肉质鲜嫩，成莱色泽红亮油润，口味香脆酸甜，颇受江南一带食者的欢迎，是一款极好的下酒菜或是开胃菜。排骨除含蛋白质、脂肪、维生素外，还含有大量磷酸钙、骨胶原、骨粘蛋白等，可为幼儿和老人提供钙质。");
/*!40000 ALTER TABLE `sys_food` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
