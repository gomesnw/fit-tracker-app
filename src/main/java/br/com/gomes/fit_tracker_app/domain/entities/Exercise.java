package br.com.gomes.fit_tracker_app.domain.entities;

import br.com.gomes.fit_tracker_app.domain.enums.EquipmentType;
import br.com.gomes.fit_tracker_app.domain.enums.ExerciseCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder

@Table(name = "exercises")
@Entity
public class Exercise implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="equipment_type")
    private EquipmentType equipmentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="exercise_category")
    private ExerciseCategory exerciseCategory;

    @Column(length = 500)
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
