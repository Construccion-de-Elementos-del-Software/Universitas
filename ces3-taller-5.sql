CREATE DATABASE `universitas` 

CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `mail` varchar(80) NOT null UNIQUE,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;


INSERT INTO `students`(`name`,`lastName`,`mail`) values ('Santiago','Torres Aguirre','santi@elpoli.edu.co');
INSERT INTO `students`(`name`,`lastName`,`mail`) values ('Jhoana','Torres Aguirre','jhoa@elpoli.edu.co');
INSERT INTO `students`(`name`,`lastName`,`mail`) values ('Oscar','Mesa','oscar@elpoli.edu.co');
INSERT INTO `students`(`name`,`lastName`,`mail`) values ('Laura','Alzate Aguirre','lau@elpoli.edu.co');
INSERT INTO `students`(`name`,`lastName`,`mail`) values ('Daniel','Saldarriaga Lopez','daniel@elpoli.edu.co');

