package dev.hygino.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import dev.hygino.entities.Contact;

public record ContactDTO(
		Long id, 
		String name, 
		String email, 
		String address, 
		String city,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT") Instant createAt,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT") Instant updateAt) {

	public ContactDTO(Contact entity) {
		this(entity.getId(), entity.getName(), entity.getEmail(), entity.getAddress(), entity.getCity(),
				entity.getCreateAt(), entity.getUpdateAt());
	}
}
