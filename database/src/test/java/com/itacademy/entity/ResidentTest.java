package com.itacademy.entity;

import com.itacademy.enteties.embedded.Credentials;
import com.itacademy.enteties.enum_.Gender;
import com.itacademy.enteties.role.Resident;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResidentTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Resident ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Resident resident = Resident.builder()
                    .first_name("Masha")
                    .second_name("Ivanova")
                    .gender(Gender.FEMALE)
                    .credentials(Credentials.of("Admin", "AdminSecondName"))
                    .build();

            Serializable id = session.save(resident);
            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Resident resident = Resident.builder()
                    .first_name("Ivan")
                    .second_name("Ivanov")
                    .gender(Gender.MALE)
                    .credentials(Credentials.of("Admin", "AdminSecondName"))
                    .build();

            Serializable id = session.save(resident);
            assertNotNull(id);

            session.clear();

            Resident resident1 = session.get(Resident.class, id);

            assertNotNull(resident1);
            assertEquals(resident1.getId(), id);
            session.getTransaction().commit();
        }
    }
}
