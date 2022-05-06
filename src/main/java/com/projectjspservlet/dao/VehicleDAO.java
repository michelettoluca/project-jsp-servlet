package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class VehicleDAO {
    public static void saveVehicle(Vehicle vehicle) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(vehicle);
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

    public static Vehicle getVehicle(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Vehicle> q = session.createQuery("from Vehicle where id = :id", Vehicle.class);
            q.setParameter("id", id);

            return q.getSingleResult();
        }
    }

    public static void deleteVehicle(int id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Vehicle vehicle = getVehicle(id);
            session.delete(vehicle);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
