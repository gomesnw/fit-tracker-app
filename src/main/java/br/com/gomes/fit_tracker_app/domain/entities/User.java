package br.com.gomes.fit_tracker_app.domain.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Builder

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
@AllArgsConstructor

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 254)
    private String email;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    @Column(nullable = false, length = 18)
    private String password;

    @Column(nullable = false, length = 11)
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
    private List<Workout> workouts = new ArrayList<>();

    public void addWorkout(Workout workout) {
        workouts.add(workout);
        workout.setUser(this);
    }

    public void removeWorkout(Workout workout) {
        workouts.remove(workout);
        workout.setUser(null);
    }

}