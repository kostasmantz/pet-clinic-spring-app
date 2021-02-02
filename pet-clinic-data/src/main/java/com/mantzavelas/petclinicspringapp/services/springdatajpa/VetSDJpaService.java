package com.mantzavelas.petclinicspringapp.services.springdatajpa;

import com.mantzavelas.petclinicspringapp.model.Vet;
import com.mantzavelas.petclinicspringapp.repositories.VetRepository;
import com.mantzavelas.petclinicspringapp.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().iterator().forEachRemaining(vets::add);

        return vets;
    }

    @Override
    public Vet findById(Long vetId) {
        return vetRepository.findById(vetId).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long vetId) {
        vetRepository.deleteById(vetId);
    }
}
