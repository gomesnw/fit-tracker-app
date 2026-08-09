package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import br.com.gomes.fit_tracker_app.domain.enums.WorkoutStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record WorkoutUpdateResponseDTO(Long id,
                                       String name,
                                       List<WorkoutExerciseResponseDTO> workoutExercises,
                                       String notes,
                                       WorkoutStatus status,
                                       @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
                                       Instant updatedAt
) {
    public WorkoutUpdateResponseDTO (Workout entity) {
        this(entity.getId(), entity.getName(), entity.getWorkoutExercises().stream()
                        .map(WorkoutExerciseResponseDTO::new).toList(),
                entity.getNotes(), entity.getStatus(), entity.getUpdatedAt());
    }
}
