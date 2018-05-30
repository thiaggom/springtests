package tmelo.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;

import tmelo.recipeproject.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
