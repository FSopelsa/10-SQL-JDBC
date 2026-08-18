package se.lexicon.dao;

import se.lexicon.model.Attendance;
import se.lexicon.model.AttendanceStatus;
import se.lexicon.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDaoImpl implements AttendanceDao {

    private final Connection connection;

    public AttendanceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Attendance save(Attendance attendance) {

        String sql = """
                INSERT INTO attendance (student_id, attendance_date, status)
                VALUES (?, ?, ?)
                """;

        try (
                PreparedStatement ps =
                        connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, attendance.getStudent().getId());
            ps.setDate(2, Date.valueOf(attendance.getAttendanceDate()));
            ps.setString(3, attendance.getStatus().name());

            ps.executeUpdate();


            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    attendance.setId(keys.getInt(1));
                }
            }


        } catch (SQLException e) {
            System.err.println("❌ Error saving attendance: " + e.getMessage());
            throw new RuntimeException("Error saving attendance", e);
        }

        return attendance;
    }

    @Override
    public List<Attendance> findAll() {

        List<Attendance> attendances = new ArrayList<>();

        //Get Attendance and Student Data
        String sql = """
                SELECT a.id, a.attendance_date, a.status,
                       s.id AS student_id, s.name, s.class_group, s.create_date
                FROM attendance a
                JOIN student s ON a.student_id = s.id
                """;


        try (
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {

                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("class_group"),
                        rs.getTimestamp("create_date").toLocalDateTime()
                );

                attendances.add(new Attendance(
                        rs.getInt("id"),
                        student,
                        rs.getDate("attendance_date").toLocalDate(),null // TODO Solve Bug not getting the correct Enum value?
                ));

            }


        } catch (SQLException e) {
            System.err.println("❌ Error retrieving attendance records: " + e.getMessage());
            throw new RuntimeException("Error retrieving attendance records", e);
        }


        return attendances;
    }
}
