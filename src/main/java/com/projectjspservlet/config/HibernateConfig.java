package com.projectjspservlet.config;


import com.projectjspservlet.entity.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;


public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
                settings.put(AvailableSettings.URL, "jdbc:postgresql://localhost:5432/rentalcar?useSSL=false");
                settings.put(AvailableSettings.USER, "postgres");
                settings.put(AvailableSettings.PASS, "asdasd");
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                settings.put(AvailableSettings.SHOW_SQL, "true");

                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(AvailableSettings.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
}
