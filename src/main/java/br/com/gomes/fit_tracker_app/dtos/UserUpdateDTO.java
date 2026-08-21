package br.com.gomes.fit_tracker_app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @NotBlank(message = "Nome é um campo obrigatório.")
        @Size(min = 2, max = 120, message = "O nome deve ter entre 2 e 100 caracteres.")
        String name
) {
}
