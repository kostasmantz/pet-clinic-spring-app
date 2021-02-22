package com.mantzavelas.petclinicspringapp.services.springdatajpa;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.repositories.OwnerRepository;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();

        ownerRepository.findAll().iterator().forEachRemaining(owners::add);

        return owners;
    }

    @Override
    public Owner findById(Long ownerId) {
        return ownerRepository.findById(ownerId).orElse(null);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long ownerId) {
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(getSqlLikeQuote(lastName));
    }

    private String getSqlLikeQuote(String property) {
        return "%" + property + "%";
    }
}
