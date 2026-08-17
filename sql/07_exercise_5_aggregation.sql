USE school_management;

-- Exercise 5.1: Count all students.
SELECT COUNT(*) AS total_students
FROM student;

-- Exercise 5.2: Count attendance records by status.
SELECT status,
       COUNT(*) AS total_records
FROM attendance
GROUP BY status
ORDER BY status;

-- Exercise 5.3: Count each student's distinct days marked Present.
-- The LEFT JOIN keeps students who have no attendance records.
SELECT s.name AS student_name,
       COUNT(DISTINCT CASE WHEN a.status = 'Present' THEN a.attendance_date END) AS days_present
FROM student AS s
         LEFT JOIN attendance AS a ON s.id = a.student_id
GROUP BY s.id, s.name
ORDER BY s.id;
