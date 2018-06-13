package tmelo.recipeproject.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tmelo.recipeproject.commands.RecipeCommand;
import tmelo.recipeproject.converters.RecipeCommandToRecipe;
import tmelo.recipeproject.converters.RecipeToRecipeCommand;
import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.exceptions.NotFoundException;
import tmelo.recipeproject.repositories.RecipeRepository;

@Slf4j
@Service
public class RecipeServicesImpl implements RecipeService{

	private final RecipeRepository recipeRepository;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	
	public RecipeServicesImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand,
			RecipeCommandToRecipe recipeCommandToRecipe) {
		this.recipeRepository = recipeRepository;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
	}

	@Override
	public Set<Recipe> getAllRecipes() {
		HashSet<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		
		log.debug("## getAllRecipes returned list of "+recipes.size());
		return recipes;
	}

	@Override
	public void saveRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
	}

	@Override
	public Recipe getRecipeById(Long id) {
		
		Optional<Recipe> optRecipe = recipeRepository.findById(id);
		
		if (!optRecipe.isPresent()) {
			throw new NotFoundException("Recipe "+id+" was not Found!");
		}
		
		return optRecipe.get();
	}

	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		
		Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
		
		Recipe savedRecipe = recipeRepository.save(detachedRecipe);
		
		log.debug("## saved recipe id: "+savedRecipe.getId());
		
		return recipeToRecipeCommand.convert(savedRecipe);
	}

	@Override
	@Transactional
	public RecipeCommand findCommandById(Long id) {
		log.debug("## findCommandById: "+id);
		return recipeToRecipeCommand.convert(getRecipeById(id));
	}

	@Override
	public void deleteRecipeById(Long id) {
		recipeRepository.deleteById(id);
	}


	
	
}
