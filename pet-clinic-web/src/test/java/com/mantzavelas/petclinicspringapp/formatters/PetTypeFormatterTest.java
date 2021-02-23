package com.mantzavelas.petclinicspringapp.formatters;

import com.mantzavelas.petclinicspringapp.model.PetType;
import com.mantzavelas.petclinicspringapp.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PetTypeFormatterTest {

    private static final String VALID_PET_TYPE_NAME = "Dog";
    private static final String INVALID_PET_TYPE_NAME = "Doggo";
    private final PetType PET_TYPE = PetType.builder().id(1L).name(VALID_PET_TYPE_NAME).build();

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetTypeFormatter formatter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void parseWithValidName_ShouldReturnPetType() {
        when(petTypeService.findByName(anyString())).thenReturn(PET_TYPE);

        try {
            PetType retrievedPetType = formatter.parse(VALID_PET_TYPE_NAME, null);

            assertEquals(VALID_PET_TYPE_NAME, retrievedPetType.getName());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test()
    void parseWithInValidName_ShouldThrowParseException() throws ParseException {
        when(petTypeService.findByName(anyString())).thenReturn(null);

        assertThrows(ParseException.class, () -> formatter.parse(INVALID_PET_TYPE_NAME, null));
    }

    @Test
    void print() {
        assertEquals(VALID_PET_TYPE_NAME, formatter.print(PET_TYPE, null));
    }
}