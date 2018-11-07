package com.itacademy.dao.rent_request;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.enum_.RequestStatus;
import com.itacademy.enteties.hotel.QRentRequest;
import com.itacademy.enteties.hotel.RentRequest;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RentRequestDaoImpl extends BaseDaoImpl<Long,RentRequest> implements RentRequestDao {

    private static final RentRequestDao INSTANCE = new RentRequestDaoImpl();

    public static RentRequestDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<RentRequest> getEntityClass() {
        return null;
    }

    @Override
    public List<RentRequest> getDraftRequests() {
        QRentRequest rentRequest = QRentRequest.rentRequest;
        return new JPAQuery<RentRequest>(getNewOpenedSession())
                .select(rentRequest)
                .from(rentRequest)
                .where(
                        rentRequest.status.eq(RequestStatus.DRAFT)
                ).fetch();
    }

    @Override
    public List<RentRequest> getApprovedRequests() {
        QRentRequest rentRequest = QRentRequest.rentRequest;
        return new JPAQuery<RentRequest>(getNewOpenedSession())
                .select(rentRequest)
                .from(rentRequest)
                .where(
                        rentRequest.status.eq(RequestStatus.APPROVED)
                ).fetch();
    }

    @Override
    public Optional<RentRequest> getRequestByResident(Long residentId) {
        QRentRequest rentRequest = QRentRequest.rentRequest;
        RentRequest result = new JPAQuery<RentRequest>(getNewOpenedSession())
                .select(rentRequest)
                .from(rentRequest)
                .where(
                        rentRequest.resident.id.eq(residentId)
                ).fetchOne();
        return Optional.ofNullable(result);
    }
}
