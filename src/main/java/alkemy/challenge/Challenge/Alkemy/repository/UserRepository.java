package alkemy.challenge.Challenge.Alkemy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import alkemy.challenge.Challenge.Alkemy.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUserName(String username);
}
