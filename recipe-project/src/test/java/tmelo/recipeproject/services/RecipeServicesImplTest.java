package tmelo.recipeproject.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.converters.RecipeCommandToRecipe;
import tmelo.recipeproject.converters.RecipeToRecipeCommand;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.repositories.RecipeRepository;

public class RecipeServicesImplTest {

	private RecipeServicesImpl recipeService;
	
	@Mock
	private RecipeRepository recipeRepo;
	
	@Mock
	private RecipeToRecipeCommand recipeToRecipeCommand;
	
	@Mock
	private RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Before
	public void init() throws Exception{
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServicesImpl(recipeRepo,recipeToRecipeCommand, recipeCommandToRecipe);
	}
	
	@Test
	public void getRecipeByIdTest() throws Exception {
		Recipe rec = new Recipe();
		rec.setId(1L);
		Optional<Recipe> optRec = Optional.of(rec);
		
		when(recipeRepo.findById(anyLong())).thenReturn(optRec);
		
		Recipe returnedRecipe = recipeService.getRecipeById(1L);
		
		assertNotNull("Null recipe returned", returnedRecipe);
		verify(recipeRepo, times(1)).findById(anyLong());
		verify(recipeRepo, never()).findAll();
	}
	
	@Test
	public void getRecipes() throws Exception {
		
		Recipe recipe = new Recipe();
		Set<Recipe> mockResult = new HashSet<>();
		mockResult.add(recipe);
		
		when(recipeService.getAllRecipes()).thenReturn(mockResult);
		
		Set<Recipe> returnedData = recipeService.getAllRecipes();
		assertEquals(returnedData.size(), 1);
		
		// verifing if the RecipeRepository was called only once...
		verify(recipeRepo, times(1)).findAll();
		verify(recipeRepo, never()).findById(anyLong());
	}

	@Test
	public void testDeleteRecipeById() throws Exception {
		
		recipeService.deleteRecipeById(1L);
		
		verify(recipeRepo, times(1)).deleteById(anyLong());
		
	}
	
	@Test
	public void findCommandByIdTest() throws Exception {
		
		// mock the repository result
		Long lID = 1L;
		String sDescription = "description";
		Recipe recipe = new Recipe();
		recipe.setId(lID);
		recipe.setDescription(sDescription);
		
		Optional<Recipe> optRecipe = Optional.of(recipe);
		
		when(recipeRepo.findById(anyLong())).thenReturn(optRecipe);
		
		RecipeCommand command = new RecipeCommand();
		command.setId(lID);
		command.setDescription(sDescription);
		when(recipeToRecipeCommand.convert(any())).thenReturn(command);
		
		RecipeCommand returnedCommand = recipeService.findCommandById(lID);
		assertNotNull(returnedCommand);
		assertEquals(lID, returnedCommand.getId());
		assertEquals(sDescription, returnedCommand.getDescription());
		verify(recipeRepo, times(1)).findById(anyLong());
		verify(recipeRepo, never()).findAll();
	}
}
