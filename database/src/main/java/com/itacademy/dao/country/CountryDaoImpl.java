package com.itacademy.dao.country;

import com.itacademy.dao.BaseDaoImpl;
import com.itacademy.enteties.catalog.Country;
import com.itacademy.enteties.catalog.QCountry;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryDaoImpl extends BaseDaoImpl<Long,Country> implements CountryDao {

    private static final CountryDao INSTANCE = new CountryDaoImpl();

    public static CountryDao getInstance(){
        return INSTANCE;
    }

    @Override
    public Class<Country> getEntityClass() {
        return Country.class;
    }

    @Override
    public Optional<Country> getByName(String name) {
        QCountry country = QCountry.country;
        Country result = new JPAQuery<Country>(getNewOpenedSession())
                .select(country)
                .from(country)
                .where(
                        country.name.eq(name)
                ).fetchOne();
        return Optional.ofNullable(result);
    }
}
