package tn.esprit.autoloc.service;

import java.util.List;

public interface CrudService<T> {

    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    T update(Long id, T entity);

    void deleteById(Long id);
}
