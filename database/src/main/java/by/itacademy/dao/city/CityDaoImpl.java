package by.itacademy.dao.city;

import by.itacademy.enteties.catalog.City;
import java.util.List;
import org.hibernate.Session;

import static by.itacademy.connector.PostgresPoolManager.getNewOpenedSession;
import static java.lang.String.format;

public class CityDaoImpl implements CityDao {

    @Override
    public List<City> getAllCity() {
        LOGGER.info("Getting all Cities from database");
        List<City> cities;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            cities = session.createQuery("select c from City c", City.class).list();
            session.getTransaction().commit();
        }
        return cities;
    }

    @Override
    public City getCityById(Long id) {
        LOGGER.info(format("Getting City from database by Id = %d", id));
        City city;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            city = session.get(City.class, id);
            session.getTransaction().commit();
        }
        return city;
    }

    @Override
    public Long createCity(City city) {
        LOGGER.info("Creating new City in database");
        Long id;
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            City saving = City.builder()
                    .name(city.getName())
                    .build();

            id = (Long) session.save(saving);
            session.getTransaction().commit();
        }
        LOGGER.info(format("Created new City with id = %d", id));
        return id;
    }

    @Override
    public void deleteCity(Long id) {
        LOGGER.info(format("Deleting City by id = %d", id));
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();
            session.delete(session.get(City.class, id));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateCity(City city) {
        LOGGER.info("Updating city = " + city);
        try (Session session = getNewOpenedSession()) {
            session.beginTransaction();

            City update = City.builder()
                    .id(city.getId())
                    .name(city.getName())
                    .build();

            session.saveOrUpdate(update);
            session.getTransaction().commit();
        }
    }
}
