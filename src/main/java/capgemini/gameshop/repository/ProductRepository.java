package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for Product entities. Access to database via JpaRepository.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findAllByIdLessThan (Long id);
}
