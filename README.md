# Library Management System

Database Table

| Column Name   | Data Type       | Constraints                   |
|---------------|-----------------|-------------------------------|
| book_id       | int             | primary key, AUTO_INCREMENT   |
| title         | VARCHAR(100)    | NOT NULL                      |
| author        | VARCHAR(49)     | NOT NULL                      |
| publisheddate | date            | NOT NULL                      |
| publisher     | VARCHAR(100)    | NOT NULL                      |
| bookimageurl  | BLOB            | NOT NULL                      |
| edition       | int             | NOT NULL                      |
| category      | varchar(100)    | NOT NULL                      |

