package com.mantzavelas.petclinicspringapp.services.map;

import com.mantzavelas.petclinicspringapp.model.Speciality;
import com.mantzavelas.petclinicspringapp.model.Vet;
import com.mantzavelas.petclinicspringapp.services.SpecialityService;
import com.mantzavelas.petclinicspringapp.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long vetId) {
        super.deleteById(vetId);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public Vet save(Vet vet) {
        if (vet.getSpecialities().size() > 0) {
            vet.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    Speciality savedSpeciality = specialityService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }

        return super.save(vet);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
