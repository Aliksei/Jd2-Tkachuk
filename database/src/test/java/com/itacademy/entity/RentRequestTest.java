package com.itacademy.entity;

import com.itacademy.enteties.enum_.RequestStatus;
import com.itacademy.enteties.hotel.Apartment;
import com.itacademy.enteties.hotel.RentRequest;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RentRequestTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from RentRequest ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Apartment apartment = Apartment.builder()
                    .pricePerNight(32)
                    .personCapacity(32)
                    .numberOfRooms(3)
                    .resident(null)
                    .build();

            RentRequest rentRequest = RentRequest.builder()
                    .status(RequestStatus.DRAFT)
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now())
                    .apartment(apartment)
                    .build();

            session.save(apartment);
            Serializable id = session.save(rentRequest);

            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Apartment apartment = Apartment.builder()
                    .pricePerNight(32)
                    .personCapacity(32)
                    .numberOfRooms(3)
                    .build();

            RentRequest rentRequest = RentRequest.builder()
                    .status(RequestStatus.DRAFT)
                    .startDate(LocalDate.now())
                    .endDate(LocalDate.now())
                    .apartment(apartment)
                    .build();

            Serializable id = session.save(rentRequest);
            assertNotNull(id);

            session.clear();

            RentRequest rentRequest1 = session.get(RentRequest.class, id);

            assertNotNull(rentRequest1);
            assertEquals(rentRequest1.getId(), id);
            session.getTransaction().commit();
        }
    }
}
