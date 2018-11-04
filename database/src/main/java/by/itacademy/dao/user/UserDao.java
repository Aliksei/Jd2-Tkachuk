package by.itacademy.dao.user;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.role.User;
import java.util.List;

public interface UserDao extends BaseDao {
    List<User> getAllUsers();

    User getUserById(Long id);

    Long createUser(User user);

    void deleteUser(Long id);

    void updateUser(User user);
}
