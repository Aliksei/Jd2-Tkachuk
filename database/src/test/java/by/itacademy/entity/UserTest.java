package by.itacademy.entity;

import by.itacademy.enteties.embedded.Credentials;
import by.itacademy.enteties.enum_.Gender;
import by.itacademy.enteties.role.Admin;
import by.itacademy.enteties.role.Resident;
import by.itacademy.enteties.role.User;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            int result = session.createQuery("delete from User ").executeUpdate();
            System.out.println(result);
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkGetAllUsers() {
        try (Session session = FACTORY.openSession()) {

            session.beginTransaction();

            Resident resident = Resident.builder()
                    .first_name("Ivan")
                    .second_name("Ivanov")
                    .credentials(Credentials.of("dsadsd", "dsadasdasd"))
                    .gender(Gender.MALE)
                    .build();

            Admin admin = Admin.builder()
                    .first_name("Admin")
                    .second_name("AdminSecondName")
                    .credentials(Credentials.of("Asdas", "sdsad"))
                    .gender(Gender.FEMALE)
                    .salary(1000)
                    .fullTime(0.75)
                    .build();

            Serializable adminId = session.save(admin);
            Serializable residentId = session.save(resident);

            assertNotNull(adminId);
            assertNotNull(residentId);

            session.clear();

            List<User> users = session.createQuery("select users from User users ", User.class).list();

            assertEquals(users.size(), 2);
            session.getTransaction().commit();
        }
    }
}
