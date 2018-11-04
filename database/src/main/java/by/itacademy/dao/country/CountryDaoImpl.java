package by.itacademy.dao.country;

import by.itacademy.enteties.catalog.Country;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class CountryDaoImpl implements CountryDao {

    @Override
    public List<Country> getAllCountries() {
        LOGGER.info("Getting all Countries from database");
        List<Country> countries;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            countries = session.createQuery("select c from Country c", Country.class).list();
            session.getTransaction().commit();
        }
        return countries;
    }

    @Override
    public Country getCountryById(Long id) {
        LOGGER.info(format("Getting Country from database by Id = %d", id));
        Country country;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            country = session.get(Country.class, id);
            session.getTransaction().commit();
        }
        return country;
    }

    @Override
    public Long createCountry(Country country) {
        LOGGER.info("Creating new Country in database");
        Long id;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Country saving = Country.builder()
                    .name(country.getName())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new Country with id = %d", id));
        return id;
    }

    @Override
    public void deleteCountry(Long id) {
        LOGGER.info(format("Deleting Country by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(Country.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateCountry(Country country) {
        LOGGER.info("Updating coutry = " + country);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            Country update = Country.builder()
                    .id(country.getId())
                    .name(country.getName())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }
}
