package employeepayroll;

import java.sql.*;
import java.util.Scanner;

public class EmployeeService {
    Connection con = DBConnection.getConnection();
    Scanner sc = new Scanner(System.in);

    // Add new employee
    public void addEmployee() {
        try {
            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Department: ");
            String dept = sc.nextLine();
            System.out.print("Enter Basic Salary: ");
            double basic = sc.nextDouble();
            sc.nextLine();

            double hra = basic * 0.20;
            double da = basic * 0.15;
            double deductions = basic * 0.10;
            double net = basic + hra + da - deductions;

            String sql = "INSERT INTO employees (name, department, basic_salary, hra, da, deductions, net_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, basic);
            ps.setDouble(4, hra);
            ps.setDouble(5, da);
            ps.setDouble(6, deductions);
            ps.setDouble(7, net);
            ps.executeUpdate();

            System.out.println("Employee added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // View all employees
    public void viewEmployees() {
        try {
            String sql = "SELECT * FROM employees";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n--- Employee Payroll Details ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("emp_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Basic: " + rs.getDouble("basic_salary"));
                System.out.println("HRA: " + rs.getDouble("hra"));
                System.out.println("DA: " + rs.getDouble("da"));
                System.out.println("Deductions: " + rs.getDouble("deductions"));
                System.out.println("Net Salary: " + rs.getDouble("net_salary"));
                System.out.println("-----------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Update salary of an employee
    public void updateSalary() {
        try {
            System.out.print("Enter Employee ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Basic Salary: ");
            double basic = sc.nextDouble();
            sc.nextLine();

            double hra = basic * 0.20;
            double da = basic * 0.15;
            double deductions = basic * 0.10;
            double net = basic + hra + da - deductions;

            String sql = "UPDATE employees SET basic_salary=?, hra=?, da=?, deductions=?, net_salary=? WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, basic);
            ps.setDouble(2, hra);
            ps.setDouble(3, da);
            ps.setDouble(4, deductions);
            ps.setDouble(5, net);
            ps.setInt(6, id);
            int updated = ps.executeUpdate();

            if (updated > 0) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("Employee ID not found!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Delete employee
    public void deleteEmployee() {
        try {
            System.out.println("Enter Employee ID to delete: ");
            int id = sc.nextInt();

            String sql = "DELETE FROM employees WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int deleted = ps.executeUpdate();

            if (deleted > 0)
                System.out.println("Employee deleted successfully!");
            else
                System.out.println("Employee not found!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Salary Report (using SQL functions)
    public void salaryReport() {
        try {
            String sql = "SELECT COUNT(*) AS total_employees, ROUND(SUM(net_salary),2) AS total_salary, ROUND(AVG(net_salary),2) AS avg_salary FROM employees";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                System.out.println("\n--- Salary Report ---");
                System.out.println("Total Employees: " + rs.getInt("total_employees"));
                System.out.println("Total Salary Paid: " + rs.getDouble("total_salary"));
                System.out.println("Average Salary: " + rs.getDouble("avg_salary"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}