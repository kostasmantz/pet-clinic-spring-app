package com.mantzavelas.petclinicspringapp.bootstrap;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.model.Pet;
import com.mantzavelas.petclinicspringapp.model.PetType;
import com.mantzavelas.petclinicspringapp.model.Vet;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import com.mantzavelas.petclinicspringapp.services.PetTypeService;
import com.mantzavelas.petclinicspringapp.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

        PetType savedDogPetType = petTypeService.save(dog);
        PetType savedCatPetType = petTypeService.save(cat);
        System.out.println("Loaded Pet types...");

        Owner dave = new Owner("Dave", "Johnson");
        dave.setAddress("Lampraki 123");
        dave.setCity("Thessaloniki");
        dave.setTelephone("1234567890");

        Pet davesDog = new Pet();
        davesDog.setPetType(savedDogPetType);
        davesDog.setBirthDate(LocalDate.now());
        davesDog.setOwner(dave);
        dave.getPets().add(davesDog);

        Owner john = new Owner("John", "Doe");
        john.setAddress("Patision 23");
        john.setCity("Athens");
        john.setTelephone("3213728937");

        Pet johnsCat = new Pet();
        johnsCat.setPetType(cat);
        johnsCat.setBirthDate(LocalDate.now());
        johnsCat.setOwner(john);
        john.getPets().add(johnsCat);

        ownerService.save(dave);
        ownerService.save(john);

        System.out.println("Loaded Owners...");

        Vet vet = new Vet("Samuel", "Jackson");
        Vet vet2 = new Vet("Sylvester", "Aloneer");

        vetService.save(vet);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
