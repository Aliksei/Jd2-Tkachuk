package com.itacademy.dao.user;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.role.QUser;
import com.itacademy.enteties.role.User;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDaoImpl extends BaseDaoImpl<Long,User> implements UserDao {

    private static final UserDao INSTANCE = new UserDaoImpl();

    public static UserDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public List<User> getByFullName(String firsName, String secondName) {
        QUser user = QUser.user;
        return new JPAQuery<User>(getNewOpenedSession())
                .select(user)
                .from(user)
                .where(
                        user.first_name.eq(firsName),
                        user.second_name.eq(secondName)
                ).fetch();
    }
}
