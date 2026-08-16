package br.com.gomes.fit_tracker_app.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Nome é um campo obrigatório.")
        @Size(min = 2, max = 120, message = "O nome deve ter entre 2 e 100 caracteres.")
        String name,

        @NotBlank(message = "Nome de usuário é um campo obrigatório.")
        @Size(min = 5, max = 30, message = "O nome de usuário deve ter entre 5 e 30 caracteres.")
        String username,

        @NotBlank(message = "E-mail é um campo obrigatório.")
        @Email(message = "Formato de e-mail inválido.")
        @Size(max = 254, message = "O e-mail não pode exceder 254 caracteres.")
        String email,

        @Size(min = 10, max = 11)
        @NotBlank(message = "Telefone é um campo obrigatório.")
        @Pattern(
                regexp = "^[1-9]{2}(?:[2-5]|9[1-9])\\d{7}$",
                message = "Telefone inválido. Envie DDD + Número (10 ou 11 dígitos, apenas números).")
        String phone,

        @NotBlank(message = "Senha é um campo obrigatório.")
        String password) {
}
