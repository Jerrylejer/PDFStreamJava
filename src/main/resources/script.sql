-- DROP TABLES
DROP TABLE IF EXISTS `alert` CASCADE;
DROP TABLE IF EXISTS `article` CASCADE;
DROP TABLE IF EXISTS `category` CASCADE;
DROP TABLE IF EXISTS `collection` CASCADE;
DROP TABLE IF EXISTS `donation` CASCADE;
DROP TABLE IF EXISTS `evaluation` CASCADE;
DROP TABLE IF EXISTS `pdf` CASCADE;
DROP TABLE IF EXISTS `pdf_category` CASCADE;
DROP TABLE IF EXISTS `pdf_collection` CASCADE;
DROP TABLE IF EXISTS `search` CASCADE;
DROP TABLE IF EXISTS `user` CASCADE;
DROP TABLE IF EXISTS `role` CASCADE;
DROP TABLE IF EXISTS `user_role` CASCADE;

-- CREATE TABLES
CREATE TABLE IF NOT EXISTS `alert` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `state` varchar(50) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `pdf_id` int DEFAULT NULL,
  `article_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
   CONSTRAINT `FK_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
   CONSTRAINT `FK_pdf` FOREIGN KEY (`pdf_id`) REFERENCES `pdf` (`id`),
   CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
);

CREATE TABLE IF NOT EXISTS `article` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `ordering` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
);

CREATE TABLE IF NOT EXISTS `category` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `parent_id` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  CONSTRAINT `FK_parent` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`),
);

CREATE TABLE IF NOT EXISTS `collection` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint UNIQUE DEFAULT NULL,
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
);

CREATE TABLE IF NOT EXISTS `donation` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `amount` int DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `pdf_id` int DEFAULT NULL,
  `user_beneficiary_id` bigint DEFAULT NULL,
  `user_donor_id` bigint DEFAULT NULL,
   CONSTRAINT `FK_user_beneficiary` FOREIGN KEY (`user_beneficiary_id`) REFERENCES `user` (`id`),
   CONSTRAINT `FK_user_donor` FOREIGN KEY (`user_donor_id`) REFERENCES `user` (`id`),
   CONSTRAINT `FK_pdf` FOREIGN KEY (`pdf_id`) REFERENCES `pdf` (`id`),
);

CREATE TABLE IF NOT EXISTS `evaluation` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `star` tinyint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `pdf_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
   CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
   CONSTRAINT `FK_pdf` FOREIGN KEY (`pdf_id`) REFERENCES `pdf` (`id`),
);

CREATE TABLE IF NOT EXISTS `pdf` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `small_description` varchar(255) DEFAULT NULL,
  `description` text,
  `image` mediumblob,
  `pdf_file` mediumblob,
  `size` bigint DEFAULT NULL,
  `counter` int DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
   CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
);

CREATE TABLE IF NOT EXISTS `pdf_category` (
  `category_id` int NOT NULL,
  `pdf_id` int NOT NULL,
  PRIMARY KEY (`category_id`,`pdf_id`),
  CONSTRAINT `FK_pdf` FOREIGN KEY (`pdf_id`) REFERENCES `pdf` (`id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
);

CREATE TABLE IF NOT EXISTS `pdf_collection` (
  `collection_id` int NOT NULL,
  `pdf_id` int NOT NULL,
  PRIMARY KEY (`collection_id`,`pdf_id`),
  CONSTRAINT `FK_pdf` FOREIGN KEY (`pdf_id`) REFERENCES `pdf` (`id`),
  CONSTRAINT `FK_collection` FOREIGN KEY (`collection_id`) REFERENCES `collection` (`id`),
);

CREATE TABLE IF NOT EXISTS `role` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` enum('ROLE_ADMIN','ROLE_USER') DEFAULT NULL,
);

CREATE TABLE IF NOT EXISTS `user` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `username` varchar(50) UNIQUE DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) UNIQUE DEFAULT NULL,
  `avatar` mediumblob,
  `bio` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
);

