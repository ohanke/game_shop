package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Adress entities. Access to database via JpaRepository.
 */
public interface AdressRepository extends JpaRepository<Adress, Long> {
}
