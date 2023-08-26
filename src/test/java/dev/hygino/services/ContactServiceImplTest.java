package dev.hygino.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	public void testInsertContact_Success() {
		// Contato informado
		ContactInsertDTO dto = new ContactInsertDTO("Dilma", "dilma@opressora.net", "Linha Borges", "Coronel Vivida");
		// Set properties on the DTO as needed

		// Criação da entidade que vai receber os dados do dto
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
		Assertions.assertEquals("Dilma", resultDto.name());
		Assertions.assertEquals("dilma@opressora.net", resultDto.email());
		Assertions.assertEquals("Linha Borges", resultDto.address());
		Assertions.assertEquals("Coronel Vivida", resultDto.city());

	}
}