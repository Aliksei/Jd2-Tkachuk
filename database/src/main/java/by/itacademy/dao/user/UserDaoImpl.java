package by.itacademy.dao.user;

import by.itacademy.enteties.role.Admin;
import by.itacademy.enteties.role.Resident;
import by.itacademy.enteties.role.User;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("Getting all SpaRequests from database");
        List<User> users;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            users = session.createQuery("select users from User users ", User.class).list();

            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public User getUserById(Long id) {
        LOGGER.info(format("Getting User from database by Id = %d", id));
        User user;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            user = session.find(User.class, id);

            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public Long createUser(User user) {
        LOGGER.info("Creating new User in database");
        Long id;
        User saving = null;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                saving = Admin.builder()
                        .credentials(admin.getCredentials())
                        .first_name(admin.getFirst_name())
                        .second_name(admin.getSecond_name())
                        .gender(admin.getGender())
                        .salary(admin.getSalary())
                        .fullTime(admin.getFullTime())
                        .build();
            }
            if (user instanceof Resident) {
                Resident resident = (Resident) user;
                saving = Resident.builder()
                        .credentials(resident.getCredentials())
                        .first_name(resident.getFirst_name())
                        .second_name(resident.getSecond_name())
                        .gender(resident.getGender())
                        .apartment(resident.getApartment())
                        .build();
            }
            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new SpaRequest with id = %d", id));
        return id;
    }

    @Override
    public void deleteUser(Long id) {
        LOGGER.info(format("Deleting User by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateUser(User user) {
        LOGGER.info("Updating User = " + user);
        User saving = null;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                saving = Admin.builder()
                        .credentials(admin.getCredentials())
                        .first_name(admin.getFirst_name())
                        .second_name(admin.getSecond_name())
                        .gender(admin.getGender())
                        .salary(admin.getSalary())
                        .fullTime(admin.getFullTime())
                        .build();
            }
            if (user instanceof Resident) {
                Resident resident = (Resident) user;
                saving = Resident.builder()
                        .credentials(resident.getCredentials())
                        .first_name(resident.getFirst_name())
                        .second_name(resident.getSecond_name())
                        .gender(resident.getGender())
                        .apartment(resident.getApartment())
                        .build();
            }
            session.save(saving);
            session.getTransaction().commit();
        }
    }

}
