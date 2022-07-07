package capgemini.gameshop.repository;

import capgemini.gameshop.model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Adress entities. Access to database via JpaRepository.
 */
public interface AdressRepository extends JpaRepository<Adress, Long> {

    Optional<Adress> findByZip(String zip);
}
