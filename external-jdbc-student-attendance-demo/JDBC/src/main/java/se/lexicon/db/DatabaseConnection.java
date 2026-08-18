package se.lexicon.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3307/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    // Basic and easy when learning
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    private static DataSource mysqlDataSource;


    // Good in production
    public static DataSource getMySQLDataSource(){
        if (mysqlDataSource == null){
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL(URL);
            ds.setUser(USER);
            ds.setPassword(PASSWORD);
            mysqlDataSource = ds;
        }
        return mysqlDataSource;
    }
}
