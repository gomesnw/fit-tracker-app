package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import jakarta.validation.constraints.NotBlank;

public record ExerciseInsertDTO(@NotBlank String name,
                                @NotBlank String muscleGroup,
                                String notes
                                ) {
    public Exercise toEntity(){
        return new Exercise(null, name, muscleGroup, notes, null, null);
    }
}
