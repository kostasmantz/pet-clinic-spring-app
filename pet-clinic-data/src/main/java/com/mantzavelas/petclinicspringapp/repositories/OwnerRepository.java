package com.mantzavelas.petclinicspringapp.repositories;

import com.mantzavelas.petclinicspringapp.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
