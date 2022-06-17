package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Cart entities. Access to database via JpaRepository.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {
}
