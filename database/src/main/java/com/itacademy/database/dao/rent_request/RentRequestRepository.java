package com.itacademy.database.dao.rent_request;

import com.itacademy.database.entity.enum_.RequestStatus;
import com.itacademy.database.entity.hotel.RentRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RentRequestRepository extends CrudRepository<RentRequest, Long> {

    List<RentRequest> findAllByStatus(RequestStatus status);

    Optional<RentRequest> findByResidentId(Long residentId);


}
