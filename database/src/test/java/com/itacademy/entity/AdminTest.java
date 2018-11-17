package com.itacademy.entity;

import com.itacademy.enteties.embedded.Credentials;
import com.itacademy.enteties.enum_.Gender;
import com.itacademy.enteties.role.Admin;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdminTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from Admin").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Admin admin = Admin.builder()
                    .first_name("sdasdas")
                    .second_name("Sdasdasaeeas")
                    .credentials(Credentials.of("Admin", "AdminSecondName"))
                    .gender(Gender.MALE)
                    .salary(1000)
                    .fullTime(0.75)
                    .build();

            Serializable id = session.save(admin);
            assertNotNull(id);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetById() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            Admin admin = Admin.builder()
                    .first_name("Vasia")
                    .second_name("Pupkin")
                    .credentials(Credentials.of("Admin", "AdminSecondName"))
                    .gender(Gender.FEMALE)
                    .salary(1000)
                    .fullTime(0.75)
                    .build();

            Serializable id = session.save(admin);
            assertNotNull(id);

            session.clear();

            Admin admin1 = session.get(Admin.class, id);

            assertNotNull(admin1);
            assertEquals(admin1.getId(), id);
            session.getTransaction().commit();
        }
    }
}
