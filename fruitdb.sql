/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 8.0.26 : Database - fruitdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fruitdb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fruitdb`;

/*Table structure for table `t_fruit` */

DROP TABLE IF EXISTS `t_fruit`;

CREATE TABLE `t_fruit` (
  `fid` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `price` int DEFAULT NULL,
  `fcount` int DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_fruit` */

insert  into `t_fruit`(`fid`,`fname`,`price`,`fcount`,`remark`) values (1,'红富士',6,16,'红富士也是苹果!'),(2,'大瓜',5,100,'王校长的瓜真香'),(3,'南瓜',4,456,'水果真好吃'),(4,'苦瓜',5,55,'苦瓜很好吃'),(5,'莲雾',9,99,'莲雾是一种神奇的水果'),(6,'羊角蜜',4,30,'羊角蜜是一种神奇的瓜'),(7,'啃大瓜',13,123,'孤瓜'),(34,'苹果',5,50,'阿克苏'),(35,'萝卜',9,99,'红'),(36,'山竹',25,30,'紫皮水果');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
