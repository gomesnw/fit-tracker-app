package br.com.gomes.fit_tracker_app.dtos;

public record WorkoutExerciseInsertDTO(Long exerciseId,
                                       Integer orderIndex,
                                       String notes)
{
}
