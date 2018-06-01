package tmelo.recipeproject.services;

import java.util.Set;

import tmelo.recipeproject.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> getAllRecipes();
	
	public void saveRecipe(Recipe recipe);
	
	public Recipe getRecipeById(Long id);
	
}
