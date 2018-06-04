package tmelo.recipeproject.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.services.RecipeService;

public class RecipeControllerTest {

	private RecipeController recipeController;

	@Mock
	private RecipeService recipeService;

	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.recipeController = new RecipeController(recipeService);
	}

	@Test
	public void getRecipeByIdTest() throws Exception {

		MockMvc mock = MockMvcBuilders.standaloneSetup(recipeController).build();

		Recipe rec = new Recipe();
		rec.setId(1L);

		when(recipeService.getRecipeById(anyLong())).thenReturn(rec);

		mock.perform(get("/recipe/show/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/show"))
			.andExpect(model().attributeExists("recipe"));

	}

}
