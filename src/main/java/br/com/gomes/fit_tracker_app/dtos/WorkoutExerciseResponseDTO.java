package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.WorkoutExercise;

public record WorkoutExerciseResponseDTO (Long id,
                                          Integer orderIndex,
                                          String notes,
                                          ExerciseResponseDTO exercise){

    public WorkoutExerciseResponseDTO(WorkoutExercise entity) {
        this(
                entity.getId(),
                entity.getOrderIndex(),
                entity.getNotes(),
                new ExerciseResponseDTO(entity.getExercise())
        );
    }
}
