package tmelo.recipeproject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.exceptions.InvalidParametersException;
import tmelo.recipeproject.exceptions.NotFoundException;
import tmelo.recipeproject.services.RecipeService;

@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/show")
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
	
	@GetMapping
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		
		return "recipe/recipeform";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.parseLong(id)));
		return "recipe/recipeform";
	}
	
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		
		return "redirect:/recipe/"+savedCommand.getId()+"/show";
	}
	
	@GetMapping
	@RequestMapping("/recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id, Model model) {
		log.debug("## Deleting recipe: "+id);
		
		recipeService.deleteRecipeById(Long.valueOf(id));
		
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception");
		log.error(exception.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", exception);
		modelAndView.setViewName("404error");
		
		return modelAndView;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidParametersException.class)
	public ModelAndView handleBadRequest(Exception e) {
		log.error(e.getMessage());
		
		ModelAndView modView = new ModelAndView();
		modView.addObject("exception", e);
		
		modView.setViewName("400error");
		
		return modView;
	}

}
