package sia.taco.utils;

import java.util.List;

import sia.taco.models.Ingredient;
import sia.taco.models.Ingredient.Type;
import java.util.stream.Collectors;  

public class Utility {
	
	public static List<Ingredient> filterByTypes(List<Ingredient>ingredients,Type type)
	{
		return ingredients.stream().filter(x -> x.getType()==type).collect(Collectors.toList());
	}
}
