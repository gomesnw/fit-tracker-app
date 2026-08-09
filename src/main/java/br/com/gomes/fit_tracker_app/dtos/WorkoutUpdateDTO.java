package br.com.gomes.fit_tracker_app.dtos;

import jakarta.validation.constraints.NotBlank;

public record WorkoutUpdateDTO(@NotBlank String name,
                               String notes
) {
}
