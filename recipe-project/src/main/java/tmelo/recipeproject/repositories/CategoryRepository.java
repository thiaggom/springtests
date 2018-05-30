package tmelo.recipeproject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tmelo.recipeproject.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	public Optional<Category> findByCategoryName(String categoryName);
	
}
