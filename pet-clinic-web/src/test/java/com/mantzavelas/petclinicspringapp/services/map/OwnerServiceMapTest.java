package com.mantzavelas.petclinicspringapp.services.map;

import com.mantzavelas.petclinicspringapp.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerServiceMapTest {

    public static final long OWNER_ID = 1L;
    public static final String OWNER_LAST_NAME = "Smith";
    OwnerServiceMap ownerServiceMap;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(OWNER_ID);

        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void saveExistingId() {
        long ID = 1L;
        Owner savedOwner = ownerServiceMap.save(Owner.builder().id(ID).build());

        assertEquals(ID, savedOwner.getId());
    }

    @Test
    void saveWithoutId_ShouldAddTheNextAvailableId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(OWNER_ID));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner johnSmith = ownerServiceMap.findByLastName(OWNER_LAST_NAME);

        assertNotNull(johnSmith);
        assertEquals(OWNER_LAST_NAME, johnSmith.getLastName());
    }
}