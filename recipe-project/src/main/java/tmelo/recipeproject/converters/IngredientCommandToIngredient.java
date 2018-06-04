package tmelo.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tmelo.recipeproject.commands.IngredientCommand;
import tmelo.recipeproject.domain.Ingredient;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient>{

	private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomCommandToUom) {
		this.uomConverter = uomCommandToUom;
	}


	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {

		if (source == null) {
			return null;
		}

		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setUom(uomConverter.convert(source.getUomCommand()));
		
		return ingredient;
	}

	
}
