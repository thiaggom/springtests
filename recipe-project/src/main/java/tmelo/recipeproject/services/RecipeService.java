package tmelo.recipeproject.services;

import java.util.Set;

import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Recipe;

public interface RecipeService {

	public Set<Recipe> getAllRecipes();
	
	public void saveRecipe(Recipe recipe);

	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	
	public Recipe getRecipeById(Long id);
	
}
