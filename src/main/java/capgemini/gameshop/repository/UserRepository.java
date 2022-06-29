package capgemini.gameshop.repository;

import capgemini.gameshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User entities. Access to database via JpaRepository.
 *
 * findUserById returns an Optional of User for the given id
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
