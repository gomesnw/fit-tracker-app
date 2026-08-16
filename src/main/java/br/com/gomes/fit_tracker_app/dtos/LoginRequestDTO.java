package br.com.gomes.fit_tracker_app.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(@NotBlank(message = "E-mail é um campo obrigatório.")
                              @Email(message = "Formato de e-mail inválido.")
                              @Size(max = 254, message = "O e-mail não pode exceder 254 caracteres.")
                              String email,
                              @NotBlank(message = "Senha é um campo obrigatório.")
                              String password) {
}
