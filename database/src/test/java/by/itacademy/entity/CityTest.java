package by.itacademy.entity;

import by.itacademy.enteties.catalog.City;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CityTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from City ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            City city = City.builder()
                    .name("Minsk")
                    .build();
            Serializable id = session.save(city);
            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            City city = City.builder()
                    .name("Minsk")
                    .build();
            Serializable id = session.save(city);
            assertNotNull(id);

            session.clear();

            City city1 = session.get(City.class, id);

            assertNotNull(city1);
            assertEquals(city1.getId(), id);
            session.getTransaction().commit();
        }
    }

}
