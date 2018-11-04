package by.itacademy.dao.city;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.catalog.City;
import java.util.List;

public interface CityDao extends BaseDao {

    List<City> getAllCity();

    City getCityById(Long id);

    Long createCity(City city);

    void deleteCity(Long id);

    void updateCity(City city);
}
