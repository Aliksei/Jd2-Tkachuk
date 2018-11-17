package com.itacademy.connector;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostgresPoolManager {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return FACTORY;
    }

    public static Session getNewOpenedSession() {
        return FACTORY.openSession();
    }

}
