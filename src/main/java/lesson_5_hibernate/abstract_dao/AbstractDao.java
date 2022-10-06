package lesson_5_hibernate.abstract_dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class AbstractDao<T, ID> implements Dao<T, ID> {

    private final Class<T> tClass;

    private final SessionFactory sessionFactory;

    private final String className;

    public AbstractDao(Class<T> entityClass, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        tClass = entityClass;
        className = tClass.getSimpleName();
    }

    @Override
    public Optional<T> findById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            Optional<T> entity = Optional.ofNullable(session.find(tClass, id));
            session.getTransaction().commit();
            return entity;
        }
    }

    public List<T> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            List<T> entities = session.createQuery("select e from " + className + " e").getResultList();
            session.getTransaction().commit();
            return entities;
        }
    }


    @Override
    public T saveOrUpdate(T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void saveOrUpdate(List<T> entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            entity.forEach(session::saveOrUpdate);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.createQuery("delete e from " + className + " e where e.id = :id", tClass);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

}
