package capgemini.gameshop.microservice.user.repository;

import capgemini.gameshop.microservice.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User entities. Access to database via JpaRepository.
 *
 * findUserById returns an Optional of User for the given id
 */

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
