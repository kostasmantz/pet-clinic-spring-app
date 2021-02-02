package com.mantzavelas.petclinicspringapp.repositories;

import com.mantzavelas.petclinicspringapp.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
