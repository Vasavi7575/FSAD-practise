package com.klu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {

        SessionFactory factory = null;
        Session session = null;
        Transaction tx = null;

        try {
            // Load configuration and build SessionFactory
            factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

            // Open session
            session = factory.openSession();

            // Begin transaction
            tx = session.beginTransaction();

            // Create object
            Student s = new Student();
            s.setName("Deepika");

            // Save object
            session.save(s);

            // Commit transaction
            tx.commit();

            System.out.println("Data has been inserted successfully");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // Close resources
            if (session != null) session.close();
            if (factory != null) factory.close();
        }
    }
}
