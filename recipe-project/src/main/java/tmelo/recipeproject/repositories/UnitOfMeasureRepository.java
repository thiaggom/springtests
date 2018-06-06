package tmelo.recipeproject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tmelo.recipeproject.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

	public Optional<UnitOfMeasure> findByDescription(String description);
	
}
