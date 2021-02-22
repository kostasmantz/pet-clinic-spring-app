package com.mantzavelas.petclinicspringapp.controllers;

import com.mantzavelas.petclinicspringapp.model.Owner;
import com.mantzavelas.petclinicspringapp.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    public static final long OWNER_ID = 1L;
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(OWNER_ID).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void findOwners() {
        try {
            mockMvc.perform(get("/owners/find"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/findOwners"))
                    .andExpect(model().attributeExists("owner"));
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void findOwnerById() {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(OWNER_ID).firstName("John").lastName("Doe").build());


        try {
            mockMvc.perform(get("/owners/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/ownerDetails"))
                    .andExpect(model().attribute("owner", hasProperty("id", is(OWNER_ID))));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    @Test
    void processFindOwnerFormWithMultipleOwnersFound_ShouldReturnOwnerListView() {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(new ArrayList<>(owners));

        try {
            mockMvc.perform(get("/owners"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/ownersList"))
                    .andExpect(model().attribute("selections", hasSize(2)));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void processFindOwnerFormWithOneOwnerFound_ShouldReturnOwnerDetails() {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Collections.singletonList(Owner.builder().id(OWNER_ID).build()));

        try {
            mockMvc.perform(get("/owners"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/owners/1"));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void initCreationForm() {
        try {
            mockMvc.perform(get("/owners/new"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/ownerForm"))
                    .andExpect(model().attributeExists("owner"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verifyNoInteractions(ownerService);
    }

    @Test
    void processCreationForm() {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(OWNER_ID).build());

        try {
            mockMvc.perform(post("/owners/new"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/owners/1"))
                    .andExpect(model().attributeExists("owner"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(ownerService, times(1)).save(any());
    }

    @Test
    void initUpdateOwnerForm() {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(OWNER_ID).build());

        try {
            mockMvc.perform(get("/owners/1/edit"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("owners/ownerForm"))
                    .andExpect(model().attributeExists("owner"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void postUpdateOwnerForm() {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(OWNER_ID).build());

        try {
            mockMvc.perform(post("/owners/1/edit"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/owners/1"))
                    .andExpect(model().attributeExists("owner"));
        } catch (Exception e) {
            fail(e.getMessage());
        }

        verify(ownerService, times(1)).save(any());
    }
}