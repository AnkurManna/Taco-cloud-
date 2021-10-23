package sia.taco.data;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import sia.taco.models.Order;
import sia.taco.models.Taco;

@Repository
public class JdbcOrderRepository implements OrderRepository{

	private SimpleJdbcInsert orderInserter;
	 private SimpleJdbcInsert orderTacoInserter;
	 private ObjectMapper objectMapper;
	 private List<String> modifiableColumn;
	 @Autowired
	 public JdbcOrderRepository(JdbcTemplate jdbc) {
	 this.orderInserter = new SimpleJdbcInsert(jdbc)
	 .withTableName("Taco_Order")
	 .usingGeneratedKeyColumns("id");
	 this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
	 .withTableName("Taco_Order_Tacos");
	 this.objectMapper = new ObjectMapper();
	 
	 modifiableColumn = Arrays.asList("name","street" ,"city","state","zip");
	 
	 }
	 
	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		 long orderId = saveOrderDetails(order);
		 order.setId(orderId);
		 List<Taco> tacos = order.getTacos();
	
		 for (Taco taco : tacos) {
		 saveTacoToOrder(taco, orderId);
		 }
		 return order;
	}
	
	@SuppressWarnings("null")
	private long saveOrderDetails(Order order) {
		 @SuppressWarnings("unchecked")
		 Map<String, Object> values =
		 objectMapper.convertValue(order, Map.class);
		 values.put("placedAt", order.getPlacedAt());
		 Map<String,Object> modifiedValues = new HashMap<String,Object>() ;
		 for(String key : values.keySet())
		 {
			 //if(Pattern.matches("cc[a-zA-Z0-9]*", key)|| key.equals("placedAt") || key.equals("id"))
			 if(!modifiableColumn.contains(key))
			 {
				 modifiedValues.put(key, values.get(key));
			 }
			 else
			 {
				 modifiedValues.put("delivery"+key, values.get(key));
			 }
		 }
		 System.out.print(modifiedValues.toString());
		 long orderId =
		 orderInserter
		 .executeAndReturnKey(modifiedValues)
		 .longValue();
		 return orderId;
		 }
	
		 private void saveTacoToOrder(Taco taco, long orderId) {
		 Map<String, Object> values = new HashMap<>();
		 values.put("tacoOrder", orderId);
		 values.put("taco", taco.getId());
		 orderTacoInserter.execute(values);
		 }
	
}
