package sia.taco.data;

import java.util.Date;
import java.util.Arrays;
import java.sql.Timestamp;
import java.sql.Types;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import sia.taco.models.Ingredient;
import sia.taco.models.Taco;

@Slf4j
@Repository
public class JdbcTacoRepository implements TacoRepository{

	private JdbcTemplate jdbc;
	private final IngredientRepository ingredientRepo;
	 public JdbcTacoRepository(JdbcTemplate jdbc,IngredientRepository ingredientRepo) {
	 this.jdbc = jdbc;
	 this.ingredientRepo = ingredientRepo;
	 }
	 @Override
	 public Taco save(Taco taco) {
	 long tacoId = saveTacoInfo(taco);
	 taco.setId(tacoId);
	 Iterable<Ingredient> ingredients = ingredientRepo.findAll();
	 /*for(Ingredient ingredient : ingredients)
	 {
		 System.out.println("Names " + ingredient.getName());
	 }*/

	 for (String ingredientName : taco.getIngredients()) {
		 //System.out.println("Names " + ingredientName);
		 
		 for(Ingredient ingredient : ingredients)
		 {
			// System.out.println("Names " + ingredient.getName());
			 if(ingredient.getId().equals(ingredientName))
				 saveIngredientToTaco(ingredient, tacoId);
		 }
	 
	 }
	 
	 return taco;
	 }
	 private long saveTacoInfo(Taco taco) {
	 taco.setCreatedAt(new Date());
	 
	 PreparedStatementCreatorFactory preparedStatementCreatorFactory 
	 = new PreparedStatementCreatorFactory(
			 "insert into Taco (name, createdAt) values (?, ?)",
			 Types.VARCHAR, Types.TIMESTAMP
			 );
		// By default, returnGeneratedKeys = false so change it to true
	 preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
	 
	 
	/* PreparedStatementCreator psc =
	 new PreparedStatementCreatorFactory(
	 "insert into Taco (name, createdAt) values (?, ?)",
	 Types.VARCHAR, Types.TIMESTAMP
	 ).newPreparedStatementCreator(
	 Arrays.asList(
	 taco.getName(),
	 new Timestamp(taco.getCreatedAt().getTime())));*/
	 PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(
			 Arrays.asList(
					 taco.getName(),
					 new Timestamp(taco.getCreatedAt().getTime())));
	 

	 KeyHolder keyHolder = new GeneratedKeyHolder();
	 jdbc.update(psc, keyHolder);
	 System.out.print(keyHolder);
	 return keyHolder.getKey().longValue();
	 }
	 private void saveIngredientToTaco(
	 Ingredient ingredient, long tacoId) {
		 
	//	 System.out.print(ingredient+ " cccccc "+tacoId);
	 jdbc.update(
	 "insert into Taco_Ingredients (taco, ingredient) " +
	 "values (?, ?)",
	 tacoId, ingredient.getId());
	 }
	
}
