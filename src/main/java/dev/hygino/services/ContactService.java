package dev.hygino.services;

import java.util.List;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;
import jakarta.validation.Valid;

public interface ContactService {

	public ContactDTO insert(@Valid ContactInsertDTO dto);

	public List<ContactDTO> findAll();

	public ContactDTO findById(Long id);

	public ContactDTO update(Long id, @Valid ContactInsertDTO dto);

}
