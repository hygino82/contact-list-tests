package dev.hygino.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;
import dev.hygino.entities.Contact;
import dev.hygino.repositories.ContactRepository;

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

	@Override
	public ContactDTO findById(Long id) {
		Optional<Contact> optional = this.contactRepository.findById(id);
		if (optional.isPresent()) {
			return new ContactDTO(optional.get());
		}
		throw new IllegalArgumentException("Não existe contato com o id " + id);
	}
}
