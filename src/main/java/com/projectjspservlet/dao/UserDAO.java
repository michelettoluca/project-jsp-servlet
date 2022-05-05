package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {
    public static User saveUser(User user) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.saveOrUpdate(user);
            transaction.commit();

            return user;

        } catch (Exception e) {
            try {
                if (transaction != null) transaction.rollback();
                
            } catch (Exception e2) {
                System.out.println("ERRORE");

                e.printStackTrace();
            }

            return null;
        }
    }

    public static void deleteUser(User user) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = getUser(id);

            if (user != null) session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public static List<User> getUsers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    public static User getUser(int id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> q = session.createQuery("from User where id = :id", User.class);
            q.setParameter("id", id);

            return q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static User getUser(String username) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> q = session.createQuery("from User where username = :username", User.class);
            q.setParameter("username", username);

            return q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static List<User> getCustomers() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Query<User> q = session.createQuery("from User where role = :role", User.class);
            q.setParameter("role", UserRoles.CUSTOMER);

            return q.getResultList();
        }
    }

}
