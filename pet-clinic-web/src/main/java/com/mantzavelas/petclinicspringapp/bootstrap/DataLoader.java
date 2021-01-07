package com.mantzavelas.petclinicspringapp.bootstrap;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.model.Vet;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import com.mantzavelas.petclinicspringapp.services.PetService;
import com.mantzavelas.petclinicspringapp.services.VetService;
import com.mantzavelas.petclinicspringapp.services.map.OwnerServiceMap;
import com.mantzavelas.petclinicspringapp.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner = new Owner(1L, "Dave", "Johnson");
        Owner owner2 = new Owner(2L, "John", "Doe");

        ownerService.save(owner);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet = new Vet(1L, "Samuel", "Jackson");
        Vet vet2 = new Vet(2L, "Sylvester", "Aloneer");

        vetService.save(vet);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
