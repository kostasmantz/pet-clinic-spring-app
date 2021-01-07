package com.mantzavelas.petclinicspringapp.bootstrap;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.model.Vet;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import com.mantzavelas.petclinicspringapp.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

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
