package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import jakarta.validation.constraints.NotBlank;

public record WorkoutInsertDTO
        (@NotBlank String name,
         String notes)
{
    public Workout toEntity(){
        return new Workout(null, null, null, null, name, null, notes, null, null);
    }
}
