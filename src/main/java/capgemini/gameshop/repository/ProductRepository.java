package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Product entities. Access to database via JpaRepository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByIdLessThan (Long id);
}
