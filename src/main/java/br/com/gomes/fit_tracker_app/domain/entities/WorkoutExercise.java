package br.com.gomes.fit_tracker_app.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Table(name="workout_exercises")
@Entity
public class WorkoutExercise implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Integer orderIndex;
    private String notes;
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne
    @JoinColumn(name="exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name="workout_id")
    private Workout workout;
}
