USE school_management;

-- Exercise 4.1: Only students with an attendance record.
SELECT s.name AS student_name,
       a.status AS attendance_status
FROM student AS s
         INNER JOIN attendance AS a ON s.id = a.student_id
ORDER BY s.id, a.attendance_date;

-- Exercise 4.2: All students, including those without attendance records.
SELECT s.name AS student_name,
       a.status AS attendance_status
FROM student AS s
         LEFT JOIN attendance AS a ON s.id = a.student_id
ORDER BY s.id, a.attendance_date;
