SELECT * FROM core_java_project;

CREATE TABLE `books` (
  `book_id` INT AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(49) NOT NULL,
  `publisheddate` date NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `bookimageurl` blob NOT NULL,
  `edition` int NOT NULL,
  `category` varchar(45) NOT NULL,
  `stock` int NOT NULL,
  PRIMARY KEY (`book_id`)
);

select * from books;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(46) NOT NULL,
  `phoneNumber` varchar(10) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(100) NOT NULL,
  `Role` varchar(13) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `transfer_status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
);
select * from users;
CREATE TABLE if not exists `transfer` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `request_date` date NOT NULL,
  `status` varchar(17) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `extended_days` int DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`)
);
select * from transfer;

Insert into users (name,phoneNumber,email,password,Role,status,transfer_status) values('Karthik',9876543210,'karthik@gmail.com','Josh@1234','LIBRARIAN',1,'INLIMIT');
