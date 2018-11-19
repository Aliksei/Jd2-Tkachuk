package com.itacademy.database.dao.resident;

import com.itacademy.database.entity.role.QResident;
import com.itacademy.database.entity.role.Resident;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class ResidentRepoImpl implements ResidentRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Resident> getByFilter(int limit, int offset, com.querydsl.core.types.Predicate... predicates) {
        QResident resident = QResident.resident;
        List<Resident> residents = new JPAQuery<Resident>(entityManager)
                .select(resident)
                .from(resident)
                .where(
                        predicates
                ).limit(limit)
                .offset(offset)
                .fetch();
        entityManager.close();
        return residents;
    }
}
