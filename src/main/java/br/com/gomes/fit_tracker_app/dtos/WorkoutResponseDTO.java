package br.com.gomes.fit_tracker_app.dtos;

import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import br.com.gomes.fit_tracker_app.domain.enums.WorkoutStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.util.List;

public record WorkoutResponseDTO(
        Long id,
        UserSummaryDTO user,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant startedAt,
        String name,
        String notes,
        WorkoutStatus status,
        List<WorkoutExerciseResponseDTO> workoutExercises,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Sao_Paulo")
        Instant createdAt)
{
    public WorkoutResponseDTO (Workout entity){
        this(entity.getId(), new UserSummaryDTO(entity.getUser()), entity.getStartedAt(),
                entity.getName(), entity.getNotes(), entity.getStatus(),
                entity.getWorkoutExercises().stream()
                        .map(WorkoutExerciseResponseDTO::new)
                        .toList(),
                entity.getCreatedAt());
    }
}
