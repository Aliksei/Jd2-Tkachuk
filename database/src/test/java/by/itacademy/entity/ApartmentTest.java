package by.itacademy.entity;

import by.itacademy.enteties.hotel.Apartment;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApartmentTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Apartment").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Apartment apartment = Apartment.builder()
                    .numberOfRooms(1)
                    .personCapacity(2)
                    .pricePerNight(25)
                    .build();
            Serializable id = session.save(apartment);
            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Apartment apartment = Apartment.builder()
                    .numberOfRooms(1)
                    .personCapacity(2)
                    .pricePerNight(25)
                    .build();
            Serializable id = session.save(apartment);
            assertNotNull(id);

            session.clear();

            Apartment apartment1 = session.get(Apartment.class, id);

            assertNotNull(apartment1);
            assertEquals(apartment1.getId(), id);
            session.getTransaction().commit();
        }
    }

}
