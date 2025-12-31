package com.klu;

import java.sql.*;
import java.util.Scanner;

public class JDBCCrud {

    static String url = "jdbc:mysql://localhost:3306/testdb";
    static String user = "root";
    static String password = "12345";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        createTables();

        int choice;

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Insert Department");
            System.out.println("2. Insert Employee");
            System.out.println("3. View Employees");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    insertDept();
                    break;
                case 2:
                    insertEmployee();
                    break;
                case 3:
                    viewEmployees();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }

    static void createTables() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            String deptTable = "CREATE TABLE IF NOT EXISTS dept (" +
                    "dept_id INT PRIMARY KEY, " +
                    "dept_name VARCHAR(50)" +
                    ")";

            String empTable = "CREATE TABLE IF NOT EXISTS employee (" +
                    "emp_id INT PRIMARY KEY, " +
                    "emp_name VARCHAR(50), " +
                    "salary DOUBLE, " +
                    "dept_id INT, " +
                    "FOREIGN KEY (dept_id) REFERENCES dept(dept_id)" +
                    ")";

            st.execute(deptTable);
            st.execute(empTable);

            System.out.println("Tables created or already exist.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertDept() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "INSERT INTO dept VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Dept ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            ps.setInt(1, id);

            System.out.print("Enter Dept Name: ");
            String name = sc.nextLine();
            ps.setString(2, name);

            ps.executeUpdate();
            System.out.println("Department inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "INSERT INTO employee VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Emp ID: ");
            int empId = sc.nextInt();
            sc.nextLine(); // consume newline
            ps.setInt(1, empId);

            System.out.print("Enter Emp Name: ");
            String empName = sc.nextLine();
            ps.setString(2, empName);

            System.out.print("Enter Salary: ");
            double sal = sc.nextDouble();
            sc.nextLine(); // consume newline
            ps.setDouble(3, sal);

            System.out.print("Enter Dept ID: ");
            int deptId = sc.nextInt();
            sc.nextLine(); // consume newline
            ps.setInt(4, deptId);

            ps.executeUpdate();
            System.out.println("Employee inserted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void viewEmployees() {
        String sql = "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name " +
                "FROM employee e JOIN dept d ON e.dept_id = d.dept_id";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nID  Name  Salary  Dept");
            while (rs.next()) {
                System.out.printf("%d  %s  %.2f  %s%n",
                        rs.getInt("emp_id"),
                        rs.getString("emp_name"),
                        rs.getDouble("salary"),
                        rs.getString("dept_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "UPDATE employee SET salary=? WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Emp ID: ");
            int empId = sc.nextInt();
            System.out.print("Enter New Salary: ");
            double newSalary = sc.nextDouble();
            sc.nextLine(); // consume newline

            ps.setDouble(1, newSalary);
            ps.setInt(2, empId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee updated!");
            } else {
                System.out.println("Employee ID not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password)) {

            String sql = "DELETE FROM employee WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.print("Enter Emp ID to delete: ");
            int empId = sc.nextInt();
            sc.nextLine(); // consume newline
            ps.setInt(1, empId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted!");
            } else {
                System.out.println("Employee ID not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
