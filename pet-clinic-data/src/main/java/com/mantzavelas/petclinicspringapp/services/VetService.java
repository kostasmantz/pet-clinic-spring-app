package com.mantzavelas.petclinicspringapp.services;

import com.mantzavelas.petclinicspringapp.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
