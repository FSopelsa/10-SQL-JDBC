# 10-SQL-JDBC

A learning project for relational databases, SQL, and Java Database Connectivity (JDBC). Exercises 1-4 from `SQL_Exercises.md` are implemented as repeatable MySQL scripts. Docker Compose starts the database, mounts the scripts, and provides a consistent environment for running and checking each exercise.

The repository currently focuses on SQL. It does not yet contain a Java/JDBC application or a Maven/Gradle build.

## Project structure

| Path | Purpose |
| --- | --- |
| `SQL_Exercises.md` | Assignment for the SQL exercises. |
| `SQL_Presentation.md` | SQL concepts and the lecture's `student` and `attendance` examples. |
| `JDBC_Presentation.md` | JDBC concepts and Java connection examples. |
| `Database_Setup_Guide.md` | MySQL and PostgreSQL setup alternatives. |
| `compose-mysql.yml` | Local MySQL 8.4 container and persistent data volume. |
| `sql/` | Exercise setup, solution, and runner scripts. |

### Implemented exercises

| Exercise | Scripts | Functionality |
| --- | --- | --- |
| 1: DDL | `01_exercise_1_ddl.sql` | Creates `school_management` and the `courses` table. |
| 2: DML | `02_exercise_2_dml.sql` | Inserts courses, updates Java credits, and deletes a course by ID. |
| 3: DQL | `03_exercise_3_student_setup.sql`, `04_exercise_3_dql.sql` | Creates sample students and runs select, filter, and pattern-matching queries. |
| 4: Joins | `05_exercise_4_attendance_setup.sql`, `06_exercise_4_joins.sql` | Creates attendance data and demonstrates inner and left joins. |

`run_exercises_1_3.sql` runs Exercises 1-3. `run_exercises_1_4.sql` runs the complete implemented solution.

## Requirements

- Docker Desktop with Docker Compose.
- Port `3306` available on the computer.

The container uses the local development login `root` / `root`. These credentials must not be used in production.

## Start the database

From the project directory, run:

```powershell
docker compose -f compose-mysql.yml up -d --wait
```

Docker reports `Healthy` when MySQL is ready.

## Test all implemented exercises

```powershell
docker compose -f compose-mysql.yml exec -T -e MYSQL_PWD=root mysql mysql -uroot --table -e "source /workspace/sql/run_exercises_1_4.sql"
```

Expected final results:

- `courses` contains `Java Programming` with 6 credits and `SQL Basics` with 3 credits.
- The Exercise 3 filters return three `G1` students and two names beginning with `J`.
- The Exercise 4 inner join returns four students with attendance.
- The Exercise 4 left join returns all five students, with `NULL` attendance for Sara.

## Test one exercise stage

Each command includes the prerequisite setup, so it can be run independently. The setup scripts recreate their exercise tables and are safe to rerun inside the `school_management` practice database.

### Exercise 1: database and table

```powershell
docker compose -f compose-mysql.yml exec -T -e MYSQL_PWD=root mysql mysql -uroot --table -e "source /workspace/sql/01_exercise_1_ddl.sql; SHOW CREATE TABLE school_management.courses;"
```

### Exercise 2: insert, update, and delete

```powershell
docker compose -f compose-mysql.yml exec -T -e MYSQL_PWD=root mysql mysql -uroot --table -e "source /workspace/sql/01_exercise_1_ddl.sql; source /workspace/sql/02_exercise_2_dml.sql; SELECT * FROM school_management.courses;"
```

### Exercise 3: student queries

```powershell
docker compose -f compose-mysql.yml exec -T -e MYSQL_PWD=root mysql mysql -uroot --table -e "source /workspace/sql/01_exercise_1_ddl.sql; source /workspace/sql/03_exercise_3_student_setup.sql; source /workspace/sql/04_exercise_3_dql.sql;"
```

### Exercise 4: inner and left joins

```powershell
docker compose -f compose-mysql.yml exec -T -e MYSQL_PWD=root mysql mysql -uroot --table -e "source /workspace/sql/01_exercise_1_ddl.sql; source /workspace/sql/03_exercise_3_student_setup.sql; source /workspace/sql/05_exercise_4_attendance_setup.sql; source /workspace/sql/06_exercise_4_joins.sql;"
```

## Stop or reset MySQL

Stop the container while keeping its data:

```powershell
docker compose -f compose-mysql.yml down
```

To also delete the exercise database volume, intentionally add `-v`:

```powershell
docker compose -f compose-mysql.yml down -v
```
