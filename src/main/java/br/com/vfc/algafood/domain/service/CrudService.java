package br.com.vfc.algafood.domain.service;

import br.com.vfc.algafood.domain.model.BaseEntity;

import java.util.List;

public interface CrudService<T extends BaseEntity> {

    List<T> list();

    T findById(Long id);

    T save(T entity);

    void remove(Long id);
}
