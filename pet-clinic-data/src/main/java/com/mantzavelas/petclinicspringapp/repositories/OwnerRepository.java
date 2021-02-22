package com.mantzavelas.petclinicspringapp.repositories;

import com.mantzavelas.petclinicspringapp.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
