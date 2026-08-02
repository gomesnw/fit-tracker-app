package br.com.gomes.fit_tracker_app.dtos;


import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import br.com.gomes.fit_tracker_app.domain.enums.EquipmentType;
import br.com.gomes.fit_tracker_app.domain.enums.ExerciseCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ExerciseUpdateResponseDTO(
        Long id,
        String name,
        ExerciseCategory category,
        EquipmentType type,
        String notes,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant updatedAt)
{
    public ExerciseUpdateResponseDTO (Exercise entity){
        this(entity.getId(), entity.getName(), entity.getExerciseCategory(), entity.getEquipmentType(), entity.getNotes(),
                entity.getUpdatedAt());
    }
}

