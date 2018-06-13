package tmelo.recipeproject.controllers;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.exceptions.InvalidParametersException;
import tmelo.recipeproject.exceptions.NotFoundException;
import tmelo.recipeproject.services.RecipeService;

public class RecipeControllerTest {

	private RecipeController recipeController;

	@Mock
	private RecipeService recipeService;

	MockMvc mock;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.recipeController = new RecipeController(recipeService);
		this.mock = MockMvcBuilders
					.standaloneSetup(recipeController)
					.setControllerAdvice(new ControllerExceptionHandler())
					.build();
	}

	@Test
	public void getRecipeByIdTest() throws Exception {

		Recipe rec = new Recipe();
		rec.setId(1L);

		when(recipeService.getRecipeById(anyLong())).thenReturn(rec);

		mock.perform(get("/recipe/1/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"))
			.andExpect(model().attributeExists("recipe"));

	}

	@Test
	public void getRecipeByIdTestNotFound() throws Exception {
		
		when(recipeService.getRecipeById(anyLong())).thenThrow(NotFoundException.class);
		
		mock.perform(get("/recipe/1/show"))
			.andExpect(status().isNotFound())
			.andExpect(view().name("404error"));
		
	}

	@Test
	public void getRecipeByIdTestBadRequest() throws Exception {
		when(recipeService.getRecipeById(anyLong())).thenThrow(InvalidParametersException.class);
		
		mock.perform(get("/recipe/1/show"))
			.andExpect(status().isBadRequest())
			.andExpect(view().name("400error"));
		
	}
	
	@Test
	public void testGetNewRecipe() throws Exception {
		this.mock
			.perform(get("/recipe/new"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("recipe"))
			.andExpect(model().attribute("recipe.id", nullValue()))
			.andExpect(view().name("recipe/recipeform"));
	}
	
	@Test
	public void testGetUpdateView() throws Exception {
		Long lID = 1L;
		RecipeCommand command = new RecipeCommand();
		command.setId(lID);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		this.mock
		.perform(get("/recipe/1/update"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("recipe"))
		.andExpect(view().name("recipe/recipeform"));
	}
	
	@Test
	public void testPostNewRecipeForm() throws Exception {
		
		RecipeCommand command = new RecipeCommand();
		
		Long lID = 1L;
		String sDescription = "description";
		
		command.setId(lID);
		command.setDescription(sDescription);
		
		when(recipeService.saveRecipeCommand(any())).thenReturn(command);
		
		mock.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", sDescription))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/"+lID+"/show"));
		
	}
	
	@Test
	public void testDeleteRecipe() throws Exception{
		
		mock.perform(get("/recipe/1/delete"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/"));
		
		verify(recipeService, times(1)).deleteRecipeById(anyLong());
		
	}
	
}
