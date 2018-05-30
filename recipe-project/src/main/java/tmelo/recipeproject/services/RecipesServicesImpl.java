package tmelo.recipeproject.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import tmelo.recipeproject.domain.Recipe;
import tmelo.recipeproject.repositories.RecipeRepository;

@Service
public class RecipesServicesImpl implements RecipesService{

	private final RecipeRepository recipeRepository;

	public RecipesServicesImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getAllRecipes() {
		HashSet<Recipe> recipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
		
		return recipes;
	}

	@Override
	public void saveRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
	}

	@Override
	public void getRecipeById(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
