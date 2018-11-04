package by.itacademy.dao.rent_request;

import by.itacademy.enteties.hotel.RentRequest;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class RentRequestDaoImpl implements RentRequestDao {

    @Override
    public List<RentRequest> getAllRentRequests() {
        LOGGER.info("Getting all RentRequest from database");
        List<RentRequest> rentRequests;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            rentRequests = session.createQuery("select r from RentRequest r", RentRequest.class).list();
            session.getTransaction().commit();
        }
        return rentRequests;
    }

    @Override
    public RentRequest getRentRequestById(Long id) {
        LOGGER.info(format("Getting RentRequest from database by Id = %d", id));
        RentRequest rentRequest;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            rentRequest = session.get(RentRequest.class, id);
            session.getTransaction().commit();
        }
        return rentRequest;
    }

    @Override
    public Long createRentRequest(RentRequest rentRequest) {
        LOGGER.info("Creating new RentRequest in database");
        Long id;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            RentRequest saving = RentRequest.builder()
                    .status(rentRequest.getStatus())
                    .apartment(rentRequest.getApartment())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new RentRequest with id = %d", id));
        return id;
    }

    @Override
    public void deleteRentRequest(Long id) {
        LOGGER.info(format("Deleting RentRequest by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(RentRequest.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateRentRequest(RentRequest rentRequest) {
        LOGGER.info("Updating RentRequest = " + rentRequest);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            RentRequest update = RentRequest.builder()
                    .id(rentRequest.getId())
                    .apartment(rentRequest.getApartment())
                    .status(rentRequest.getStatus())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }

}
