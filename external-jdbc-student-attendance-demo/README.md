# Imported JDBC Student Attendance Demo

This directory contains material imported from the separate `G62-SQL` repository. It has been isolated here so it does not mix with the SQL exercise solution in the project root. Nothing in this directory is used by the root `compose-mysql.yml` or `sql/run_exercises_1_5.sql` workflow.

## What it contains

| Path | Purpose |
| --- | --- |
| `DB_SCRIPTS.sql` | Creates `student_db`, the `student` and `attendance` tables, and a small four-student dataset. |
| `Lecture_Scripts.sql` | Heavily commented lecture examples covering schema creation, inserts, selects, filtering, updates, deletes, attendance data, and an inner join. |
| `SQL_Presentation.md` | SQL lecture material; this file is identical to the presentation in the project root. |
| `JDBC_Presentation.md` | JDBC lecture material; it matches the root presentation apart from table-of-contents link formatting. |
| `JDBC/pom.xml` | Maven configuration for Java 25 and MySQL Connector/J 8.0.33. |
| `JDBC/src/main/java/se/lexicon/model/` | `Student`, `Attendance`, and `AttendanceStatus` domain classes. |
| `JDBC/src/main/java/se/lexicon/dao/` | DAO interfaces and JDBC implementations for saving and reading students and attendance. |
| `JDBC/src/main/java/se/lexicon/db/DatabaseConnection.java` | `DriverManager` and `DataSource` connection examples. |
| `JDBC/src/main/java/se/lexicon/JDBC_DEMO.java` | Basic `Statement`, `PreparedStatement`, and `ResultSet` examples. |
| `JDBC/src/main/java/se/lexicon/Main.java` | DAO and transaction demonstrations. |

## Functionality

The example models a one-to-many relationship in which a student can have one attendance record per date. The Java code demonstrates:

- Connecting to MySQL with both `DriverManager` and `MysqlDataSource`.
- Mapping database rows to Java model objects.
- Using prepared statements and generated keys.
- Saving and listing students through a DAO layer.
- Saving and listing attendance together with student data.
- Grouping a student insert and attendance insert in a transaction.

## Runtime assumptions

- Java 25.
- Maven.
- MySQL database `student_db` on `localhost:3307`.
- Username and password `root` / `root`.

These settings are hardcoded in `DatabaseConnection.java` and `JDBC_DEMO.java`. The main project container uses port `3306`, so this imported application will not connect to it without changing either the Java URL or the port mapping.

For a fresh database, `DB_SCRIPTS.sql` is the better starting script. The SQL scripts contain fixed seed data and are not designed as migrations or reliable repeatable test fixtures.

## Important findings before reuse

- `Main.main()` currently calls no demonstrations because both method calls are commented out.
- `AttendanceDaoImpl.findAll()` constructs attendance objects with a `null` status; the source contains a TODO for this mapping bug.
- DAO methods wrap SQL failures in `RuntimeException`, while the transaction example only catches `SQLException`, so not every failure will reach its rollback block.
- `Lecture_Scripts.sql` creates the database twice and deletes student ID 4 before inserting attendance for that ID. It is instructional material, not a safe end-to-end initializer in its current order.
- Re-running either seed script against existing data can create duplicate students or violate the unique attendance constraint.
- Maven reports that `mysql:mysql-connector-java` has moved to the `com.mysql:mysql-connector-j` coordinates.
- Connection credentials are embedded in source code and should be externalized before treating this as an application.
- There are no automated tests.

## Verification

`mvn clean test` successfully compiled all 10 Java source files. Maven found no tests to run. The build used JDK 26 while targeting Java 25 and warned that the POM should use `--release 25` instead of separate source and target settings.

The files have otherwise been kept as imported so this directory remains a faithful reference copy of the other repository.
