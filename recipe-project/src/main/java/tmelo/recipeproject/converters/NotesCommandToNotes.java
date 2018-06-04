package tmelo.recipeproject.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.Synchronized;
import tmelo.recipeproject.commands.NotesCommand;
import tmelo.recipeproject.domain.Notes;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>{

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		
		if (source == null) {
			return null;
		}
		
		final Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setRecipeNotes(source.getRecipeNotes());
		
		return notes;
	}
	
}
