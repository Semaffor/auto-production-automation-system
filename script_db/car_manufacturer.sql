CREATE DATABASE IF NOT EXISTS `car-manufacturer`;
USE `car-manufacturer`;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `personal_data_id` bigint DEFAULT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'USER',
  `is_blocked` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `personal_data_id_UNIQUE` (`personal_data_id`),
  CONSTRAINT `account_personal_date_idx` FOREIGN KEY (`personal_data_id`) REFERENCES `personal_data` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `account` (login, password, role) VALUES
("1","1","ADMIN"),
("2","2","USER"),
("3","3","GUEST");

CREATE TABLE IF NOT EXISTS `car` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `VIN` varchar(20) NOT NULL,
  `model_id` bigint DEFAULT NULL,
  `body_type` varchar(20) DEFAULT 'Хэтчбек',
  `gearbox` varchar(20) DEFAULT 'МКПП',
  `price` decimal(10,2) DEFAULT NULL,
  `issue_date` date DEFAULT NULL,
  `rate` decimal(3,2) DEFAULT '0.00',
  `fuel_type` varchar(20) DEFAULT 'Бензин',
  `photo_url` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`id`,`VIN`),
  UNIQUE KEY `VIN_UNIQUE` (`VIN`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `FK_model_id_model_idx` (`model_id`),
  CONSTRAINT `FK_car_model` FOREIGN KEY (`model_id`) REFERENCES `model` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_id` bigint NOT NULL,
  `question` varchar(180) NOT NULL,
  `answer` varchar(180) DEFAULT NULL,
  `question_date` date NOT NULL,
  `answer_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `feedback_account_id_idx` (`sender_id`),
  CONSTRAINT `feedback_account_id` FOREIGN KEY (`sender_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `history_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL,
  `entrance` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `log_account_id_idx` (`account_id`),
  CONSTRAINT `FK_history_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `model` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `model_name` varchar(45) NOT NULL,
  `description` varchar(180) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `model_model_name_uindex` (`model_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `personal_data` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `thirdname` varchar(45) DEFAULT '----------',
  `age` int DEFAULT '18',
  `position_id` bigint DEFAULT NULL,
  `phone` varchar(45) DEFAULT '----------',
  `social` varchar(45) DEFAULT '----------',
  `empl_start_date` date DEFAULT NULL,
  `empl_end_date` date DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `date_position_id_idx` (`position_id`),
  CONSTRAINT `date_position_id` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `position` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT '1000.00',
  `description` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
