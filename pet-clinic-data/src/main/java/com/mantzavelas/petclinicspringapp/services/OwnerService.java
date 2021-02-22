package com.mantzavelas.petclinicspringapp.services;

import com.mantzavelas.petclinicspringapp.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
