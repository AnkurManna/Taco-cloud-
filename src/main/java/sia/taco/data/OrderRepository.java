package sia.taco.data;

import org.springframework.data.repository.CrudRepository;

import sia.taco.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	
}
