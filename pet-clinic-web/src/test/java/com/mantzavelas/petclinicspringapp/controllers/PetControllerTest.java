package com.mantzavelas.petclinicspringapp.controllers;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.model.Pet;
import com.mantzavelas.petclinicspringapp.model.PetType;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import com.mantzavelas.petclinicspringapp.services.PetService;
import com.mantzavelas.petclinicspringapp.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    private static final long OWNER_ID = 1L;
    private static final Owner OWNER = Owner.builder().id(OWNER_ID).build();

    private static final Set<PetType> PET_TYPES = new HashSet<>(
            Arrays.asList(
                    PetType.builder().id(1L).name("Starfish").build(),
                    PetType.builder().id(2L).name("Chameleon").build()
            )
    );

    @Mock
    PetTypeService petTypeService;

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    PetController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void initCreationForm() {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPES);

        try {
            mockMvc.perform(get("/owners/1/pets/new"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("pet"))
                    .andExpect(view().name("pets/petForm"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void processCreationForm() {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPES);

        try {
            mockMvc.perform(post("/owners/1/pets/new"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("pet"))
                    .andExpect(redirectedUrl("/owners/1"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPES);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().name("Twinkie").build());

        try {
            mockMvc.perform(get("/owners/1/pets/2/edit"))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("owner"))
                    .andExpect(model().attributeExists("pet"))
                    .andExpect(view().name("pets/petForm"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void processUpdateForm() {
        when(ownerService.findById(anyLong())).thenReturn(OWNER);
        when(petTypeService.findAll()).thenReturn(PET_TYPES);

        try {
            mockMvc.perform(post("/owners/1/pets/2/edit"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/owners/1"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(petService).save(any());
    }
}