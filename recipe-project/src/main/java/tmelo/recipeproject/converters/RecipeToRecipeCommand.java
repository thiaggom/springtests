package tmelo.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Category;
import tmelo.recipeproject.domain.Ingredient;
import tmelo.recipeproject.domain.Recipe;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	private final NotesToNotesCommand notesConverter;
	private final CategoryToCategoryCommand categoryConverter;
	private final IngredientToIngredientCommand ingredientConverter;
	
	
	
	public RecipeToRecipeCommand(NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter,
			IngredientToIngredientCommand ingredientConverter) {
		this.notesConverter = notesConverter;
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
	}



	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe source) {
		
		if (source == null) {
			return null;
		}

		RecipeCommand command = new RecipeCommand();
		command.setId(source.getId());
		command.setDescription(source.getDescription());
		command.setPrepTime(source.getPrepTime());
		command.setCookTime(source.getCookTime());
		command.setServings(source.getServings());
		command.setSource(source.getSource());
		command.setUrl(source.getUrl());
		command.setDirections(source.getDirections());
		command.setDifficulty(source.getDifficulty());
		
		command.setNotesCommand(notesConverter.convert(source.getNotes()));
		
		if (source.getCategories() != null && source.getCategories().size() > 0) {
			
			source.getCategories()
				.forEach((Category category) -> command.getCategoriesCommand().add(categoryConverter.convert(category)));
		}
		
		if (source.getIngredients() != null && source.getIngredients().size() > 0) {
			source.getIngredients()
				.forEach((Ingredient ingredient) -> command.getIngredientsCommand().add(ingredientConverter.convert(ingredient)));
		}
	
		return command;
	}

}
