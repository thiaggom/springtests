package tmelo.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	
	@RequestMapping("/recipe/show/{id}")
	public String getRecipeById(@PathVariable String id, Model model) {
		
		Recipe rec = recipeService.getRecipeById(Long.parseLong(id)); 
		
		model.addAttribute("recipe",rec);
		
		log.debug("recipe object sent to the view --> "+rec.toString());
		
		return "recipe/show";
	}
}
