package by.itacademy.dao.apartments;

import by.itacademy.enteties.hotel.Apartment;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class ApartmentsDaoImpl implements ApartmentsDao {

    @Override
    public List<Apartment> getAllApartments() {
        LOGGER.info("Getting all Apartments from database");
        List<Apartment> apartments;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            apartments = session.createQuery("select a from Apartment a", Apartment.class).list();
            session.getTransaction().commit();
        }
        return apartments;
    }

    @Override
    public Apartment getApartmentBId(Long id) {
        LOGGER.info(format("Getting Apartment from database by Id = %d", id));
        Apartment apartment;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            apartment = session.get(Apartment.class, id);
            session.getTransaction().commit();
        }
        return apartment;
    }

    @Override
    public Long createApartment(Apartment apartment) {
        Long id;
        LOGGER.info("Creating new Apartment in database");
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Apartment saving = Apartment.builder()
                    .pricePerNight(apartment.getPricePerNight())
                    .numberOfRooms(apartment.getNumberOfRooms())
                    .personCapacity(apartment.getPersonCapacity())
                    .resident(apartment.getResident())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new Apartment with id = %d", id));
        return id;
    }

    @Override
    public void deleteApartment(Long id) {
        LOGGER.info(format("Deleting Admin by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(Apartment.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateApartment(Apartment apartment) {
        LOGGER.info("Updating apartment = " + apartment);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Apartment update = Apartment.builder()
                    .id(apartment.getId())
                    .pricePerNight(apartment.getPricePerNight())
                    .numberOfRooms(apartment.getNumberOfRooms())
                    .personCapacity(apartment.getPersonCapacity())
                    .resident(apartment.getResident())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }

}
