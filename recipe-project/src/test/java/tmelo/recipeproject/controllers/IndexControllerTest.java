package tmelo.recipeproject.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import tmelo.recipeproject.services.RecipesService;

public class IndexControllerTest {

	private IndexController indexController;
	
	@Mock
	private RecipesService recipesService;
	
	@Mock
	private Model model;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		indexController = new IndexController(recipesService);
	}
	
	@Test
	public void getIndexPage() throws Exception {
		
		String returnedPage = indexController.getIndexPage(model);
		assertEquals("index", returnedPage);
		
		// verifing if the service method was called only once...
		verify(recipesService, times(1)).getAllRecipes();
		
		// verifing if the recipes attribute was set in the page model
		verify(model, times(1)).addAttribute(eq("recipes"), anySet());
	}
}
