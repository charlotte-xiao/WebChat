/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.31 : Database - webchat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`webchat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `webchat`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`pwd`) values ('10005','jojoke2','05'),('10003','今日解签,宜下江南','03'),('10009','最最最可爱','09'),('10008','没有指针的钟','08'),('10012','潇潇雨歇','12'),('10004','瑞雪兆丰年','04'),('10002','相留醉','02'),('10011','秋吉','11'),('10001','第三颗松果','01'),('10006','花间酒','06'),('10007','霸气外露','07'),('10010','颜回','10');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
