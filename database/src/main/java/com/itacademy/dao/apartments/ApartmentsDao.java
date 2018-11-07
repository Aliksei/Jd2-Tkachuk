package com.itacademy.dao.apartments;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.hotel.Apartment;
import java.util.List;

public interface ApartmentsDao extends BaseDao<Long, Apartment> {

    List<Apartment> getByPrice(int price);

    List<Apartment> getAvailableApartments();


}
