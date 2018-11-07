package com.itacademy.entity;

import com.itacademy.enteties.embedded.Credentials;
import com.itacademy.enteties.enum_.Gender;
import com.itacademy.enteties.hotel.Apartment;
import com.itacademy.enteties.hotel.SpaRequest;
import com.itacademy.enteties.role.Resident;
import java.io.Serializable;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SpaRequestTest {
    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from SpaRequest").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Apartment apartment = Apartment.builder()
                    .numberOfRooms(2)
                    .personCapacity(3)
                    .pricePerNight(45)
                    .build();

            session.save(apartment);

            Resident resident = Resident.builder()
                    .credentials(Credentials.of("fesfdsfds", "AdminSecodfsdfsdfsdfsdfsndName"))
                    .first_name("Ivan")
                    .second_name("Ivanonv")
                    .gender(Gender.MALE)
                    .apartment(apartment)
                    .build();

            session.save(resident);

            ArrayList<Resident> residents = new ArrayList<>();
            residents.add(resident);

            SpaRequest spaRequest = SpaRequest.builder()
                    .duration(12)
                    .spaPrice(32)
                    .procedureName("pool")
                    .residents(residents)
                    .build();

            Serializable id = session.save(spaRequest);
            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Apartment apartment = Apartment.builder()
                    .numberOfRooms(2)
                    .personCapacity(3)
                    .pricePerNight(45)
                    .build();

            session.save(apartment);

            Resident resident = Resident.builder()
                    .first_name("Ivan")
                    .second_name("Ivanonv")
                    .credentials(Credentials.of("Admin", "AdminSecondName"))
                    .gender(Gender.MALE)
                    .apartment(apartment)
                    .build();

            session.save(resident);

            ArrayList<Resident> residents = new ArrayList<>();
            residents.add(resident);

            SpaRequest spaRequest = SpaRequest.builder()
                    .duration(12)
                    .spaPrice(33)
                    .procedureName("pool")
                    .residents(residents)
                    .build();

            Serializable id = session.save(spaRequest);

            session.clear();

            SpaRequest spaRequest1 = session.get(SpaRequest.class, id);


            assertNotNull(id);
            assertEquals(spaRequest1.getId(), id);
            session.getTransaction().commit();
        }
    }

}

