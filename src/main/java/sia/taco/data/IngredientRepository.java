package sia.taco.data;

import org.springframework.data.repository.CrudRepository;

import sia.taco.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
	

}
