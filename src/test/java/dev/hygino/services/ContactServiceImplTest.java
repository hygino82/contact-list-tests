package dev.hygino.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	private Contact existingContact;
	private Long existingId;
	private Long nonExistingId;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		existingContact = new Contact(1L, "Dilma", "dilma@opressora.net", "Linha Borges", "Coronel Vivida");
		existingId = 1L;
		nonExistingId = 100L;
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

	@Test
	void testFindById_ExistingId() {
		// Simulando o comportamento do repository quando um contato existe
		when(contactRepository.findById(existingId)).thenReturn(Optional.of(existingContact));

		ContactDTO result = contactService.findById(existingId);

		assertNotNull(result);
		// Verifique se o método findById do repositório foi chamado com o ID correto
		verify(contactRepository).findById(existingId);
	}

	@Test
	void testFindById_NonExistingId() {
		// Simulando o comportamento do repository quando um contato não existe
		when(contactRepository.findById(nonExistingId)).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> {
			contactService.findById(nonExistingId);
		});
		// Verifique se o método findById do repositório foi chamado com o ID correto
		verify(contactRepository).findById(nonExistingId);
	}

	@Test
	void testFindAll() {
		// Simulando uma lista de contatos do repository
		List<Contact> mockContactList = new ArrayList<>();
		mockContactList.add(new Contact(1L, "Nome 1", "Email 1", "Endereço 1", "Cidade 1"));
		mockContactList.add(new Contact(2L, "Nome 2", "Email 2", "Endereço 2", "Cidade 2"));

		when(contactRepository.findAll()).thenReturn(mockContactList);

		List<ContactDTO> result = contactService.findAll();

		assertNotNull(result);
		assertEquals(2, result.size());

		// Verifique se o método findAll do repositório foi chamado
		verify(contactRepository).findAll();
	}
}