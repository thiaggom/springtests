package tmelo.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tmelo.recipeproject.commands.IngredientCommand;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.commands.UnitOfMeasureCommand;
import tmelo.recipeproject.services.IngredientService;
import tmelo.recipeproject.services.RecipeService;
import tmelo.recipeproject.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientsController {

	private RecipeService recipeService;

	private IngredientService ingredientService;
	
	private UnitOfMeasureService unitOfMeasureService;
	
	public IngredientsController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}
	
	@GetMapping("/recipe/{id}/ingredients")
	public String getIngredients(@PathVariable String id, Model model) {
		log.debug("Getting ingredients of recipe: "+id);
		
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		
		return "recipe/ingredients/list";
	}

	@GetMapping("recipe/{recipeId}/ingredients/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        return "recipe/ingredients/show";
    }

	@GetMapping("recipe/{recipeId}/ingredients/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredients/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients/" + savedCommand.getId() + "/show";
    }
    
    @GetMapping("recipe/{recipeId}/ingredients/new")
    public String newIngredient(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUomCommand(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredients/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredients/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
    
}
