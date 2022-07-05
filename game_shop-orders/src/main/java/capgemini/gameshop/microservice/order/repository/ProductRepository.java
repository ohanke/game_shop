package capgemini.gameshop.microservice.order.repository;

import capgemini.gameshop.microservice.order.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for Product entities. Access to database via JpaRepository.
 */

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdLessThan (Long id);
}
