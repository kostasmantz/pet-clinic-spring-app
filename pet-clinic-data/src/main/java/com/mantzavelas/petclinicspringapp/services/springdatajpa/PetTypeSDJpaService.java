package com.mantzavelas.petclinicspringapp.services.springdatajpa;

import com.mantzavelas.petclinicspringapp.model.PetType;
import com.mantzavelas.petclinicspringapp.repositories.PetTypeRepository;
import com.mantzavelas.petclinicspringapp.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }


    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().iterator().forEachRemaining(petTypes::add);

        return petTypes;
    }

    @Override
    public PetType findById(Long petTypeId) {
        return petTypeRepository.findById(petTypeId).orElse(null);
    }

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(Long petTypeId) {
        petTypeRepository.deleteById(petTypeId);
    }
}
