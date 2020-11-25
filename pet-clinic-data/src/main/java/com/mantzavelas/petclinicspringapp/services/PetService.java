package com.mantzavelas.petclinicspringapp.services;

import com.mantzavelas.petclinicspringapp.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
