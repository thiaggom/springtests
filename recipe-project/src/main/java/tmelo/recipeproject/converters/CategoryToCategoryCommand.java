package tmelo.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tmelo.recipeproject.commands.CategoryCommand;
import tmelo.recipeproject.domain.Category;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand>{

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {

		if (source == null) {
			return null;
		}
		
		final CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(source.getId());
		categoryCommand.setCategoryName(source.getCategoryName());
		
		return categoryCommand;
	}

	
	
}
