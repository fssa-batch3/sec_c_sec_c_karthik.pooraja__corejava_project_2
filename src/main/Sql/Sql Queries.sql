<<<<<<< HEAD
SELECT * FROM core_java_project.books;

CREATE TABLE `books` (
  `book_id` INT AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(49) NOT NULL,
  `publisheddate` date NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `bookimageurl` blob NOT NULL,
  `edition` int NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`book_id`)
);

select * from books;
=======
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
>>>>>>> 92b7872f20fe8eb92f296630d6ec9bed3b1812a9
