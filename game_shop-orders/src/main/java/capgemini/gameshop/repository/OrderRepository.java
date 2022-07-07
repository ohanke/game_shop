package capgemini.gameshop.repository;

import capgemini.gameshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Order entities. Access to database via JpaRepository.
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
}
