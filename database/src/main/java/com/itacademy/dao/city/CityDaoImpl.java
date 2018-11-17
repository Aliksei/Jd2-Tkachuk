package com.itacademy.dao.city;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.catalog.City;
import com.itacademy.enteties.catalog.QCity;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CityDaoImpl extends BaseDaoImpl<Long,City> implements CityDao {

    private static final CityDao INSTANCE = new CityDaoImpl();

    public static CityDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }

    @Override
    public Optional<City> getByName(String name) {
        QCity city = QCity.city;
        City result = new JPAQuery<City>(getNewOpenedSession())
                .select(city)
                .from(city)
                .where(
                        city.name.eq(name)
                ).fetchOne();
        return Optional.ofNullable(result);
    }
}
