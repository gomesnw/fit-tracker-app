package br.com.gomes.fit_tracker_app.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Table(name="workout_exercises")
@Entity
@AllArgsConstructor
public class WorkoutExercise implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "order_index")
    private Integer orderIndex;

    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Instant updatedAt;

    @NotNull
    @ManyToOne
    @JoinColumn(name="exercise_id", nullable = false)
    private Exercise exercise;

    @NotNull
    @ManyToOne
    @JoinColumn(name="workout_id", nullable = false)
    private Workout workout;

    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<WorkoutSet> sets = new ArrayList<>();

    public void addSet(WorkoutSet set) {
        sets.add(set);
        set.setWorkoutExercise(this);
    }

    public void removeSet(WorkoutSet set) {
        sets.remove(set);
        set.setWorkoutExercise(null);
    }

}
