package employeepayroll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n==== EMPLOYEE PAYROLL SYSTEM ====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Salary Report");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 : service.addEmployee();
                		 break;
                case 2 : service.viewEmployees();
                		 break;
                case 3 : service.updateSalary();
                		 break;
                case 4 : service.deleteEmployee();
                		 break;
                case 5 : service.salaryReport();
                		 break;
                case 6 : System.out.println("Goodbye!!");
                     	 System.exit(0);
                     	 break;
                default: System.out.println("Invalid option!");
            }
        }
    }
}