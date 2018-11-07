package com.itacademy.dao;

import com.itacademy.dao.resident.ResidentDao;
import com.itacademy.dao.resident.ResidentDaoImpl;
import com.itacademy.enteties.embedded.Credentials;
import com.itacademy.enteties.enum_.Gender;
import com.itacademy.enteties.role.Resident;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ResidentDaoTest {

    private ResidentDao residentDao = ResidentDaoImpl.getInstance();
    private SessionFactory sessionFactory;

    @Before
    public void initDb() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @After
    public void finish() {
        sessionFactory.close();
    }

    @Test
    public void checkSave() {
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Long id = residentDao.save(resident);
        assertNotNull(id);
    }

    @Test
    public void checkFindById() {
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Long id = residentDao.save(resident);
        assertNotNull(id);

        Optional<Resident> byId = residentDao.findById(id);
        assertTrue(byId.isPresent());
    }

    @Test
    public void checkFindAll() {
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Resident resident1 = Resident.builder()
                .first_name("Masha")
                .second_name("Masha")
                .credentials(Credentials.of("lsdogin", "qwesrty"))
                .gender(Gender.FEMALE)
                .build();

        Long id = residentDao.save(resident);
        Long id1 = residentDao.save(resident1);
        assertNotNull(id);
        assertNotNull(id1);

        List<Resident> list = residentDao.findAll();

        assertEquals(2, list.size());
    }

    @Test
    public void checkUpdate(){
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Long id = residentDao.save(resident);

        resident.setFirst_name("Andrey");
        resident.setSecond_name("Efremov");

        residentDao.update(resident);

        Optional<Resident> byId = residentDao.findById(id);

        assertTrue(byId.isPresent());
        Resident resident1 = byId.get();
        assertEquals("Andrey", resident1.getFirst_name());
        assertEquals("Efremov", resident1.getSecond_name());
    }

    @Test
    public void checkDelete(){
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Long id = residentDao.save(resident);
        residentDao.delete(resident);
        Optional<Resident> byId = residentDao.findById(id);
        assertFalse(byId.isPresent());
    }

}
