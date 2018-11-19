package com.itacademy.database.dao;

import com.itacademy.database.config.TestConfiguration;
import com.itacademy.database.dao.resident.ResidentRepository;
import com.itacademy.database.entity.embedded.Credentials;
import com.itacademy.database.entity.enum_.Gender;
import com.itacademy.database.entity.role.Resident;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;

    @Test
    public void checkSave() {
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Resident id = residentRepository.save(resident);
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


        Long id = residentRepository.save(resident).getId();
        assertNotNull(id);

        Optional<Resident> byId = residentRepository.findById(id);
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

        Resident id = residentRepository.save(resident);
        Resident id1 = residentRepository.save(resident1);
        assertNotNull(id);
        assertNotNull(id1);

        Iterable<Resident> list = residentRepository.findAll();

        list.forEach(Assert::assertNotNull);
    }

    @Test
    public void checkUpdate(){
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Long id = residentRepository.save(resident).getId();

        resident.setFirstName("Andrey");
        resident.setSecondName("Efremov");

        residentRepository.save(resident);

        Optional<Resident> byId = residentRepository.findById(id);

        assertTrue(byId.isPresent());
        Resident resident1 = byId.get();
        assertEquals("Andrey", resident1.getFirstName());
        assertEquals("Efremov", resident1.getSecondName());
    }

    @Test
    public void checkDelete(){
        Resident resident = Resident.builder()
                .first_name("Ivan")
                .second_name("Ivanov")
                .credentials(Credentials.of("login", "qwerty"))
                .gender(Gender.MALE)
                .build();

        Resident resident1 = residentRepository.save(resident);
        residentRepository.delete(resident);
        Optional<Resident> byId = residentRepository.findById(resident1.getId());
        assertFalse(byId.isPresent());
    }

}
