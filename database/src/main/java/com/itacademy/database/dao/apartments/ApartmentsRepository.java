package com.itacademy.database.dao.apartments;

import com.itacademy.database.entity.hotel.Apartment;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentsRepository extends CrudRepository<Apartment, Long> {

    List<Apartment> findAllByPricePerNight(int price);

    List<Apartment> findAllByResidentIsNull();

}
