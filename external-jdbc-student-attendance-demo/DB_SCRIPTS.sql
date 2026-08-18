CREATE DATABASE IF NOT EXISTS student_db
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE student_db;

CREATE TABLE IF NOT EXISTS student (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    class_group VARCHAR(50) NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS attendance (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    attendance_date DATE NOT NULL DEFAULT (CURRENT_DATE),
    status ENUM('Present', 'Absent') NOT NULL,

    FOREIGN KEY (student_id) REFERENCES student(id),
    UNIQUE (student_id, attendance_date)
);

INSERT INTO student (name, class_group)
VALUES ('Erik Andersson', 'G1'),
       ('Anna Johansson', 'G1'),
       ('Lars Svensson', 'G2'),
       ('Karin Nilsson', 'G2');


INSERT INTO attendance (student_id, attendance_date, status) VALUES
        (1, '2024-03-12', 'Present'),
        (2, '2024-03-12', 'Absent'),
        (3, '2024-03-12', 'Present'),
        (4, '2024-03-12', 'Present');