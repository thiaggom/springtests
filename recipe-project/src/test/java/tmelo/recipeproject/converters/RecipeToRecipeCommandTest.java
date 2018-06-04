package tmelo.recipeproject.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Category;
import tmelo.recipeproject.domain.Difficulty;
import tmelo.recipeproject.domain.Ingredient;
import tmelo.recipeproject.domain.Notes;
import tmelo.recipeproject.domain.Recipe;

public class RecipeToRecipeCommandTest {
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

	private RecipeToRecipeCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(new NotesToNotesCommand(), new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
	}
	
	@Test
	public void testNullParameter() throws Exception {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyParameter() throws Exception {
		assertNotNull(converter.convert(new Recipe()));
	}
	
	@Test
	public void testConvertion() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID);
		recipe.setDescription(DESCRIPTION);
		recipe.setCookTime(COOK_TIME);
		recipe.setPrepTime(PREP_TIME);
		recipe.setDirections(DIRECTIONS);
		recipe.setDifficulty(DIFFICULTY);
		recipe.setServings(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);

		Category cat1 = new Category();
		cat1.setId(CAT_ID_1);
		recipe.getCategories().add(cat1);
		
		Category cat2 = new Category();
		cat2.setId(CAT_ID2);
		recipe.getCategories().add(cat2);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(INGRED_ID_1);
		recipe.getIngredients().add(ingredient1);
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(INGRED_ID_2);
		recipe.getIngredients().add(ingredient2);

		Notes notes = new Notes();
		notes.setId(NOTES_ID);
		recipe.setNotes(notes);

		RecipeCommand command = converter.convert(recipe);
		
		assertNotNull(command);
		assertEquals(RECIPE_ID, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(COOK_TIME, command.getCookTime());
		assertEquals(PREP_TIME, command.getPrepTime());
		assertEquals(DIRECTIONS, command.getDirections());
		assertEquals(DIFFICULTY, command.getDifficulty());
		assertEquals(SERVINGS, command.getServings());
		assertEquals(SOURCE, command.getSource());
		assertEquals(URL, command.getUrl());
		assertEquals(2, command.getCategoriesCommand().size());
		assertEquals(2, command.getIngredientsCommand().size());
		assertEquals(NOTES_ID, command.getNotesCommand().getId());
		
	}
	
}