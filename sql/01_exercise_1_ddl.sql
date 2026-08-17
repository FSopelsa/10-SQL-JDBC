-- Exercise 1.1: Create the database.
CREATE DATABASE IF NOT EXISTS school_management;
USE school_management;

-- Recreate the exercise table so this script can be run more than once.
DROP TABLE IF EXISTS courses;

-- Exercise 1.2: Create the courses table.
CREATE TABLE courses
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits     INT          NOT NULL
);
