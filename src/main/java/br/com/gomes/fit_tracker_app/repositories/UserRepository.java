package br.com.gomes.fit_tracker_app.repositories;

import br.com.gomes.fit_tracker_app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById (User user);
    Optional<User> findByEmail(String email);
}
