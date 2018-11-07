package com.itacademy.dao.user;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.role.User;
import java.util.List;

public interface UserDao extends BaseDao<Long, User> {

    List<User> getByFullName(String firsName, String secondName);
}
