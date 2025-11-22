package timemanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil - simple JDBC connection helper.
 * Update DB_URL, DB_USER, DB_PASS as per your MySQL setup.
 */
public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/timemanager?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "Ritesh@1904"; // <-- change this

    static {
        try {
            // load driver (optional for modern JDBC but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Add connector jar to classpath.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
}
