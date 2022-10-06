package lesson_5_hibernate.abstract_dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T, ID> {

    Optional<T> findById(ID id);

    List<T> findAll();

    T saveOrUpdate(T entity);

    void saveOrUpdate(List<T> entity);

    void update(T entity);

    void deleteById(ID id);

    void delete(T entity);

}
