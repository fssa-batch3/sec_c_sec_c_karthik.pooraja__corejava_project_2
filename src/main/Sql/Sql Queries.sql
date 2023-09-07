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