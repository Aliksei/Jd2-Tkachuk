package com.itacademy.dao.admin;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.role.Admin;
import java.util.List;

public interface AdminDao extends BaseDao<Long, Admin> {

    List<Admin> getByFullName(String firsName, String secondName);

    List<Admin> getWhereSalaryMoreThan(int salary);

    List<Admin> getWhereSalaryLessThan(int salary);

}
