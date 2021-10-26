package sia.taco.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
public class Ingredient {

	@Id
	private  String id;
	private  String name;
	private  Type type;
	
	public Ingredient()
	{
		
		
	}
	public Ingredient(String a,String b,Type c)
	{
		id = a;
		name = b;
		type = c;
	}
	public static enum Type
	{
		WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
	}
}
