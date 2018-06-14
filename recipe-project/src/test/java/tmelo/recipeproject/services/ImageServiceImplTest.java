package tmelo.recipeproject.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.exceptions.NotFoundException;
import tmelo.recipeproject.repositories.RecipeRepository;

public class ImageServiceImplTest {

	private ImageServiceImpl imageService;
	
	@Mock
	private RecipeRepository recipeRepo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageService = new ImageServiceImpl(recipeRepo);
	}
	
	@Test(expected=NotFoundException.class)
	public void recipeNotFoundTest() throws Exception {
		Optional<Recipe> optRecipe = Optional.empty();
		
		when(recipeRepo.findById(anyLong())).thenReturn(optRecipe);
		
		imageService.saveImageFile(anyLong(), null);
	}
	
	@Test
	public void saveRecipeImageTest() throws Exception {
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		Optional<Recipe> optRecipe = Optional.of(recipe);
		
		when(recipeRepo.findById(anyLong())).thenReturn(optRecipe);
		
		MultipartFile file = new MockMultipartFile("imagefile","test.txt","text/plain", "test of save".getBytes());
		
		imageService.saveImageFile(anyLong(), file);
		
		ArgumentCaptor<Recipe> captor = ArgumentCaptor.forClass(Recipe.class);
		
		verify(recipeRepo, times(1)).save(captor.capture());

		// testing returned recipe saved
		Recipe savedRecipe = captor.getValue();
		
		assertEquals(savedRecipe.getImage().length, file.getBytes().length);
		
		
	}
}
