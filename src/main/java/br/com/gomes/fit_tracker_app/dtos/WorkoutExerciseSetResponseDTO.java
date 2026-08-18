package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.WorkoutSet;

public record WorkoutExerciseSetResponseDTO (Long id,
                                             Integer orderIndex,
                                             Integer reps,
                                             Double weight,
                                             Integer repsInReserve,
                                             String notes){

    public WorkoutExerciseSetResponseDTO(WorkoutSet entity) {
        this(
                entity.getId(),
                entity.getOrderIndex(),
                entity.getReps(),
                entity.getWeight(),
                entity.getRepsInReserve(),
                entity.getNotes()
        );
    }
}
