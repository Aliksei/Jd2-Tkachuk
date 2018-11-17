package com.itacademy.dao;

import com.itacademy.enteties.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

public interface BaseDao<PK extends Serializable, E extends BaseEntity<PK>>{

    Logger LOGGER = Logger.getLogger(BaseDao.class);

    Optional<E> findById(PK id);

    PK save(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> findAll();
}
