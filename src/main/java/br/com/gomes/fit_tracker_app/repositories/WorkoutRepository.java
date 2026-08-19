package br.com.gomes.fit_tracker_app.repositories;

import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Workout findById(Workout workout);

    List<Workout> findAllByUserId(Long userId);

    Optional<Workout> findByIdAndUserId(Long id, Long userId);
}
