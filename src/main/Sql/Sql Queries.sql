CREATE TABLE `books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(49) DEFAULT NULL,
  `publisheddate` date DEFAULT NULL,
  `publisher` varchar(100) DEFAULT NULL,
  `bookimageurl` blob,
  `edition` int DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
)