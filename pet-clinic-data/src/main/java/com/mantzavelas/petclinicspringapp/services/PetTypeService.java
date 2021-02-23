package com.mantzavelas.petclinicspringapp.services;

import com.mantzavelas.petclinicspringapp.model.PetType;

public interface PetTypeService extends CrudService<PetType, Long> {

    PetType findByName(String name);
}
