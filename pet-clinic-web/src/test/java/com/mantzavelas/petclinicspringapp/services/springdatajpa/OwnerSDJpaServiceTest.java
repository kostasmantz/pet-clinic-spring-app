package com.mantzavelas.petclinicspringapp.services.springdatajpa;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String OWNER_LAST_NAME = "Smith";
    public static final long OWNER_ID = 1L;

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerService;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build();
    }

    @Test
    void findAll() {
        when(ownerRepository.findAll()).thenReturn(new HashSet<>(Collections.singletonList(returnOwner)));

        Set<Owner> owners = ownerService.findAll();

        assertEquals(1, owners.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerService.findById(OWNER_ID);

        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void findByIdForNoExistingId_ShouldReturnNull() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = ownerService.findById(OWNER_ID);

        assertNull(owner);
        verify(ownerRepository, times(1)).findById(any());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(Optional.of(returnOwner));

        Owner smith = ownerService.findByLastName(OWNER_LAST_NAME);

        assertNotNull(smith);
        assertEquals(OWNER_LAST_NAME, smith.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void findByLastNameWithOwnerNotFound_ShouldReturnNull() {
        when(ownerRepository.findByLastName(anyString())).thenReturn(Optional.empty());

        Owner smith = ownerService.findByLastName(OWNER_LAST_NAME);

        assertNull(smith);
        verify(ownerRepository, times(1)).findByLastName(any());
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        ownerService.save(Owner.builder().build());

        verify(ownerRepository, times(1)).save(any());
    }

    @Test
    void delete() {
        ownerService.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(returnOwner.getId());

        verify(ownerRepository).deleteById(any());
    }
}