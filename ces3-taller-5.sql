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

CREATE TABLE `enrollments` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nameCourse` varchar(50) NOT NULL,
    `semester` varchar(50) NOT NULL,
    `status` varchar(20) NOT null,
    `id_student` int NOT NULL,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deletedAt` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`id_student`) REFERENCES `students`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

INSERT INTO `enrollments`(`nameCourse`,`semester`,`status`,`id_student`) VALUES ('Mathematics','Fall 2023','enrolled',1);
INSERT INTO `enrollments`(`nameCourse`,`semester`,`status`,`id_student`) VALUES ('Physics','Fall 2023','enrolled',1);
INSERT INTO `enrollments`(`nameCourse`,`semester`,`status`,`id_student`) VALUES ('Chemistry','Spring 2024','pending',1);
INSERT INTO `enrollments`(`nameCourse`,`semester`,`status`,`id_student`) VALUES ('Biology','Fall 2023','completed',1);
INSERT INTO `enrollments`(`nameCourse`,`semester`,`status`,`id_student`) VALUES ('Compute Science','Spring 2024','enrolled',1);