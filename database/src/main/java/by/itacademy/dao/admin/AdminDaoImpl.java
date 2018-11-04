package by.itacademy.dao.admin;

import by.itacademy.enteties.role.Admin;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class AdminDaoImpl implements AdminDao {

    @Override
    public List<Admin> getAllAdmins() {
        LOGGER.info("Getting all admins from database");
        List<Admin> admins;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            admins = session.createQuery("select u from Admin u", Admin.class).list();
            session.getTransaction().commit();
        }
        return admins;
    }

    @Override
    public Admin getAdminById(Long id) {
        LOGGER.info(format("Getting admin from database by Id = %d", id));
        Admin admin;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            admin = session.get(Admin.class, id);
            session.getTransaction().commit();
        }
        return admin;
    }

    @Override
    public Long createAdmin(Admin admin) {
        LOGGER.info("Creating new Admin in database");
        Long id;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Admin saving = Admin.builder()
                    .credentials(admin.getCredentials())
                    .first_name(admin.getFirst_name())
                    .second_name(admin.getSecond_name())
                    .gender(admin.getGender())
                    .salary(admin.getSalary())
                    .fullTime(admin.getFullTime())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new Admin with id = %d", id));
        return id;
    }

    @Override
    public void deleteAdmin(Long id) {
        LOGGER.info(format("Deleting Admin by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(Admin.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        LOGGER.info("Updating Admin = " + admin);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Admin update = Admin.builder()
                    .id(admin.getId())
                    .credentials(admin.getCredentials())
                    .first_name(admin.getFirst_name())
                    .second_name(admin.getSecond_name())
                    .gender(admin.getGender())
                    .salary(admin.getSalary())
                    .fullTime(admin.getFullTime())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }
}
