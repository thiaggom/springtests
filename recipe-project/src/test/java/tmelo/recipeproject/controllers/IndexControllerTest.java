package tmelo.recipeproject.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.services.RecipeService;

public class IndexControllerTest {

	private IndexController indexController;
	
	@Mock
	private RecipeService recipesService;
	
	@Mock
	private Model model;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		indexController = new IndexController(recipesService);
	}
	
	@Test
	public void testHtmlIndexPage() throws Exception {
		MockMvc mvc = MockMvcBuilders.standaloneSetup(indexController).build();
		
		mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));
	}
	
	@Test
	public void getIndexPage() throws Exception {
	
		Set<Recipe> recipes = new HashSet<>();
		Recipe rec1 = new Recipe();
		rec1.setDescription("recipe 1");
		Recipe rec2 = new Recipe();
		rec2.setDescription("recipe 2");
		recipes.add(rec1);
		recipes.add(rec2);

		when(recipesService.getAllRecipes()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
		
		String returnedPage = indexController.getIndexPage(model);
		assertEquals("index", returnedPage);
		
		// verifing if the service method was called only once...
		verify(recipesService, times(1)).getAllRecipes();
		
		// verifing if the recipes attribute was set in the page model
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		
		// verifing if the object returned in the model was the Set with 2 positions...
		assertEquals(2, argumentCaptor.getValue().size());
	}
}
