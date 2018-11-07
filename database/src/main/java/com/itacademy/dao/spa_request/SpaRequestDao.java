package com.itacademy.dao.spa_request;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.hotel.SpaRequest;
import java.util.List;

public interface SpaRequestDao extends BaseDao<Long, SpaRequest> {

    List<SpaRequest> getByProcedureName(String procedureName);

    List<SpaRequest> getWherePriceMoreThan(int price);

    List<SpaRequest> getWherePriceLessThan(int price);


}
