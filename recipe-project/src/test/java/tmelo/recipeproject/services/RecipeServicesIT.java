package tmelo.recipeproject.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.converters.RecipeCommandToRecipe;
import tmelo.recipeproject.converters.RecipeToRecipeCommand;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServicesIT {

	private final String RECIPE_DESCRIPTION = "new description";
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Transactional
	@Test
	public void testSavedDescription() throws Exception {
		
		Recipe originRecipe = recipeRepository.findAll().iterator().next();
		
		RecipeCommand recipeCommand = recipeToRecipeCommand.convert(originRecipe);
		
		recipeCommand.setDescription(RECIPE_DESCRIPTION);
		
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		
		assertNotNull(savedRecipeCommand);
		assertEquals(RECIPE_DESCRIPTION, savedRecipeCommand.getDescription());
		assertEquals(recipeCommand.getId(), savedRecipeCommand.getId());
		assertEquals(recipeCommand.getCategoriesCommand().size(), savedRecipeCommand.getCategoriesCommand().size());
		assertEquals(recipeCommand.getIngredientsCommand().size(), savedRecipeCommand.getIngredientsCommand().size());
		
		
	}
	
}
