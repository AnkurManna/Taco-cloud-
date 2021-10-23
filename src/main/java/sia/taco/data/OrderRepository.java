package sia.taco.data;

import sia.taco.models.Order;

public interface OrderRepository {
	
	Order save(Order order);
}
