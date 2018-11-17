package com.itacademy.dao.rent_request;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.hotel.RentRequest;
import java.util.List;
import java.util.Optional;

public interface RentRequestDao extends BaseDao<Long, RentRequest> {

    List<RentRequest> getDraftRequests();

    List<RentRequest> getApprovedRequests();

    Optional<RentRequest> getRequestByResident(Long residentId);



}
