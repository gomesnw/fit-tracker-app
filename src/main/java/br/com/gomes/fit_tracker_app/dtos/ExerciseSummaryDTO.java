package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.enums.EquipmentType;
import br.com.gomes.fit_tracker_app.domain.enums.ExerciseCategory;

public record ExerciseSummaryDTO(Long id,
                                 String name,
                                 ExerciseCategory category,
                                 EquipmentType equipment
) {
}
