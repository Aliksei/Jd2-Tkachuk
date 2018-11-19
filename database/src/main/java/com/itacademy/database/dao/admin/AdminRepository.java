package com.itacademy.database.dao.admin;

import com.itacademy.database.entity.role.Admin;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {

    List<Admin> findAllByFirstNameAndSecondName(String firsName, String secondName);

}
