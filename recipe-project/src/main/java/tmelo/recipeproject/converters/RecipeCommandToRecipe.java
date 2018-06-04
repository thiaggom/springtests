package tmelo.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tmelo.recipeproject.commands.CategoryCommand;
import tmelo.recipeproject.commands.IngredientCommand;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Recipe;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

	private final NotesCommandToNotes notesConverter;
	private final CategoryCommandToCategory categoryConverter;
	private final IngredientCommandToIngredient ingredientConverter;
	
	public RecipeCommandToRecipe(NotesCommandToNotes notesConverter, CategoryCommandToCategory categoryConverter,
			IngredientCommandToIngredient ingredientConverter) {
		super();
		this.notesConverter = notesConverter;
		this.categoryConverter = categoryConverter;
		this.ingredientConverter = ingredientConverter;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand source) {

		if (source == null) {
			return null;
		}
		
		Recipe recipe = new Recipe();
		
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setCookTime(source.getCookTime());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setDirections(source.getDirections());
		recipe.setDifficulty(source.getDifficulty());
		
		recipe.setNotes(notesConverter.convert(source.getNotesCommand()));
		
		if (source.getCategoriesCommand() != null && source.getCategoriesCommand().size() > 0) {

			source.getCategoriesCommand()
				.forEach((CategoryCommand catCommand) -> recipe.getCategories().add(categoryConverter.convert(catCommand)));
		}
		
		if (source.getIngredientsCommand() != null && source.getIngredientsCommand().size() > 0) {
			
			source.getIngredientsCommand()
				.forEach((IngredientCommand ingredientCommand) -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
		}
		
		return recipe;
		
	}
	
	
	
	
	
}
