package dev.hygino.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactInsertDTO(
                @NotBlank(message = "O nome deve ser informado") String name,
                @Email(message = "O email deve ser informado") String email,
                @NotBlank(message = "O endereço deve ser informado") String address,
                @NotBlank(message = "A cidade deve ser informada") String city) {
}
