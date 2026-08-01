package br.com.gomes.fit_tracker_app.domain.entities;

import br.com.gomes.fit_tracker_app.domain.enums.WorkoutStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Table(name="workouts")
@Entity
public class Workout implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "started_at")
    private Instant startedAt;

    @UpdateTimestamp
    @Column(name = "finished_at")
    private Instant finishedAt;

    private String name;
    private WorkoutStatus status;
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<WorkoutExercise> workoutExercises = new ArrayList<>();

    public void addWorkoutExercise(WorkoutExercise workoutExercise) {
        workoutExercises.add(workoutExercise);
        workoutExercise.setWorkout(this);
    }

    public void removeWorkoutExercise(WorkoutExercise workoutExercise) {
        workoutExercises.remove(workoutExercise);
        workoutExercise.setWorkout(null);
    }

}
