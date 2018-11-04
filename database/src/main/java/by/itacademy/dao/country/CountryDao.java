package by.itacademy.dao.country;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.catalog.Country;
import java.util.List;

public interface CountryDao extends BaseDao {

    List<Country> getAllCountries();

    Country getCountryById(Long id);

    Long createCountry(Country country);

    void deleteCountry(Long id);

    void updateCountry(Country country);
}
