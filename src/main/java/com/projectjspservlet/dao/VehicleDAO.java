package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VehicleDAO {
    public static void addVehicle(Vehicle vehicle) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static void removeVehicle(Vehicle vehicle) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(vehicle);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static List<Vehicle> getVehicles() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Vehicle", Vehicle.class).list();
        }
    }
}
