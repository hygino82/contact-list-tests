package dev.hygino.services;

import java.util.List;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;

public interface ContactService {

    public ContactDTO insert(ContactInsertDTO dto);

	public List<ContactDTO> findAll();

	public ContactDTO findById(Long id);
    
}
