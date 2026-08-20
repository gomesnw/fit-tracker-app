package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.enums.EquipmentType;
import br.com.gomes.fit_tracker_app.domain.enums.ExerciseCategory;
import jakarta.validation.constraints.NotBlank;

public record ExerciseInsertDTO(@NotBlank String name,
                                ExerciseCategory category,
                                EquipmentType type,
                                String notes
                                ) {
}
