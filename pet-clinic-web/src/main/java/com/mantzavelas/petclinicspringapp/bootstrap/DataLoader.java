package com.mantzavelas.petclinicspringapp.bootstrap;

import com.mantzavelas.petclinicspringapp.model.*;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import com.mantzavelas.petclinicspringapp.services.PetTypeService;
import com.mantzavelas.petclinicspringapp.services.SpecialityService;
import com.mantzavelas.petclinicspringapp.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int petTypesCount = petTypeService.findAll().size();

        if (petTypesCount == 0) {
            loadData();
        }
    }

    private void loadData() {
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

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Vet samuel = new Vet("Samuel", "Jackson");
        samuel.getSpecialities().add(savedDentistry);
        samuel.getSpecialities().add(savedRadiology);
        Vet sylvester = new Vet("Sylvester", "Aloneer");
        sylvester.getSpecialities().add(savedSurgery);

        vetService.save(samuel);
        vetService.save(sylvester);

        System.out.println("Loaded Vets...");
    }
}
