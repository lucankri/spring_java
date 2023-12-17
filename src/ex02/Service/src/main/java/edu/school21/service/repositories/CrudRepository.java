package edu.school21.service.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    Optional<T> findById(Long id);
    List<T> findAll();
    class EmailExistsException extends RuntimeException {}
    void save(T entity) throws EmailExistsException;
    void update(T entity);
    void delete(Long id);
}
