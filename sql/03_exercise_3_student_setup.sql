USE school_management;

-- Exercise 3 assumes that the student table from the lecture exists.
-- This setup recreates that table and adds data for testing the queries.
-- Remove the dependent Exercise 4 table first when rerunning all exercises.
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS student;

CREATE TABLE student
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    class_group VARCHAR(50)  NOT NULL,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO student (name, class_group)
VALUES ('Alice Johnson', 'G1'),
       ('Bob Smith', 'G1'),
       ('Julia Andersson', 'G1'),
       ('Jonas Svensson', 'G2'),
       ('Sara Nilsson', 'G2');
