package com.mantzavelas.petclinicspringapp.bootstrap;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.model.PetType;
import com.mantzavelas.petclinicspringapp.model.Vet;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import com.mantzavelas.petclinicspringapp.services.PetTypeService;
import com.mantzavelas.petclinicspringapp.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        petTypeService.save(dog);
        petTypeService.save(cat);
        System.out.println("Loaded Pet types...");

        Owner owner = new Owner("Dave", "Johnson");
        Owner owner2 = new Owner("John", "Doe");

        ownerService.save(owner);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet = new Vet("Samuel", "Jackson");
        Vet vet2 = new Vet("Sylvester", "Aloneer");

        vetService.save(vet);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
