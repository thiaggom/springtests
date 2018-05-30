package tmelo.recipeproject.services;

import java.util.Set;

import tmelo.recipeproject.domain.Recipe;

public interface RecipesService {

	public Set<Recipe> getAllRecipes();
	
	public void saveRecipe(Recipe recipe);
	
	public void getRecipeById(Long id);
	
}
