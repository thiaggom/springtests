package tmelo.recipeproject.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.repositories.RecipeRepository;

public class RecipesServicesImplTest {

	private RecipesServicesImpl recipeService;
	
	@Mock
	private RecipeRepository recipeRepo;
	
	@Before
	public void init() throws Exception{
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipesServicesImpl(recipeRepo);
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
	}
	
}
