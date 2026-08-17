# 10-SQL-JDBC

This project contains study material and exercises about relational databases, SQL, and Java Database Connectivity (JDBC). The material mainly uses MySQL for examples, while also covering PostgreSQL setup and connection examples.

## Project contents

### `SQL_Presentation.md`

Introduces databases, DBMSs, relational databases, and SQL. It covers:

- Database types and the purpose of a DBMS.
- Tables, rows, columns, and the relationship between object-oriented programming and relational databases.
- SQL categories: DDL, DML, and DQL.
- MySQL examples for creating databases and tables, inserting, querying, updating, and deleting data.
- Primary keys, MySQL data types, filtering with `AND`, `OR`, `LIKE`, `BETWEEN`, and `IN`.
- `ALTER TABLE` operations for adding, modifying, renaming, and removing columns.
- One-to-one, one-to-many, and many-to-many relationships.
- Constraints such as `NOT NULL`, `UNIQUE`, `PRIMARY KEY`, `FOREIGN KEY`, and `DEFAULT`, including a student-attendance example.

### `JDBC_Presentation.md`

Explains how Java applications communicate with relational databases through JDBC. It covers:

- The JDBC API, JDBC drivers, and the role of a Type 4 (thin) driver.
- The standard JDBC workflow: connect, create a statement, execute SQL, process results, and close resources.
- JDBC URL structure and connection properties for MySQL and PostgreSQL.
- The `Connection`, `Statement`, `PreparedStatement`, and `ResultSet` interfaces.
- Parameterized queries and protection against SQL injection.
- Reading query results and retrieving generated database keys.
- `DataSource`, connection pooling, and the difference between `DataSource` and `DriverManager`.

### `Database_Setup_Guide.md`

Provides setup instructions for MySQL and PostgreSQL. It compares local installation with Docker, gives Docker commands and Docker Compose configurations, lists default connection details and ports, and links to official documentation and management tools such as MySQL Workbench and pgAdmin.

### `SQL_Exercises.md`

Contains practice tasks based on the presentations:

- DDL: create a `school_management` database and a `courses` table.
- DML: insert, update, and delete course records.
- DQL: select, filter, and pattern-match student records.
- Joins: query student attendance with inner and left joins.
- Aggregation: count students and group attendance records by status and student.

## Project status

Exercises 1-3 have been implemented as MySQL scripts in `sql/`. The repository does not yet contain Java source code or a Maven/Gradle build configuration.

The `.idea/` directory contains local IntelliJ IDEA project metadata: the Java module definition, project JDK/output settings, module registration, and Git integration settings. These files configure the editor and are not part of the learning material.

## Preparation for implementation

The repository includes an empty structure for the next phase:

- `src/main/java/`: application and JDBC code.
- `src/main/resources/`: configuration and other runtime resources.
- `src/test/java/`: automated tests.
- `sql/`: database setup, seed, or migration scripts.

Before adding a Java application, choose the Java version, build tool, and JDBC driver. Keep usernames, passwords, and other local connection settings out of Git; use environment variables or another local configuration file instead.

## Running SQL exercises 1-3

The exercise solution uses MySQL 8.4 in Docker, following the recommended Docker approach in `Database_Setup_Guide.md`. The password in `compose-mysql.yml` is the guide's local development password and must not be used in production.

Start MySQL:

```powershell
docker compose -f compose-mysql.yml up -d --wait
```

Run all solution scripts after the container reports that it is healthy:

```powershell
docker compose -f compose-mysql.yml exec -T -e MYSQL_PWD=root mysql mysql -uroot --table -e "source /workspace/sql/run_exercises_1_3.sql"
```

The command recreates the exercise tables, inserts the sample data, applies the requested update and deletion, and prints the Exercise 3 query results. It also prints the final `courses` table so the DML changes can be checked.

The SQL files map to the assignment as follows:

- `sql/01_exercise_1_ddl.sql`: creates `school_management` and `courses`.
- `sql/02_exercise_2_dml.sql`: inserts, updates, and deletes course data.
- `sql/03_exercise_3_student_setup.sql`: creates and seeds the lecture-compatible `student` table required by Exercise 3.
- `sql/04_exercise_3_dql.sql`: contains the three requested `SELECT` queries.
- `sql/run_exercises_1_3.sql`: runs everything in order and displays the final course data.

Stop the database when finished:

```powershell
docker compose -f compose-mysql.yml down
```

The named Docker volume keeps the database data between starts. Add `-v` to the `down` command only if you intentionally want to remove that exercise data.
