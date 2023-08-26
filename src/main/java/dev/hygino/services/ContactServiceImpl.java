package dev.hygino.services;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;
import dev.hygino.entities.Contact;
import dev.hygino.repositories.ContactRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;

	public ContactServiceImpl(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Override
	public ContactDTO insert(ContactInsertDTO dto) {
		Contact entity = new Contact(dto);
		entity.setCreateAt(Instant.now());
		entity = contactRepository.save(entity);

		return new ContactDTO(entity);
	}

	@Override
	public List<ContactDTO> findAll() {
		List<Contact> entityList = contactRepository.findAll();
		return entityList.stream().map(contact -> new ContactDTO(contact)).collect(Collectors.toList());
	}
}
