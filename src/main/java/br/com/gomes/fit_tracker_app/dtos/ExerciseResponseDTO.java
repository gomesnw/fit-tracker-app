package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ExerciseResponseDTO(
        Long id,
        String name,
        String muscleGroup,
        String notes,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant createdAt)
{
    public ExerciseResponseDTO (Exercise entity){
        this(entity.getId(), entity.getName(), entity.getMuscleGroup(), entity.getNotes(),
               entity.getCreatedAt());
    }
}
