package br.com.gomes.fit_tracker_app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;


public record WorkoutInsertDTO
        (@NotBlank String name,
         @Size(min = 2) List <WorkoutExerciseInsertDTO> exercises,
         String notes)
{}
