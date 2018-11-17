package com.itacademy.dao.city;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.catalog.City;
import java.util.Optional;

public interface CityDao extends BaseDao<Long, City> {

    Optional<City> getByName(String name);

}
