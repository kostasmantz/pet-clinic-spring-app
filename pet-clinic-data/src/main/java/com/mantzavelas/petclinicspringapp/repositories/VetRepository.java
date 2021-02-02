package com.mantzavelas.petclinicspringapp.repositories;

import com.mantzavelas.petclinicspringapp.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
