package com.mantzavelas.petclinicspringapp.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<ID> findAll();

    T findById();

    T save(T object);

    void delete(T object);

    void deleteById(ID objectId);
}
