package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.CategoryCommand;
import tmelo.recipeproject.commands.IngredientCommand;
import tmelo.recipeproject.commands.NotesCommand;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Difficulty;
import tmelo.recipeproject.domain.Recipe;

public class RecipeCommandToRecipeTest {

	private static final Long RECIPE_ID = 1L;
	private static final Integer COOK_TIME = Integer.valueOf("5");
	private static final Integer PREP_TIME = Integer.valueOf("7");
	private static final String DESCRIPTION = "My Recipe";
	private static final String DIRECTIONS = "Directions";
	private static final Difficulty DIFFICULTY = Difficulty.EASY;
	private static final Integer SERVINGS = Integer.valueOf("3");
	private static final String SOURCE = "Source";
	private static final String URL = "Some URL";
	private static final Long CAT_ID_1 = 1L;
	private static final Long CAT_ID2 = 2L;
	private static final Long INGRED_ID_1 = 3L;
	private static final Long INGRED_ID_2 = 4L;
	private static final Long NOTES_ID = 9L;

	private RecipeCommandToRecipe converter;

	@Before
	public void setUp() throws Exception {

		converter = new RecipeCommandToRecipe(new NotesCommandToNotes(), new CategoryCommandToCategory(),
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));

	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new RecipeCommand()));
	}
	
	@Test
	public void testConvertion() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(RECIPE_ID);
		command.setDescription(DESCRIPTION);
		command.setCookTime(COOK_TIME);
		command.setPrepTime(PREP_TIME);
		command.setDirections(DIRECTIONS);
		command.setDifficulty(DIFFICULTY);
		command.setServings(SERVINGS);
		command.setSource(SOURCE);
		command.setUrl(URL);

		CategoryCommand cat1 = new CategoryCommand();
		cat1.setId(CAT_ID_1);
		command.getCategoriesCommand().add(cat1);
		
		CategoryCommand cat2 = new CategoryCommand();
		cat2.setId(CAT_ID2);
		command.getCategoriesCommand().add(cat2);

		IngredientCommand ingredient1 = new IngredientCommand();
		ingredient1.setId(INGRED_ID_1);
		command.getIngredientsCommand().add(ingredient1);
		
		IngredientCommand ingredient2 = new IngredientCommand();
		ingredient2.setId(INGRED_ID_2);
		command.getIngredientsCommand().add(ingredient2);

		NotesCommand notes = new NotesCommand();
		notes.setId(NOTES_ID);
		command.setNotesCommand(notes);

		Recipe recipe = converter.convert(command);
		
		assertNotNull(recipe);
		assertEquals(RECIPE_ID, recipe.getId());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(COOK_TIME, recipe.getCookTime());
		assertEquals(PREP_TIME, recipe.getPrepTime());
		assertEquals(DIRECTIONS, recipe.getDirections());
		assertEquals(DIFFICULTY, recipe.getDifficulty());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());
		assertEquals(2, recipe.getCategories().size());
		assertEquals(2, recipe.getIngredients().size());
		assertEquals(NOTES_ID, recipe.getNotes().getId());
	}
	
	

}
