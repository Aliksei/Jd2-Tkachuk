package com.itacademy.dao;

import com.itacademy.enteties.BaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import lombok.Cleanup;
import org.hibernate.Session;

import static com.itacademy.connector.PostgresPoolManager.getNewOpenedSession;

public abstract class BaseDaoImpl<PK extends Serializable, E extends BaseEntity<PK>> implements BaseDao<PK, E> {

    public abstract Class<E> getEntityClass();

    @Override
    public Optional<E> findById(PK id) {
        @Cleanup Session session = getNewOpenedSession();
        E e = session.find(getEntityClass(), id);
        return Optional.ofNullable(e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public PK save(E entity) {
        @Cleanup Session session = getNewOpenedSession();
        session.beginTransaction();
        Serializable id = session.save(entity);
        session.getTransaction().commit();
        return (PK) id;
    }

    @Override
    public void update(E entity) {
        @Cleanup Session session = getNewOpenedSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(E entity) {
        @Cleanup Session session = getNewOpenedSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    @Override
    public List<E> findAll() {
        Class<E> entityClass = getEntityClass();
        @Cleanup Session session = getNewOpenedSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(entityClass);
        criteria.select(criteria.from(entityClass));
        return session.createQuery(criteria).list();
    }
}
