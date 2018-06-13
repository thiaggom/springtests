package tmelo.recipeproject.controllers;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Difficulty;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.exceptions.InvalidParametersException;
import tmelo.recipeproject.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping("/recipe/{id}/show")
	public String getRecipeById(@PathVariable String id, Model model) {
		
		Long pId = null;
		
		try {
			pId = Long.valueOf(id);
		} catch (NumberFormatException e) {
			throw new InvalidParametersException("Invalid Recipe Id: "+id);
		}
		
		Recipe rec = recipeService.getRecipeById(pId); 
		
		model.addAttribute("recipe",rec);
		
		log.debug("recipe object sent to the view --> "+rec.toString());
		
		return "recipe/show";
	}
	
	@GetMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}
	
	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
	}
	
	@GetMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id, Model model) {
		log.debug("## Deleting recipe: "+id);
		
		recipeService.deleteRecipeById(Long.valueOf(id));
		
		return "redirect:/";
	}

}
