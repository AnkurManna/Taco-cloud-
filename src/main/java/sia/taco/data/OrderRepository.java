package sia.taco.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import sia.taco.models.Order;
import sia.taco.models.User;

public interface OrderRepository extends CrudRepository<Order, Long>{

	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
	
}
