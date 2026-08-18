package se.lexicon;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JDBC_DEMO {


    private static final String URL = "jdbc:mysql://localhost:3307/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";


    static void main() {


        //ex1();

        ex2();


    }

    private static void ex2() {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, class_group, create_date FROM student WHERE class_group LIKE ?");

        ){
            System.out.println("✅ Database connection established successfully!");

            String classGroupParam = "G1";
            preparedStatement.setString(1, classGroupParam);


            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                System.out.println("📌 Student Records in Class Group: " + classGroupParam);
                while(resultSet.next()){

                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String classGroup = resultSet.getString("class_group");
                    LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();

                    System.out.println("ID: " + id + " | Name: " + name + " | Class: " + classGroup + " | Created At: " + createDate);

                }
            }



        } catch (SQLException e) {
            System.err.println("❌ Error connecting to the database: " + e.getMessage());
        }
    }

    private static void ex1() {
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement()
        ){
            System.out.println("✅ Database connection established successfully!");


            String query = "SELECT id, name, class_group, create_date FROM student";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String classGroup = resultSet.getString("class_group");

                LocalDateTime createDate = resultSet.getTimestamp("create_date").toLocalDateTime();

                String FormatedDateTime = createDate.format(DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy"));


                System.out.println("ID: " + id + " | Name: " + name + " | Class: " + classGroup + " | Created At: " + FormatedDateTime);


            }

        } catch (SQLException e) {
            System.err.println("❌ Error connecting to the database: " + e.getMessage());
        }
    }
}
