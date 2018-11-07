package com.itacademy.dao.spa_request;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.hotel.QSpaRequest;
import com.itacademy.enteties.hotel.SpaRequest;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpaRequestDaoImpl extends BaseDaoImpl<Long,SpaRequest> implements SpaRequestDao {

    private static final SpaRequestDao INSTANCE = new SpaRequestDaoImpl();

    public static SpaRequestDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<SpaRequest> getEntityClass() {
        return SpaRequest.class;
    }

    @Override
    public List<SpaRequest> getByProcedureName(String procedureName) {
        QSpaRequest spaRequest = QSpaRequest.spaRequest;
        return new JPAQuery<SpaRequest>(getNewOpenedSession())
                .select(spaRequest)
                .from(spaRequest)
                .where(
                        spaRequest.procedureName.eq(procedureName)
                ).fetch();
    }

    @Override
    public List<SpaRequest> getWherePriceMoreThan(int price) {
        QSpaRequest spaRequest = QSpaRequest.spaRequest;
        return new JPAQuery<SpaRequest>(getNewOpenedSession())
                .select(spaRequest)
                .from(spaRequest)
                .where(
                        spaRequest.spaPrice.lt(price)
                ).fetch();
    }

    @Override
    public List<SpaRequest> getWherePriceLessThan(int price) {
        QSpaRequest spaRequest = QSpaRequest.spaRequest;
        return new JPAQuery<SpaRequest>(getNewOpenedSession())
                .select(spaRequest)
                .from(spaRequest)
                .where(
                        spaRequest.spaPrice.lt(price)
                ).fetch();
    }
}
