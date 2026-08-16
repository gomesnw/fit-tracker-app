package br.com.gomes.fit_tracker_app.repositories;

import br.com.gomes.fit_tracker_app.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String role);
}
