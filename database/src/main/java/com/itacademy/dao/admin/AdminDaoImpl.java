package com.itacademy.dao.admin;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.role.Admin;
import com.itacademy.enteties.role.QAdmin;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminDaoImpl extends BaseDaoImpl<Long,Admin> implements AdminDao {

    private static final AdminDao INSTANCE = new AdminDaoImpl();

    public static AdminDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }

    @Override
    public List<Admin> getByFullName(String firsName, String secondName) {
        QAdmin admin = QAdmin.admin;
        return new JPAQuery<Admin>(getNewOpenedSession())
                .select(admin)
                .from(admin)
                .where(
                        admin.first_name.eq(firsName),
                        admin.second_name.eq(secondName)
                ).fetch();
    }

    @Override
    public List<Admin> getWhereSalaryMoreThan(int salary) {
        QAdmin admin = QAdmin.admin;
        return new JPAQuery<Admin>(getNewOpenedSession())
                .select(admin)
                .from(admin)
                .where(
                        admin.salary.gt(salary)
                ).fetch();
    }

    @Override
    public List<Admin> getWhereSalaryLessThan(int salary) {
        QAdmin admin = QAdmin.admin;
        return new JPAQuery<Admin>(getNewOpenedSession())
                .select(admin)
                .from(admin)
                .where(
                        admin.salary.lt(salary)
                ).fetch();
    }

}

