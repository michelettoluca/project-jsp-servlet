package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.Reservation;
import com.projectjspservlet.entity.User;
import com.projectjspservlet.type.UserRoles;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {
    public static User createUser(User user) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User sameUser = getUser(user.getUsername());
            if (sameUser != null) return null;

            session.save(user);
            transaction.commit();

            return user;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();

            return null;
        }
    }

    public static User updateUser(User user) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            System.out.println(user);

            User currentUser = getUser(user.getId());

            if (user.getFirstName() != null) currentUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null) currentUser.setLastName(user.getLastName());
            if (user.getRole() != null) currentUser.setRole(user.getRole());
            if (user.getUsername() != null) currentUser.setUsername(user.getUsername());
            if (user.getPassword() != null) currentUser.setPassword(user.getPassword());

            session.update(currentUser);
            transaction.commit();

            return user;

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();

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
            User user = getUser(id);
            if (user == null) return;

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
