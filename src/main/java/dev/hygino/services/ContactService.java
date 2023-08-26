package dev.hygino.services;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;

public interface ContactService {

    public ContactDTO insert(ContactInsertDTO dto);
    
}
