package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Adress;
import capgemini.gameshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Adress entities. Access to database via JpaRepository.
 */
public interface AdressRepository extends JpaRepository<Adress, Long> {

    Optional<Adress> findByZip(String zip);
}
