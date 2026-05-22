package br.com.gomes.fit_tracker_app.repositories;

import br.com.gomes.fit_tracker_app.domain.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise findById (Exercise exercise);
}
