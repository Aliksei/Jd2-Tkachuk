package by.itacademy.dao.rent_request;

import by.itacademy.dao.BaseDao;
import by.itacademy.enteties.hotel.RentRequest;
import java.util.List;

public interface RentRequestDao extends BaseDao {
    List<RentRequest> getAllRentRequests();

    RentRequest getRentRequestById(Long id);

    Long createRentRequest(RentRequest rentRequest);

    void deleteRentRequest(Long id);

    void updateRentRequest(RentRequest rentRequest);
}
