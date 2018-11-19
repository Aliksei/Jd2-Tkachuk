package com.itacademy.database.dao.city;

import com.itacademy.database.entity.catalog.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

    City findByName(String name);

}
