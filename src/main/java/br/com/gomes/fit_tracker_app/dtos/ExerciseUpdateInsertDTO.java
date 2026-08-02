package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.enums.EquipmentType;
import br.com.gomes.fit_tracker_app.domain.enums.ExerciseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ExerciseUpdateInsertDTO(@NotBlank @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
                                      String name,
                                      ExerciseCategory category,
                                      EquipmentType type,
                                      @Size(max = 300, message = "As notas podem ter até 300 caracteres.")
                                      String notes
) {
}
