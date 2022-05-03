package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.type.ReservationStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class ReservationDAO {
    public static void addReservation(Reservation reservation) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static void removeReservation(Reservation reservation) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static List<Reservation> getReservations() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Reservation", Reservation.class).list();
        }
    }

    public static List<Reservation> getPendingReservations() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> q = session.createQuery("from Reservation where status = :status", Reservation.class);
            q.setParameter("status", ReservationStatus.PENDING);

            return q.getResultList();
        }
    }

    public static List<Reservation> getApprovedReservations() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> q = session.createQuery("from Reservation where status = :status", Reservation.class);
            q.setParameter("status", ReservationStatus.APPROVED);

            return q.getResultList();
        }
    }

    public static List<Reservation> getDeniedReservations() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> q = session.createQuery("from Reservation where status = :status", Reservation.class);
            q.setParameter("status", ReservationStatus.DENIED);

            return q.getResultList();
        }
    }
}
