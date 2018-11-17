package com.itacademy.dao.resident;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.role.QResident;
import com.itacademy.enteties.role.Resident;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResidentDaoImpl extends BaseDaoImpl<Long,Resident> implements ResidentDao {

    private static final ResidentDao INSTANCE = new ResidentDaoImpl();

    public static ResidentDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<Resident> getEntityClass() {
        return Resident.class;
    }

    @Override
    public Long getCount() {
        QResident resident = QResident.resident;
        return new JPAQuery<Long>(getNewOpenedSession()).from(resident).fetchCount();
    }

    @Override
    public List<Resident> getByFullName(String firsName, String secondName) {
        QResident resident = QResident.resident;
        return new JPAQuery<Resident>(getNewOpenedSession())
                .select(resident)
                .from(resident)
                .where(
                        resident.first_name.eq(firsName),
                        resident.second_name.eq(secondName)
                ).fetch();
    }

    @Override
    public List<Resident> getAcommodatedResidents() {
        QResident resident = QResident.resident;
        return new JPAQuery<Resident>(getNewOpenedSession())
                .select(resident)
                .from(resident)
                .where(
                        resident.apartment.isNull()
                ).fetch();
    }

    @Override
    public List<Resident> getByFilter(int limit, int offset, com.querydsl.core.types.Predicate... predicates) {
        QResident resident = QResident.resident;
        return new JPAQuery<Resident>(getNewOpenedSession())
                .select(resident)
                .from(resident)
                .where(
                        predicates
                ).limit(limit)
                .offset(offset)
                .fetch();
    }

}
