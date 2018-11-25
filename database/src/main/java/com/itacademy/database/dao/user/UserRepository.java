package com.itacademy.database.dao.user;

import com.itacademy.database.entity.role.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAllByFirstNameAndSecondName(String firsName, String secondName);

    Optional<User> findByCredentialsLogin(String login);
}
