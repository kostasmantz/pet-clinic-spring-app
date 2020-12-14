package com.mantzavelas.petclinicspringapp.services;

import com.mantzavelas.petclinicspringapp.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
