package tn.esprit.autoloc.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.BeanUtils;
import tn.esprit.autoloc.web.exception.ResourceNotFoundException;

import java.util.List;

public abstract class AbstractCrudService<T> implements CrudService<T> {

    private final JpaRepository<T, Long> repository;
    private final String entityName;
    private final String idProperty;

    protected AbstractCrudService(JpaRepository<T, Long> repository, String entityName, String idProperty) {
        this.repository = repository;
        this.entityName = entityName;
        this.idProperty = idProperty;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " introuvable avec l'identifiant " + id));
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(Long id, T entity) {
        T existing = findById(id);
        BeanUtils.copyProperties(entity, existing, idProperty);
        return repository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
