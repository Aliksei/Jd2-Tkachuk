package com.itacademy.database.dao.spa_request;

import com.itacademy.database.entity.hotel.SpaRequest;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SpaRequestRepository extends CrudRepository<SpaRequest, Long> {

    List<SpaRequest> findAllByProcedureName(String procedureName);

    List<SpaRequest> findAllBySpaPriceGreaterThan(int price);

    List<SpaRequest> findAllBySpaPriceLessThan(int price);

}
