package by.itacademy.dao.apartments;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.hotel.Apartment;
import java.util.List;

public interface ApartmentsDao extends BaseDao {
    List<Apartment> getAllApartments();

    Apartment getApartmentBId(Long id);

    Long createApartment(Apartment apartment);

    void deleteApartment(Long id);

    void updateApartment(Apartment apartment);
}
