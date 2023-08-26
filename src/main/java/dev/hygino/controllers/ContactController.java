package dev.hygino.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hygino.dto.ContactDTO;
import dev.hygino.dto.ContactInsertDTO;
import dev.hygino.services.ContactService;

@RestController
@RequestMapping("api/v1/contact")
public class ContactController {

	private final ContactService service;

	public ContactController(ContactService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ContactDTO> insert(@RequestBody ContactInsertDTO dto) {
		ContactDTO result = this.service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}

	@GetMapping
	public ResponseEntity<List<ContactDTO>> findAll() {
		List<ContactDTO> list = this.service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ContactDTO> findById(@PathVariable("id") Long id) {
		ContactDTO result = this.service.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
