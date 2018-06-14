package tmelo.recipeproject.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.exceptions.NotFoundException;
import tmelo.recipeproject.services.ImageService;
import tmelo.recipeproject.services.RecipeService;

public class ImageControllerTest {

	@Mock
	private RecipeService recipeService;
	
	@Mock
	private ImageService imageService;
	
	private ImageController controller;
	
	private MockMvc mock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new ImageController(recipeService, imageService);
		mock = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new ControllerExceptionHandler()).build();
	}

	@Test
	public void getRecipeImageTest() throws Exception {
		
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);
		
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		mock.perform(get("/recipe/1/image"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/imageuploadform"))
			.andExpect(model().attributeExists("recipe"));
		
		verify(recipeService,times(1)).findCommandById(anyLong());
	}
	
	@Test
	public void getRecipeImageBadRequestTest() throws Exception {
		
		mock.perform(get("/recipe/aa/image"))
		.andExpect(status().isBadRequest())
		.andExpect(model().attributeExists("exception"))
		.andExpect(view().name("400error"));
		
	}
	
	@Test
	public void getRecipeImageNotFoundTest() throws Exception {
		
		when(recipeService.findCommandById(anyLong())).thenThrow(NotFoundException.class);
		
		mock.perform(get("/recipe/1/image"))
		.andExpect(status().isNotFound())
		.andExpect(model().attributeExists("exception"))
		.andExpect(view().name("404error"));
		
	}
	
    @Test
    public void saveImagePost() throws Exception {
    	MockMultipartFile mockFile = new MockMultipartFile("imagefile", "test.txt", "text/plain", "teste".getBytes());
    	
    	mock.perform(multipart("/recipe/1/image").file(mockFile))
    		.andExpect(status().is3xxRedirection())
    		.andExpect(header().string(HttpHeaders.LOCATION, "/recipe/1/show"));
    	
    	verify(imageService, times(1)).saveImageFile(anyLong(), any());

    	
    }
	
    @Test
    public void getImageFromDB() throws Exception {

        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()){
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mock.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] reponseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, reponseBytes.length);
    }
    
	
}
