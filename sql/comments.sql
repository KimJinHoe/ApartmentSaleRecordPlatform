use happyhouse;
DROP TABLE `comments`;
CREATE TABLE `comments` (
  `commentno` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `comment` varchar(500) NOT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `no` int NOT NULL,
  PRIMARY KEY (`commentno`),
  KEY `comment_no_idx` (`no`),
  CONSTRAINT `comment_no` FOREIGN KEY (`no`) REFERENCES `notice` (`no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;