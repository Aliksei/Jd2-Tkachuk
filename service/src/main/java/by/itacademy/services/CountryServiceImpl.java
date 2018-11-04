package by.itacademy.services;

import by.itacademy.dao.country.CountryDao;
import by.itacademy.dao.country.CountryDaoImpl;
import by.itacademy.enteties.catalog.Country;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CountryServiceImpl implements CountryService {

    private static final CountryServiceImpl INSTANCE = new CountryServiceImpl();

    private final CountryDao countryDao = new CountryDaoImpl();

    public static CountryServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryDao.getAllCountries();
    }

}
