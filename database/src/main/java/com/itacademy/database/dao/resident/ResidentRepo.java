package com.itacademy.database.dao.resident;

import com.itacademy.database.entity.role.Resident;
import java.util.List;

public interface ResidentRepo {

    List<Resident> getByFilter(int limit, int offset, com.querydsl.core.types.Predicate... predicates);

}
