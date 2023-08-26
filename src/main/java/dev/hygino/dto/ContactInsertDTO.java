package dev.hygino.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactInsertDTO(
                @NotBlank String name,
                @NotBlank String email,
                @Email String address,
                @NotBlank String city) {
}
