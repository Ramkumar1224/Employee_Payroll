package employeepayroll;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_payroll","root","Tiger");
        } catch (Exception e) {
            System.out.println("Database connection error: " + e);
        }
        return con;
    }
}