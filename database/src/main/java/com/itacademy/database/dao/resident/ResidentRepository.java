package com.itacademy.database.dao.resident;

import com.itacademy.database.entity.role.Resident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long>, ResidentRepo {

    List<Resident> findByFirstNameAndSecondName(String firsName, String secondName);

    List<Resident> findResidentsByApartmentNotNull();

}
