USE school_management;

-- Exercise 3.1: Select all columns from student.
SELECT *
FROM student;

-- Exercise 3.2: Select students who belong to class group G1.
SELECT *
FROM student
WHERE class_group = 'G1';

-- Exercise 3.3: Select students whose names start with J.
SELECT *
FROM student
WHERE name LIKE 'J%';
