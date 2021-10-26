package sia.taco.data;

import org.springframework.data.repository.CrudRepository;

import sia.taco.models.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{
	
}
