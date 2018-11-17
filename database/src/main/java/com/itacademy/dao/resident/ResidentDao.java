package com.itacademy.dao.resident;

import com.itacademy.dao.BaseDao;
import com.itacademy.enteties.role.Resident;
import com.querydsl.core.types.Predicate;
import java.util.List;

public interface ResidentDao extends BaseDao<Long, Resident> {

    Long getCount();

    List<Resident> getByFullName(String firsName, String secondName);

    List<Resident> getAcommodatedResidents();

    List<Resident> getByFilter(int limit, int offset, Predicate... predicates);
}
