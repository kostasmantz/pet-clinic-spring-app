package com.mantzavelas.petclinicspringapp.repositories;

import com.mantzavelas.petclinicspringapp.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
