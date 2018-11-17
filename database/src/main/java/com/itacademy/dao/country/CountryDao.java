package com.itacademy.dao.country;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.catalog.Country;
import java.util.Optional;

public interface CountryDao extends BaseDao<Long, Country> {

    Optional<Country> getByName(String name);

}
