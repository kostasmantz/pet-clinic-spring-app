package com.mantzavelas.petclinicspringapp.bootstrap;

import com.mantzavelas.petclinicspringapp.model.*;
import com.mantzavelas.petclinicspringapp.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
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

        Owner dave = Owner.builder()
                .firstName("Dave")
                .lastName("Johnson")
                .address("Lampraki 123")
                .city("Thessaloniki")
                .telephone("1234567890")
                .pets(new HashSet<>())
                .build();

        Pet davesDog = new Pet();
        davesDog.setName("Stark");
        davesDog.setPetType(savedDogPetType);
        davesDog.setBirthDate(LocalDate.now());
        davesDog.setOwner(dave);

        dave.getPets().add(davesDog);

        Owner john = Owner.builder()
                .firstName("John")
                .lastName("Doe")
                .address("Patision 23")
                .city("Athens")
                .telephone("3213728937")
                .pets(new HashSet<>())
                .build();

        Pet johnsCat = new Pet();
        johnsCat.setName("Twinkie");
        johnsCat.setPetType(savedCatPetType);
        johnsCat.setBirthDate(LocalDate.now());
        johnsCat.setOwner(john);

        john.getPets().add(johnsCat);

        ownerService.save(dave);
        ownerService.save(john);

        Visit johnsCatVisit = new Visit();
        johnsCatVisit.setPet(johnsCat);
        johnsCatVisit.setDate(LocalDate.now());
        johnsCatVisit.setDescription("All good, minor worms in stomach");

        visitService.save(johnsCatVisit);

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
