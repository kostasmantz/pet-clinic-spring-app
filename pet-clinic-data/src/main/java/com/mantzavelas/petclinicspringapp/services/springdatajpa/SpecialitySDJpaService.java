package com.mantzavelas.petclinicspringapp.services.springdatajpa;

import com.mantzavelas.petclinicspringapp.model.Speciality;
import com.mantzavelas.petclinicspringapp.repositories.SpecialityRepository;
import com.mantzavelas.petclinicspringapp.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialties = new HashSet<>();
        specialityRepository.findAll().iterator().forEachRemaining(specialties::add);

        return specialties;
    }

    @Override
    public Speciality findById(Long specialtyId) {
        return specialityRepository.findById(specialtyId).orElse(null);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepository.delete(speciality);
    }

    @Override
    public void deleteById(Long specialityId) {
        specialityRepository.deleteById(specialityId);
    }
}
