package com.projectjspservlet.dao;

import com.projectjspservlet.config.HibernateConfig;
import com.projectjspservlet.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {
    public void saveUser(User user) {
        Transaction transaction = null;

        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
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
}