CREATE TABLE IF NOT EXISTS `user_role` (
  `role_id` int NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
);

 -- INSERT DES DATAS -> ARTICLE
 INSERT INTO `article` (`title`, `description`, `ordering`, `created_at`, `updated_at`) VALUES
 ('article 1', 'a. Nous souhaitons garantir que le contenu partagé ici soit sécurisé, informatif et respectueux des droits d\'auteur. À cet effet, voici les types de fichiers PDF autorisés sur notre plateforme : .pdf, .pdfa, .pdfx, .pdx, .acrobatsecuritysettings', 1, NOW(), NULL),
 ('article 2', 'b. Contenu Original : Vous êtes encouragé à partager des fichiers PDF contenant votre propre contenu original, que vous détenez les droits d\'auteur ou que vous avez la permission de partager.\r\n', 2, NOW(), NULL),
 ('article 3', 'c. Contenu Libre de Droits : Les fichiers PDF qui sont libres de droits ou qui sont distribués sous des licences telles que Creative Commons peuvent être mis à disposition, à condition de respecter les termes de ces licences.\r\n', 3, NOW(), NULL),
 ('article 4', 'd. Contenu du Domaine Public : Les fichiers PDF relevant du domaine public sont les bienvenus. Assurez-vous que le contenu que vous partagez est réellement dans le domaine public.\r\n', 4, NOW(), NULL),
 ('article 5', 'e. Documents Éducatifs : Les documents éducatifs, tels que des manuels, des articles de recherche et des présentations pédagogiques, sont encouragés à des fins d\'apprentissage et d\'éducation.\r\n', 5, NOW(), NULL),
 ('article 6', 'f. Contenu Respectant la Loi : Tous les fichiers PDF doivent être conformes aux lois locales, nationales et internationales. La diffusion de contenu illégal, diffamatoire, offensant ou préjudiciable est strictement interdite.\r\n', 6, NOW(), NULL),
 ('article 7', 'a. Respectez les droits d\'auteur : Avant de partager un fichier PDF, assurez-vous d\'avoir les droits nécessaires pour le faire. Ne téléchargez que des fichiers PDF pour lesquels vous détenez les droits d\'auteur ou pour lesquels vous avez obtenu une autorisation appropriée.\r\n', 7, NOW(), NULL),
 ('article 8', 'b. Licences Creative Commons : Si vous partagez un fichier PDF sous une licence Creative Commons, assurez-vous de respecter les termes de cette licence et d\'attribuer correctement l\'auteur, le cas échéant.\r\n', 8, NOW(), NULL),
 ('article 9', 'c. Signalement des violations : Si vous êtes propriétaire des droits d\'auteur et que vous estimez qu\'un fichier PDF enfreint vos droits, veuillez nous contacter immédiatement pour signaler l\'infraction.\r\n', 9, NOW(), NULL),
 ('article 10', 'd. Utilisation équitable : Si vous utilisez un fichier PDF à des fins d\'utilisation équitable, veillez à respecter les critères de cette doctrine, tels que la critique, les commentaires, les rapports d\'actualités, l\'éducation et la recherche.\r\n', 10, NOW(), NULL),
 ('article 11', 'a. Exactitude du contenu : Vous êtes responsable de l\'exactitude et de la véracité du contenu que vous partagez. Ne partagez pas d\'informations fausses ou trompeuses.\r\n', 11, NOW(), NULL),
 ('article 12', 'b. Respect des droits d\'auteur : Vous devez respecter les droits d\'auteur et les licences associées au contenu que vous partagez. Ne téléchargez que des fichiers PDF pour lesquels vous avez les droits appropriés.\r\n', 12, NOW(), NULL),
 ('article 13', 'c. Contenu approprié : N\'uploadez, ne partagez ou ne mettez pas à disposition de contenu illégal, diffamatoire, offensant, vulgaire, violent ou préjudiciable.\r\n', 13, NOW(), NULL),
 ('article 14', 'd. Utilisation légale : Utilisez notre site conformément à toutes les lois locales, nationales et internationales. Ne participez pas à des activités illégales.\r\n', 14, NOW(), NULL),
 ('article 15', '\r\ne. Signalement des problèmes : Si vous constatez une violation des règles ou des droits d\'auteur sur notre site, veuillez nous le signaler immédiatement.\r\n', 15, NOW(), NULL),
 ('article 16', 'f. Conformité aux politiques : Vous devez vous conformer à toutes les politiques et les règlements de notre site. La violation répétée de ces règles peut entraîner la suppression de votre compte.\r\n', 16, NOW(), NULL);

 -- INSERT DES DATAS -> CATEGORY
INSERT INTO `category` (`title`, `parent_id`, `created_at`, `updated_at`) VALUES
('Education', NULL, NOW(), NULL),
('Métiers', NULL, NOW(), NULL),
('Arts', NULL, NOW(), NULL),
('Sciences', NULL, NOW(), NULL),
('Passions', NULL, NOW(), NULL),
('Technologies', NULL, NOW(), NULL),
('Développement web', 6, NOW(), NULL),
('IDE', 7, NOW(), NULL),
('Langages', 7, NOW(), NULL),
('Frameworks', 7, NOW(), NULL),
('Veille technologique', 7, NOW(), NULL),
('Javascript', 9, NOW(), NULL),
('Java', 9, NOW(), NULL),
('PHP', 9, NOW(), NULL),
('Web security', 6, NOW(), NULL),
('Data-scientist', 6, NOW(), NULL),
('Elémentaire', 1, NOW(), NULL),
('Collège', 1, NOW(), NULL),
('Lycée', 1, NOW(), NULL),
('Université', 1, NOW(), NULL),
('Biologie', 4, NOW(), NULL),
('Chimie', 4, NOW(), NULL),
('Physique', 4, NOW(), NULL),
('Astronomie', 4, NOW(), NULL),
('Artisanats', 2, NOW(), NULL),
('Finances', 2, NOW(), NULL),
('Métiers de bouche', 2, NOW(), NULL),
('Pratique musicale', 5, NOW(), NULL),
('Collections', 5, NOW(), NULL),
('Jeux', 5, NOW(), NULL),
('Peinture', 3, NOW(), NULL),
('Musique', 3, NOW(), NULL),
('Cinéma', 3, NOW(), NULL),
('Théatre', 3, NOW(), NULL),
('Danse', 3, NOW(), NULL),
('Versioning', 7, NOW(), NULL),
('Mathématiques', 17, NOW(), NULL),
('Mathématiques', 18, NOW(), NULL),
('Mathématiques', 19, NOW(), NULL),
('Mathématiques', 20, NOW(), NULL),
('Français', 17, NOW(), NULL),
('Français', 18, NOW(), NULL),
('Français', 19, NOW(), NULL),
('Histoire Géo', 17, NOW(), NULL),
('Histoire Géo', 18, NOW(), NULL),
('Histoire Géo', 19, NOW(), NULL),
('Arts plastiques', 17, NOW(), NULL),
('Arts plastiques', 18, NOW(), NULL),
('Arts plastiques', 19, NOW(), NULL);

 -- INSERT DES DATAS -> ROLE
INSERT INTO `role` (`name`) VALUES
('ROLE_USER'),
('ROLE_ADMIN');

COMMIT;


