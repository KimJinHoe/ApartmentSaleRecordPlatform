drop table if exists notice;

create table `notice` (
  `no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `author` varchar(10) DEFAULT NULL,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `view` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB;
