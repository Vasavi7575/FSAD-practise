package com.klu.app;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.klu.model.Department;
import com.klu.model.Employee;
import com.klu.util.HibernateUtil;

public class MainApp {

    static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== EMPLOYEE MENU =====");
            System.out.println("1. Insert Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(sc);
                    break;
                case 2:
                    viewEmployee(sc);
                    break;
                case 3:
                    updateEmployee(sc);
                    break;
                case 4:
                    deleteEmployee(sc);
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        factory.close();
        sc.close();
    }

   
    private static void insertEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Dept Name: ");
        String deptName = sc.next();

        Department dept = new Department();
        dept.setDeptName(deptName);

        System.out.print("Enter Emp Name: ");
        String empName = sc.next();

        System.out.print("Enter Salary: ");
        double empSalary = sc.nextDouble();

        Employee emp = new Employee();
        emp.setEmpName(empName);
        emp.setSalary(empSalary);
        emp.setDepartment(dept);

        session.persist(dept);
        session.persist(emp);

        tx.commit();
        session.close();

        System.out.println("Employee inserted successfully!");
    }

   
    private static void viewEmployee(Scanner sc) {

        Session session = factory.openSession();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.println("Name   : " + emp.getEmpName());
            System.out.println("Salary : " + emp.getSalary());
            System.out.println("Dept   : " + emp.getDepartment().getDeptName());
        } else {
            System.out.println("Employee not found!");
        }

        session.close();
    }

    
    private static void updateEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            System.out.print("Enter New Salary: ");
            emp.setSalary(sc.nextDouble());
            tx.commit();
            System.out.println("Salary updated!");
        } else {
            System.out.println("Employee not found!");
            tx.rollback();
        }

        session.close();
    }

   
    private static void deleteEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.remove(emp);
            tx.commit();
            System.out.println("Employee deleted!");
        } else {
            System.out.println("Employee not found!");
            tx.rollback();
        }

        session.close();
    }
}