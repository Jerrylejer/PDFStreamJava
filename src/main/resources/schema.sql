-- DROP TABLES
DROP TABLE IF EXISTS pdf_category CASCADE;
DROP TABLE IF EXISTS pdf_collection CASCADE;
DROP TABLE IF EXISTS user_role CASCADE;
DROP TABLE IF EXISTS alert CASCADE;
DROP TABLE IF EXISTS article CASCADE;
DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS collection CASCADE;
DROP TABLE IF EXISTS donation CASCADE;
DROP TABLE IF EXISTS evaluation CASCADE;
DROP TABLE IF EXISTS pdf CASCADE;
DROP TABLE IF EXISTS `user` CASCADE;
DROP TABLE IF EXISTS `role` CASCADE;

-- CREATE TABLES
CREATE TABLE `article` (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  description text,
  `ordering` int DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL
);

CREATE TABLE `user` (
  id bigint PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username varchar(50) UNIQUE DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  email varchar(255) UNIQUE DEFAULT NULL,
  avatar mediumblob,
  bio varchar(255) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL
);

CREATE TABLE `role` (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL
);

CREATE TABLE pdf (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title varchar(255) DEFAULT NULL,
  small_description varchar(255) DEFAULT NULL,
  description text,
  image mediumblob,
  pdf_file mediumblob,
  `size` bigint DEFAULT NULL,
  counter int DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE alert (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  description text,
  state varchar(50) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  pdf_id int DEFAULT NULL,
  article_id int DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  CONSTRAINT FK_article FOREIGN KEY (article_id) REFERENCES article (id),
  CONSTRAINT FK_pdf FOREIGN KEY (pdf_id) REFERENCES pdf (id),
  CONSTRAINT FK_alert_user FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE category (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  parent_id int DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  CONSTRAINT FK_parent FOREIGN KEY (parent_id) REFERENCES category (id)
);

CREATE TABLE collection (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  user_id bigint UNIQUE DEFAULT NULL,
  CONSTRAINT FK_collection_user FOREIGN KEY (user_id) REFERENCES `user` (id)
);

CREATE TABLE donation (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  amount int DEFAULT NULL,
  message varchar(255) DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  pdf_id int DEFAULT NULL,
  user_beneficiary_id bigint DEFAULT NULL,
  user_donor_id bigint DEFAULT NULL,
   CONSTRAINT FK_user_beneficiary FOREIGN KEY (user_beneficiary_id) REFERENCES `user` (id),
   CONSTRAINT FK_user_donor FOREIGN KEY (user_donor_id) REFERENCES `user` (id),
   CONSTRAINT FK_donation_pdf FOREIGN KEY (pdf_id) REFERENCES pdf (id)
);

CREATE TABLE evaluation (
  id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  comment varchar(255) DEFAULT NULL,
  star tinyint DEFAULT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  pdf_id int DEFAULT NULL,
  user_id bigint DEFAULT null,
   CONSTRAINT FK_evaluation_user FOREIGN KEY (user_id) REFERENCES `user` (id),
   CONSTRAINT FK_evaluation_pdf FOREIGN KEY (pdf_id) REFERENCES pdf (id)
);

CREATE TABLE pdf_category (
  category_id int NOT NULL,
  pdf_id int NOT NULL,
  PRIMARY KEY (category_id, pdf_id),
  CONSTRAINT FK_pdf_category_pdf FOREIGN KEY (pdf_id) REFERENCES pdf (id),
  CONSTRAINT FK_pdf_category_category FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE pdf_collection (
  collection_id int NOT NULL,
  pdf_id int NOT NULL,
  PRIMARY KEY (collection_id, pdf_id),
  CONSTRAINT FK_pdf_collection_pdf FOREIGN KEY (pdf_id) REFERENCES pdf (id),
  CONSTRAINT FK_pdf_collection_collection FOREIGN KEY (collection_id) REFERENCES collection (id)
);

CREATE TABLE user_role (
  role_id int NOT NULL,
  user_id bigint NOT NULL,
  PRIMARY KEY (role_id, user_id),
  CONSTRAINT FK_user_role_user FOREIGN KEY (user_id) REFERENCES `user` (id),
  CONSTRAINT FK_role FOREIGN KEY (role_id) REFERENCES `role` (id)
);



