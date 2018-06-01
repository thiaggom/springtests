package tmelo.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import tmelo.recipeproject.services.RecipeService;

@Controller
public class IndexController {

	private final RecipeService recipesService;
	
	public IndexController(RecipeService recipesService) {
		this.recipesService = recipesService;
	}

	@RequestMapping({"","/","/index","/home"})
	public String getIndexPage(Model model) {

		model.addAttribute("recipes", recipesService.getAllRecipes());
		
		return "index";
	}
}
