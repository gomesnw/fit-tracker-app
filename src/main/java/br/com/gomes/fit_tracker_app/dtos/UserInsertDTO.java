package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(@NotBlank String name,
                            @NotBlank @Email String email,
                            String phone,
                            @NotBlank @Size(min = 6, max = 18) String password) {
    public User toEntity() {
        return new User(null, name, email, phone, password, null, null, null);
    }
}
