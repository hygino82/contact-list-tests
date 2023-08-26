package dev.hygino.services;

import static org.mockito.Mockito.*;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;
import dev.hygino.entities.Contact;
import dev.hygino.repositories.ContactRepository;

public class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertContact() {
        // Given
        ContactInsertDTO dto = new ContactInsertDTO("Dilma", "dilma@opressora.net", "Linha Borges", "Coronel Vivida");
        // Set properties on the DTO as needed

        // Create a sample entity that you expect to be returned by the repository
        Contact expectedEntity = new Contact(dto);
        expectedEntity.setCreateAt(Instant.now());

        when(contactRepository.save(any(Contact.class))).thenReturn(expectedEntity);

        // When
        ContactDTO resultDto = contactService.insert(dto);

        // Then
        // Perform assertions on the resultDto or other expected behavior
        // For example: assertEquals(expectedDto, resultDto);
        // You can also verify interactions with the mock repository using Mockito's
        // verify
        verify(contactRepository, times(1)).save(any(Contact.class));
        Assertions.assertEquals(dto.name(), resultDto.name());
        Assertions.assertEquals(dto.address(), resultDto.address());
        Assertions.assertEquals(dto.email(), resultDto.email());
        Assertions.assertEquals(dto.city(), resultDto.city());
        
    }
}