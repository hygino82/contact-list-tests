package dev.hygino.dto;

import java.time.Instant;

import dev.hygino.entities.Contact;

public record ContactDTO(
        Long id,
        String name,
        String email,
        String address,
        String city,
        Instant createAt,
        Instant updateAt) {

    public ContactDTO(Contact entity) {
        this(
                entity.getId(),
                entity.getName(), 
                entity.getEmail(),
                entity.getAddress(),
                entity.getCity(),
                entity.getCreateAt(), 
                entity.getUpdateAt()
        );
    }
}
