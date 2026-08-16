package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant createdAt
){
    public UserResponseDTO(User entity) {
        this(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt());
    }
}
