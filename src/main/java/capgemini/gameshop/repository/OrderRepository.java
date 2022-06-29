package capgemini.gameshop.repository;

import capgemini.gameshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Order entities. Access to database via JpaRepository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
