package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.type.ReservationStatus;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAO {
    public static void saveReservation(Reservation reservation) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(reservation);
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

    public static Reservation getReservation(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> q = session.createQuery("from Reservation where id = :id", Reservation.class);
            q.setParameter("id", id);

            return q.getSingleResult();
        }
    }

    public static List<Reservation> getReservationsWithStatus(ReservationStatus status) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<Reservation> q = session.createQuery("from Reservation where status = :status", Reservation.class);
            q.setParameter("status", status);

            return q.getResultList();
        }
    }


    public static void deleteReservation(int id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Reservation reservation = getReservation(id);
            if (reservation == null) return;

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

    public static void updateReservationStatus(int id, ReservationStatus status) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Reservation reservation = getReservation(id);
            if (reservation == null) return;

            transaction = session.beginTransaction();

            reservation.setStatus(status);

            session.update(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
