package sia.taco.data;

import sia.taco.models.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	Ingredient findOne(String id);
	Ingredient save(Ingredient ingredient);
}
