USE school_management;

-- Exercise 2.1: Insert at least three courses.
INSERT INTO courses (course_name, credits)
VALUES ('Java Programming', 5),
       ('SQL Basics', 3),
       ('Web Development', 4);

-- Exercise 2.2: Increase the credits for Java Programming.
UPDATE courses
SET credits = 6
WHERE course_name = 'Java Programming';

-- Exercise 2.3: Delete Web Development by its generated ID (3).
DELETE FROM courses
WHERE id = 3;
