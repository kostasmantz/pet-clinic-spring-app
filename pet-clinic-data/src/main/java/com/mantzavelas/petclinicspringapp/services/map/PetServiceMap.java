package com.mantzavelas.petclinicspringapp.services.map;

import com.mantzavelas.petclinicspringapp.model.Pet;
import com.mantzavelas.petclinicspringapp.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "springdatamap"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long petId) {
        super.deleteById(petId);
    }

    @Override
    public Pet save(Pet pet) {
        return super.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        super.delete(pet);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
