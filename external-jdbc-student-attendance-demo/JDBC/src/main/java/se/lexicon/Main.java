package se.lexicon;

import se.lexicon.dao.AttendanceDao;
import se.lexicon.dao.AttendanceDaoImpl;
import se.lexicon.dao.StudentDao;
import se.lexicon.dao.StudentDaoImpl;
import se.lexicon.db.DatabaseConnection;
import se.lexicon.model.Attendance;
import se.lexicon.model.AttendanceStatus;
import se.lexicon.model.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {


    static void main() {
//        ex1();
//        ex2(); // Demo Transaction Student + Attendance
    }

    private static void ex1() {
        DataSource dataSource = DatabaseConnection.getMySQLDataSource();

        try (Connection connection = dataSource.getConnection()) {


            StudentDao studentDao = new StudentDaoImpl(connection);
            AttendanceDao attendanceDao = new AttendanceDaoImpl(connection);

            studentDao.findAll().forEach(IO::println);


            Student student = new Student("Simon Elbrink", "G62");

//            Student saved = studentDao.save(student);
//            IO.println("Saved Student" + saved);

            attendanceDao.findAll().forEach(IO::println);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void ex2() {
        DataSource dataSource = DatabaseConnection.getMySQLDataSource();

        try (Connection connection = dataSource.getConnection()) {

            try {
                connection.setAutoCommit(false);

                StudentDao studentDao = new StudentDaoImpl(connection);
                AttendanceDao attendanceDao = new AttendanceDaoImpl(connection);


                Student student = new Student("Peter Petterson", "G65");
                student = studentDao.save(student);
                System.out.println("✅ Student Saved");

//                 student.setId(1000); // Triggering the Transaction to rollback

                Attendance attendance = new Attendance(student, LocalDate.now(), AttendanceStatus.PRESENT);
                attendanceDao.save(attendance);
                System.out.println("✅ Attendance Saved");

                connection.commit();

                System.out.println("✅ Transaction committed");
            } catch (SQLException exception) {
                System.err.println("❌ Transaction failed → rollback");

                connection.rollback();
            }


        } catch (SQLException e) {

            IO.println("Error: Database connection failed!");
            e.printStackTrace();
        }
    }
}
