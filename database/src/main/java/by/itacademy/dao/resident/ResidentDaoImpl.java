package by.itacademy.dao.resident;

import by.itacademy.enteties.role.Resident;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class ResidentDaoImpl implements ResidentDao {

    @Override
    public List<Resident> getAllResidents() {
        LOGGER.info("Getting all Residents from database");
        List<Resident> residents;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            residents = session.createQuery("select u from Resident u", Resident.class).list();
            session.getTransaction().commit();
        }
        return residents;
    }

    @Override
    public Resident getResidentById(Long id) {
        LOGGER.info(format("Getting Resident from database by Id = %d", id));
        Resident resident;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            resident = session.get(Resident.class, id);
            session.getTransaction().commit();
        }
        return resident;
    }

    @Override
    public Long createResident(Resident resident) {
        LOGGER.info("Creating new Resident in database");
        Long id;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();

            cb.createQuery();

            Resident saving = Resident.builder()
                    .credentials(resident.getCredentials())
                    .first_name(resident.getFirst_name())
                    .second_name(resident.getSecond_name())
                    .gender(resident.getGender())
                    .apartment(resident.getApartment())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new Resident with id = %d", id));
        return id;
    }

    @Override
    public void deleteResident(Long id) {
        LOGGER.info(format("Deleting Resident by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(Resident.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateResident(Resident resident) {
        LOGGER.info("Updating Resident = " + resident);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Resident update = Resident.builder()
                    .id(resident.getId())
                    .first_name(resident.getFirst_name())
                    .second_name(resident.getSecond_name())
                    .gender(resident.getGender())
//                    .details(resident.getDetails())
                    .apartment(resident.getApartment())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }
}
