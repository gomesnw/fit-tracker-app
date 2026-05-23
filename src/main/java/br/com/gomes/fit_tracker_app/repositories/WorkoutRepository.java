package br.com.gomes.fit_tracker_app.repositories;

import br.com.gomes.fit_tracker_app.domain.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {
     Workout findById (Workout workout);
}
