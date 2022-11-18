drop table if exists hospital;

create table `hospital` (
  `sido` varchar(10) DEFAULT NULL,
  `gugun` varchar(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `no` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB;
