package by.itacademy.dao.spa_request;

import by.itacademy.enteties.hotel.SpaRequest;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class SpaDaoImpl implements SpaRequestDao {

    @Override
    public List<SpaRequest> getAllSpaRequests() {
        LOGGER.info("Getting all SpaRequests from database");
        List<SpaRequest> spaRequests;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            spaRequests = session.createQuery("select u from SpaRequest u", SpaRequest.class).list();
            session.getTransaction().commit();
        }
        return spaRequests;
    }

    @Override
    public SpaRequest getSpaRequestById(Long id) {
        LOGGER.info(format("Getting SpaRequest from database by Id = %d", id));
        SpaRequest spaRequest;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            spaRequest = session.get(SpaRequest.class, id);
            session.getTransaction().commit();
        }
        return spaRequest;
    }

    @Override
    public Long createSpaRequest(SpaRequest spaRequest) {
        LOGGER.info("Creating new SpaRequest in database");
        Long id;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            SpaRequest saving = SpaRequest.builder()
                    .duration(spaRequest.getDuration())
                    .spaPrice(spaRequest.getSpaPrice())
                    .procedureName(spaRequest.getProcedureName())
                    .residents(spaRequest.getResidents())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new SpaRequest with id = %d", id));
        return id;
    }

    @Override
    public void deleteSpaReqeust(Long id) {
        LOGGER.info(format("Deleting SpaRequest by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(SpaRequest.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateSpaRequest(SpaRequest spaRequest) {
        LOGGER.info("Updating SpaRequest = " + spaRequest);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            SpaRequest update = SpaRequest.builder()
                    .id(spaRequest.getId())
                    .duration(spaRequest.getDuration())
                    .spaPrice(spaRequest.getSpaPrice())
                    .procedureName(spaRequest.getProcedureName())
                    .residents(spaRequest.getResidents())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }
}
