package com.itacademy.dao.apartments;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.hotel.Apartment;
import com.itacademy.enteties.hotel.QApartment;
import com.itacademy.enteties.role.QResident;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApartmentsDaoImpl extends BaseDaoImpl<Long,Apartment> implements ApartmentsDao {

    private static final ApartmentsDao INSTANCE = new ApartmentsDaoImpl();

    public static ApartmentsDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<Apartment> getEntityClass() {
        return Apartment.class;
    }

    @Override
    public List<Apartment> getByPrice(int price) {
        QApartment apartment = QApartment.apartment;
        return new JPAQuery<Apartment>(getNewOpenedSession())
                .select(apartment)
                .from(apartment)
                .where(
                        apartment.pricePerNight.eq(price)
                ).fetch();
    }

    @Override
    public List<Apartment> getAvailableApartments() {
        QApartment apartment = QApartment.apartment;
        return new JPAQuery<Apartment>(getNewOpenedSession())
                .select(apartment)
                .from(apartment)
                .leftJoin(QResident.resident)
                .where(
                        apartment.resident.isNotNull()
                ).fetch();
    }

}
