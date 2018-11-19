package com.itacademy.database.dao.country;

import com.itacademy.database.entity.catalog.Country;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {

    Optional<Country> findByName(String name);

}
