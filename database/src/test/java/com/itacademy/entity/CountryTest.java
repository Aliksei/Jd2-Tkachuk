package com.itacademy.entity;

import com.itacademy.enteties.catalog.Country;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CountryTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Country ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Country country = Country.builder()
                    .name("Belarus")
                    .build();
            Serializable id = session.save(country);
            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Country country = Country.builder()
                    .name("Belarus")
                    .build();
            Serializable id = session.save(country);
            assertNotNull(id);

            session.clear();

            Country country1 = session.get(Country.class, id);

            assertNotNull(country1);
            assertEquals(country1.getId(), id);
            session.getTransaction().commit();
        }
    }

}
