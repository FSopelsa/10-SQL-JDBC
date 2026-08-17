USE school_management;

-- Exercise 4 uses the attendance table from the lecture.
DROP TABLE IF EXISTS attendance;

CREATE TABLE attendance
(
    id              INT                         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_id      INT                         NOT NULL,
    attendance_date DATE                        NOT NULL DEFAULT (CURRENT_DATE),
    status          ENUM ('Present', 'Absent') NOT NULL,

    FOREIGN KEY (student_id) REFERENCES student (id),
    UNIQUE (student_id, attendance_date)
);

-- Sara intentionally has no attendance row so the LEFT JOIN can include her.
INSERT INTO attendance (student_id, attendance_date, status)
VALUES (1, '2026-08-11', 'Present'),
       (2, '2026-08-11', 'Absent'),
       (3, '2026-08-11', 'Present'),
       (4, '2026-08-11', 'Absent');
