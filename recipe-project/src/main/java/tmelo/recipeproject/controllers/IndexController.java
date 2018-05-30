package tmelo.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import tmelo.recipeproject.services.RecipesService;

@Controller
public class IndexController {

	private final RecipesService recipesService;
	
	public IndexController(RecipesService recipesService) {
		this.recipesService = recipesService;
	}

	@RequestMapping({"","/","/index","/home"})
	public String getIndexPage(Model model) {

		model.addAttribute("recipes", recipesService.getAllRecipes());
		
		return "index";
	}
}
