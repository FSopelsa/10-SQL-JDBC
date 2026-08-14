![Lexicon Logo](https://lexicongruppen.se/media/wi5hphtd/lexicon-logo.svg)

# SQL Exercises

Practice your SQL skills using the concepts learned in the lectures.

---

## 1. DDL: Data Definition Language

**Exercise 1.1: Create a Database**
Create a new database named `school_management`.

**Exercise 1.2: Create Tables**
Within `school_management`, create a table named `courses` with the following columns:
- `id`: Integer, Primary Key, Auto Increment.
- `course_name`: VARCHAR(100), Not Null.
- `credits`: Integer, Not Null.

---

## 2. DML: Data Manipulation Language

**Exercise 2.1: Insert Data**
Insert at least three courses into the `courses` table (e.g., 'Java Programming', 'SQL Basics', 'Web Development').

**Exercise 2.2: Update Data**
Update the credits for 'Java Programming' to a higher value.

**Exercise 2.3: Delete Data**
Delete a course from the table by its ID.

---

## 3. DQL: Data Query Language

**Exercise 3.1: Basic Select**
Select all columns from the `student` table (assuming the table from the lecture exists).

**Exercise 3.2: Filtering**
Select students from the `student` table who belong to a specific `class_group`.

**Exercise 3.3: Pattern Matching**
Find all students whose names start with the letter 'J'.

---

## 4. Joins and Relationships

**Exercise 4.1: Inner Join**
Write a query to display student names and their corresponding attendance status by joining the `student` and `attendance` tables.

**Exercise 4.2: Left Join**
Write a query to display all students and their attendance status, including students who have no attendance records yet.

---

## 5. Aggregation and Grouping

**Exercise 5.1: Count**
Count the total number of students in the `student` table.

**Exercise 5.2: Group By**
Find the total number of 'Present' vs 'Absent' records in the `attendance` table.

**Exercise 5.3: Group By with Join**
Calculate the number of days each student has been 'Present'. Display the student's name and the count.

---
