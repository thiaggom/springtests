package tmelo.recipeproject.services;

import tmelo.recipeproject.commands.IngredientCommand;

public interface IngredientService {
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	
	public IngredientCommand saveIngredientCommand(IngredientCommand command);
}
